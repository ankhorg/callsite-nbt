package bot.inker.bukkit.nbt.loader;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import org.objectweb.asm.Type;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.MethodRemapper;
import org.objectweb.asm.commons.Remapper;

import java.io.*;
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
        loaded = true;
      } catch (ReflectiveOperationException | IOException e) {
        throw new IllegalStateException("Failed to install CallSiteNbt", e);
      }
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

    transformRemapper = new TransformRemapper(new RefOnlyClassLoader(classLoader, pluginJar));
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
        if(internalName.startsWith("org/bukkit/craftbukkit/")){
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
      if(handleBy == null || handleBy.reference().isEmpty()){
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
      if(handleBy == null || handleBy.reference().isEmpty()){
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
      if(handleBy == null || handleBy.reference().isEmpty()){
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
      if(handleBy == null || handleBy.reference().isEmpty()){
        visitThrow("field L" + owner + ";" +  name + ":" + descriptor + " not available in this minecraft version");
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
      if(handleBy == null || handleBy.reference().isEmpty()){
        visitThrow("method L" + owner + ";" +  name + descriptor + " not available in this minecraft version");
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
        return;
      }
      if (handleBy.isInterface()) {
        opcode = Opcodes.INVOKEINTERFACE;
      }
      String[] reference = parseMethod(handleBy.reference());
      super.visitMethodInsn(opcode | source, reference[0], reference[1], reference[2], handleBy.isInterface());
    }
  }
  public static class Spy {
    public static void throwException(String message){
      throw new IllegalStateException(message);
    }
  }
}