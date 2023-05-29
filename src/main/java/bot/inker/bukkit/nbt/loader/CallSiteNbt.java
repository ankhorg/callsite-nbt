package bot.inker.bukkit.nbt.loader;

import bot.inker.bukkit.nbt.*;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import org.objectweb.asm.Type;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.MethodRemapper;
import org.objectweb.asm.commons.Remapper;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.invoke.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

public class CallSiteNbt {
  private static final String CSN_PACKAGE;
  private static final String CSN_CLASS_FILE_PREFIX;
  private static final String CSN_LOADER_PACKAGE;
  private static final String CSN_LOADER_CLASS_FILE_PREFIX;
  private static final String CSN_REF_PACKAGE;
  private static final String CSN_REF_PACKAGE_INTERNAL;

  private static boolean loaded = false;
  private static MethodHandles.Lookup lookup;
  private static TransformRemapper transformRemapper;

  static {
    CSN_PACKAGE = "bot.inker.bukkit.nbt.".replace('.', '.');
    CSN_CLASS_FILE_PREFIX = CSN_PACKAGE.replace('.', '/');

    CSN_LOADER_PACKAGE = "bot.inker.bukkit.nbt.loader.".replace('.', '.');
    CSN_LOADER_CLASS_FILE_PREFIX = CSN_LOADER_PACKAGE.replace('.', '/');

    CSN_REF_PACKAGE = "bot.inker.bukkit.nbt.loader.ref.".replace('.', '.');
    CSN_REF_PACKAGE_INTERNAL = CSN_REF_PACKAGE.replace('.', '/');
  }

  public static void install(Class<?> clazz) {
    if (!loaded) {
      try {
        installImpl(clazz);
      } catch (ReflectiveOperationException | IOException e) {
        throw new IllegalStateException("Failed to install CallSiteNbt", e);
      }
      Class<?>[] classes = new Class<?>[]{
          Nbt.class, NbtByte.class, NbtByteArray.class, NbtCompound.class, NbtDouble.class, NbtEnd.class,
          NbtFloat.class, NbtInt.class, NbtIntArray.class, NbtList.class, NbtLong.class, NbtLongArray.class,
          NbtNumeric.class, NbtShort.class, NbtString.class
      };
      for (Class<?> loadedClass : classes) {
        loadedClass.getName();
      }
      loaded = true;
    }
  }

  private static void installImpl(Class<?> clazz) throws ReflectiveOperationException, IOException {
    Class<?> pluginClassLoaderClass = Class.forName("org.bukkit.plugin.java.PluginClassLoader");
    ClassLoader classLoader = clazz.getClassLoader();
    if (classLoader.getClass() != pluginClassLoaderClass) {
      throw new IllegalStateException("CallSiteNbt can only install in PluginClassLoader");
    }
    Field fileField = pluginClassLoaderClass.getDeclaredField("file");
    fileField.setAccessible(true);
    File pluginFile = (File) fileField.get(classLoader);
    Field jarField = pluginClassLoaderClass.getDeclaredField("jar");
    jarField.setAccessible(true);
    JarFile pluginJar = (JarFile) jarField.get(classLoader);
    jarField.set(classLoader, new DelegateJarFile(pluginFile, pluginJar));

    lookup = provideLookup();
    transformRemapper = new TransformRemapper(new RefOnlyClassLoader(classLoader, pluginJar));
  }

  private static <T extends Throwable> MethodHandles.Lookup provideLookup() throws T {
    try {
      Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafeField.setAccessible(true);
      Unsafe unsafe = (Unsafe) theUnsafeField.get(null);

      Field implLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
      return (MethodHandles.Lookup) unsafe.getObject(
          unsafe.staticFieldBase(implLookupField),
          unsafe.staticFieldOffset(implLookupField)
      );
    } catch (Throwable e) {
      throw (T) e;
    }
  }

  private static byte[] transformBaseClass(byte[] input) {
    ClassReader reader = new ClassReader(input);
    ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    ClassVisitor visitor = new TransformClassVisitor(writer, transformRemapper);
    reader.accept(visitor, 0);
    return writer.toByteArray();
  }

  private static byte[] readAllBytes(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(32, in.available()));
    byte[] buf = new byte[4096];
    int r;
    while ((r = in.read(buf)) != -1) {
      out.write(buf, 0, r);
    }
    return out.toByteArray();
  }

  private static String[] parseMethod(String reference) {
    int firstSplitIndex = reference.indexOf(';');
    int secondSplitIndex = reference.indexOf('(', firstSplitIndex);
    String[] result = new String[3];
    result[0] = parseType(
        Type.getObjectType(reference.substring(1, firstSplitIndex))
    ).getInternalName();
    result[1] = reference.substring(firstSplitIndex + 1, secondSplitIndex);
    result[2] = parseType(
        Type.getMethodType(reference.substring(secondSplitIndex))
    ).getDescriptor();
    return result;
  }

  private static String[] parseField(String reference) {
    int firstSplitIndex = reference.indexOf(';');
    int secondSplitIndex = reference.indexOf(':');
    String[] result = new String[3];
    result[0] = parseType(
        Type.getObjectType(reference.substring(1, firstSplitIndex))
    ).getInternalName();
    result[1] = reference.substring(firstSplitIndex + 1, secondSplitIndex);
    result[2] = parseType(
        Type.getType(reference.substring(secondSplitIndex + 1))
    ).getDescriptor();
    return result;
  }

  private static String parseClass(String reference) {
    return parseType(Type.getObjectType(reference)).getInternalName();
  }

  private static Type parseType(Type type) {
    switch (type.getSort()) {
      case Type.ARRAY:
        StringBuilder remappedDescriptor = new StringBuilder();
        for (int i = 0; i < type.getDimensions(); ++i) {
          remappedDescriptor.append('[');
        }
        remappedDescriptor.append(parseType(type.getElementType()).getDescriptor());
        return Type.getType(remappedDescriptor.toString());
      case Type.OBJECT:
        String matchedPrefix, internalName = type.getInternalName();
        if (internalName.startsWith("org/bukkit/craftbukkit/")) {
          matchedPrefix = "org/bukkit/craftbukkit/";
        } else if (!CbVersion.v1_17_R1.isSupport() && internalName.startsWith("net/minecraft/server/")) {
          matchedPrefix = "net/minecraft/server/";
        } else {
          return type;
        }
        int splitIndex = internalName.indexOf('/', matchedPrefix.length());
        return Type.getObjectType(matchedPrefix + CbVersion.current() + internalName.substring(splitIndex));
      case Type.METHOD:
        Type methodType = Type.getMethodType(type.getDescriptor());
        Type[] newArgumentTypes = new Type[methodType.getArgumentTypes().length];
        for (int i = 0; i < newArgumentTypes.length; i++) {
          newArgumentTypes[i] = parseType(methodType.getArgumentTypes()[i]);
        }
        return Type.getMethodType(parseType(methodType.getReturnType()), newArgumentTypes);
      default:
        return type;
    }
  }

  private static Class<?> typeToClass(ClassLoader classLoader, Type type) throws ClassNotFoundException {
    switch (type.getSort()) {
      case Type.VOID:
        return void.class;
      case Type.BOOLEAN:
        return boolean.class;
      case Type.CHAR:
        return char.class;
      case Type.BYTE:
        return byte.class;
      case Type.SHORT:
        return short.class;
      case Type.INT:
        return int.class;
      case Type.FLOAT:
        return float.class;
      case Type.LONG:
        return long.class;
      case Type.DOUBLE:
        return double.class;
      case Type.ARRAY: {
        Class<?> currentClass = Class.forName(type.getElementType().getClassName(), false, classLoader);
        for (int i = 0; i < type.getDimensions(); i++) {
          currentClass = Array.newInstance(currentClass, 0).getClass();
        }
        return currentClass;
      }
      case Type.OBJECT:
        return Class.forName(type.getClassName(), false, classLoader);
      default:
        throw new IllegalArgumentException("Unsupported class type: " + type);
    }
  }

  private static class DelegateJarFile extends JarFile {
    private final JarFile jarFile;

    public DelegateJarFile(File file, JarFile jarFile) throws IOException {
      super(file);
      this.jarFile = jarFile;
    }

    @Override
    public Manifest getManifest() throws IOException {
      return jarFile.getManifest();
    }

    @Override
    public JarEntry getJarEntry(String name) {
      return jarFile.getJarEntry(name);
    }

    @Override
    public ZipEntry getEntry(String name) {
      return jarFile.getEntry(name);
    }

    @Override
    public Enumeration<JarEntry> entries() {
      return jarFile.entries();
    }

    @Override
    public Stream<JarEntry> stream() {
      return jarFile.stream();
    }

    @Override
    public InputStream getInputStream(ZipEntry ze) throws IOException {
      if (ze.getName().startsWith(CSN_CLASS_FILE_PREFIX)
          && !ze.getName().startsWith(CSN_LOADER_CLASS_FILE_PREFIX)
          && ze.getName().endsWith(".class")) {
        byte[] bytes;
        try (InputStream in = jarFile.getInputStream(ze)) {
          bytes = readAllBytes(in);
        }
        bytes = transformBaseClass(bytes);
        if (true) {
          Path dumpPath = Paths.get("callsite-nbt", ze.getName());
          Files.createDirectories(dumpPath.getParent());
          Files.write(dumpPath, bytes);
        }
        return new ByteArrayInputStream(bytes);
      } else {
        return jarFile.getInputStream(ze);
      }
    }

    @Override
    public String getComment() {
      return jarFile.getComment();
    }

    @Override
    public String getName() {
      return jarFile.getName();
    }

    @Override
    public int size() {
      return jarFile.size();
    }

    @Override
    public void close() throws IOException {
      super.close();
      jarFile.close();
    }
  }

  private static class RefOnlyClassLoader extends ClassLoader {
    private final JarFile jar;

    public RefOnlyClassLoader(ClassLoader parent, JarFile jar) {
      super(parent);
      this.jar = jar;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      synchronized (getClassLoadingLock(name)) {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
          c = name.startsWith(CSN_REF_PACKAGE) ? findClass(name) : getParent().loadClass(name);
        }
        if (resolve) {
          resolveClass(c);
        }
        return c;
      }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      if (name.startsWith(CSN_REF_PACKAGE)) {
        String path = name.replace('.', '/').concat(".class");
        JarEntry entry = jar.getJarEntry(path);
        if (entry != null) {
          try (InputStream in = jar.getInputStream(entry)) {
            byte[] bytes = readAllBytes(in);
            return defineClass(name, bytes, 0, bytes.length, null);
          } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
          }
        }
      }
      throw new ClassNotFoundException(name);
    }
  }

  private static class TransformRemapper extends Remapper {
    private final RefOnlyClassLoader refOnlyClassLoader;

    public TransformRemapper(RefOnlyClassLoader refOnlyClassLoader) {
      this.refOnlyClassLoader = refOnlyClassLoader;
    }

    private Class<?> fetchRefClass(String internalName) {
      try {
        return refOnlyClassLoader.loadClass(internalName.replace('/', '.'));
      } catch (ClassNotFoundException e) {
        throw new IllegalStateException("No ref class found: " + internalName);
      }
    }

    private Field fetchRefField(String owner, String name, String descriptor) {
      Class<?> clazz = fetchRefClass(owner);
      for (Field field : clazz.getFields()) {
        if (field.getName().equals(name) && Type.getDescriptor(field.getType()).equals(descriptor)) {
          return field;
        }
      }
      throw new IllegalStateException("No ref class field found: L" + owner + ";" + name + ":" + descriptor);
    }

    private Executable fetchRefMethod(String owner, String name, String descriptor) {
      Class<?> clazz = fetchRefClass(owner);
      boolean isInit = name.equals("<init>");
      for (Executable method : isInit ? clazz.getConstructors() : clazz.getMethods()) {
        Type methodType = isInit ? Type.getType((Constructor<?>) method) : Type.getType((Method) method);
        if ((isInit || method.getName().equals(name)) && methodType.getDescriptor().equals(descriptor)) {
          return method;
        }
      }
      throw new IllegalStateException("No ref class method found: L" + owner + ";" + name + descriptor);
    }

    private HandleBy fetchHandleBy(AnnotatedElement element) {
      HandleBy.List list = element.getAnnotation(HandleBy.List.class);
      if (list != null) {
        return CbVersion.match(list.value());
      }
      HandleBy single = element.getAnnotation(HandleBy.class);
      return CbVersion.match(new HandleBy[]{single});
    }

    public HandleBy fetchClass(String internalName) {
      return fetchHandleBy(fetchRefClass(internalName));
    }

    public HandleBy fetchField(String owner, String name, String descriptor) {
      return fetchHandleBy(fetchRefField(owner, name, descriptor));
    }

    public HandleBy fetchMethod(String owner, String name, String descriptor) {
      return fetchHandleBy(fetchRefMethod(owner, name, descriptor));
    }

    @Override
    public String mapMethodName(String owner, String name, String descriptor) {
      if (!owner.startsWith(CSN_REF_PACKAGE_INTERNAL)) {
        return name;
      }
      HandleBy handleBy = fetchMethod(owner, name, descriptor);
      if (handleBy == null || handleBy.reference().isEmpty()) {
        return name;
      }
      String[] reference = parseMethod(handleBy.reference());
      return reference[1];
    }

    @Override
    public String mapFieldName(String owner, String name, String descriptor) {
      if (!owner.startsWith(CSN_REF_PACKAGE_INTERNAL)) {
        return name;
      }
      HandleBy handleBy = fetchField(owner, name, descriptor);
      if (handleBy == null || handleBy.reference().isEmpty()) {
        return name;
      }
      String[] reference = parseField(handleBy.reference());
      return reference[1];
    }

    @Override
    public String map(String internalName) {
      if (!internalName.startsWith(CSN_REF_PACKAGE_INTERNAL)) {
        return internalName;
      }
      HandleBy handleBy = fetchClass(internalName);
      if (handleBy == null || handleBy.reference().isEmpty()) {
        return internalName;
      }
      return parseClass(handleBy.reference());
    }
  }

  private static class TransformClassVisitor extends ClassRemapper {
    private final TransformRemapper remapper;

    public TransformClassVisitor(ClassVisitor classVisitor, TransformRemapper remapper) {
      super(Opcodes.ASM9, classVisitor, remapper);
      this.remapper = remapper;
    }

    @Override
    protected MethodVisitor createMethodRemapper(MethodVisitor methodVisitor) {
      return new TransformMethodRemapper(methodVisitor, remapper);
    }
  }

  private static class TransformMethodRemapper extends MethodRemapper {
    private final TransformRemapper remapper;

    public TransformMethodRemapper(MethodVisitor methodVisitor, TransformRemapper remapper) {
      super(Opcodes.ASM9, methodVisitor, remapper);
      this.remapper = remapper;
    }

    private void visitThrow(String message) {
      super.visitLdcInsn(message);
      super.visitMethodInsn(Opcodes.INVOKESTATIC, "bot/inker/bukkit/nbt/loader/CallSiteNbt$Spy", "throwException", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
      if (!owner.startsWith(CSN_REF_PACKAGE_INTERNAL)) {
        super.visitFieldInsn(opcode, owner, name, descriptor);
        return;
      }
      HandleBy handleBy = remapper.fetchField(owner, name, descriptor);
      if (handleBy == null || handleBy.reference().isEmpty()) {
        visitThrow("field L" + owner + ";" + name + ":" + descriptor + " not available in this minecraft version");
        super.visitFieldInsn(opcode, owner, name, descriptor);
        return;
      }
      String[] reference = parseField(handleBy.reference());
      super.visitFieldInsn(opcode, reference[0], reference[1], reference[2]);
    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
      if (!owner.startsWith(CSN_REF_PACKAGE_INTERNAL)) {
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
        return;
      }
      int source = opcodeAndSource & Opcodes.SOURCE_MASK;
      int opcode = opcodeAndSource & ~Opcodes.SOURCE_MASK;

      HandleBy handleBy = remapper.fetchMethod(owner, name, descriptor);
      if (handleBy == null || handleBy.reference().isEmpty()) {
        visitThrow("method L" + owner + ";" + name + descriptor + " not available in this minecraft version");
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
        return;
      }
      if (handleBy.isInterface()) {
        opcode = Opcodes.INVOKEINTERFACE;
      }
      String[] reference = parseMethod(handleBy.reference());
      if (handleBy.accessor()) {
        super.visitInvokeDynamicInsn(
            reference[1],
            (opcode == Opcodes.INVOKESTATIC) ? descriptor : ("(L" + owner + ";" + descriptor.substring(1)),
            new Handle(
                Opcodes.H_INVOKESTATIC,
                "bot/inker/bukkit/nbt/loader/CallSiteNbt$Spy",
                "bootstrap",
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;ILjava/lang/String;Ljava/lang/String;)Ljava/lang/invoke/CallSite;",
                false),
            opcode,
            reference[0],
            reference[2]
        );
      } else {
        super.visitMethodInsn(opcode | source, reference[0], reference[1], reference[2], handleBy.isInterface());
      }
    }
  }

  public static class Spy {
    public static CallSite bootstrap(
        MethodHandles.Lookup callerLookup,
        String name,
        MethodType type,
        int opcode,
        String owner,
        String describe
    ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
      Class<?> caller = callerLookup.lookupClass();
      ClassLoader classLoader = caller.getClassLoader();
      Class<?> ownerClass = Class.forName(owner.replace('/', '.'), false, classLoader);
      Type targetType = Type.getMethodType(describe);
      Type[] targetArgumentTypes = targetType.getArgumentTypes();
      Class<?>[] targetArgumentClasses = new Class[targetArgumentTypes.length];
      for (int i = 0; i < targetArgumentTypes.length; i++) {
        targetArgumentClasses[i] = typeToClass(classLoader, targetArgumentTypes[i]);
      }
      MethodType targetMethodType = MethodType.methodType(
          typeToClass(classLoader, targetType.getReturnType()),
          targetArgumentClasses
      );
      MethodHandle handle;
      if (opcode == Opcodes.INVOKESTATIC) {
        handle = lookup.findStatic(ownerClass, name, targetMethodType).asType(type);
      } else if (opcode == Opcodes.INVOKESPECIAL) {
        handle = lookup.findSpecial(ownerClass, name, targetMethodType, caller).asType(type);
      } else if (opcode == Opcodes.INVOKEVIRTUAL || opcode == Opcodes.INVOKEINTERFACE) {
        handle = lookup.findVirtual(ownerClass, name, targetMethodType).asType(type);
      } else {
        throw new IllegalStateException("Unsupported invokeMethod opcode: " + opcode);
      }
      return new ConstantCallSite(handle);
    }

    public static void throwException(String message) {
      throw new IllegalStateException(message);
    }
  }
}