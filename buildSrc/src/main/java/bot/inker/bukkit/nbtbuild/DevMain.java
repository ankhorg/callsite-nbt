package bot.inker.bukkit.nbtbuild;

import bot.inker.bukkit.nbtbuild.maps.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class DevMain {
  private static final Logger logger = LoggerFactory.getLogger("mappingGenerator");

  public static void run(List<SpigotBuildInfo> buildInfos, File outputClassRoot) throws IOException {
    Map<String, String> resultMap = new LinkedHashMap<>();
    for (SpigotBuildInfo buildInfo : buildInfos) {
      logger.warn("Building remapping for {}", buildInfo.craftBukkitVersion());
      AbstractMap proguardMap;
      try (BufferedReader reader = createOrNull(buildInfo.mojang())) {
        proguardMap = ProguardMap.create(reader);
      }
      AbstractMap spigotSrgMap;
      try (BufferedReader classReader = createOrNull(buildInfo.bukkitCl())) {
        try (BufferedReader membersReader = createOrNull(buildInfo.bukkitMembers())) {
          spigotSrgMap = SpigotSrgMap.create(classReader, membersReader);
        }
      }
      AbstractMap spigot2proguard = ContactMap.create(
          ClassFilterMap.create(spigotSrgMap),
          ClassFilterMap.create(ReverseMap.create(proguardMap))
      );
      AbstractMap result = ContactMap.create(
          proguardMap, // proguard -> raw
          ReverseMap.create(spigotSrgMap), // raw -> spigot
          ReverseMap.create(proguardMap) // raw -> proguard
      );

      File proguardOutputDir = new File("build/tmp/" + buildInfo.craftBukkitVersion());
      proguardOutputDir.mkdirs();
      try (Writer writer = new FileWriter(new File(proguardOutputDir, "result-1.txt"))) {
        new ProguardMap(result.classNodeMap()).write(writer);
      }

      StringWriter stringWriter = new StringWriter();
      try (OutputStream out = new GZIPOutputStream(
          Base64.getEncoder().wrap(new WriterOutputStream(stringWriter))
      )) {
        new BlobMap(result.classNodeMap()).write(out);
      }
      resultMap.put(buildInfo.craftBukkitVersion(), stringWriter.toString());
    }

    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    cw.visit(
        Opcodes.V1_8,
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
        "bot/inker/bukkit/nbt/internal/loader/ConstMappings",
        null,
        "java/lang/Object",
        null
    );
    for (Map.Entry<String, String> entry : resultMap.entrySet()) {
      FieldVisitor fv = cw.visitField(
          Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
          entry.getKey(), "Ljava/lang/String;",
          null,
          (entry.getValue().length() > 65535) ? null : entry.getValue()
      );
      fv.visitEnd();
    }
    {
      MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
      for (Map.Entry<String, String> entry : resultMap.entrySet()) {
        if (entry.getValue().length() <= 65535) {
          continue;
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "bot/inker/bukkit/nbt/internal/loader/ConstMappings", entry.getKey(), "()Ljava/lang/String;", false);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, "bot/inker/bukkit/nbt/internal/loader/ConstMappings", entry.getKey(), "Ljava/lang/String;");
      }
      mv.visitInsn(Opcodes.RETURN);
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    for (Map.Entry<String, String> entry : resultMap.entrySet()) {
      if (entry.getValue().length() <= 65535) {
        continue;
      }
      MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC, entry.getKey(), "()Ljava/lang/String;", null, null);
      mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuffer");
      mv.visitInsn(Opcodes.DUP);
      mv.visitLdcInsn(entry.getValue().length());
      mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuffer", "<init>", "(I)V", false);
      int currentPos = 0;
      while (true) {
        if ((entry.getValue().length() - currentPos) > 65535) {
          mv.visitLdcInsn(entry.getValue().substring(currentPos, currentPos + 65535));
          mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;", false);
          currentPos += 65535;
        } else {
          mv.visitLdcInsn(entry.getValue().substring(currentPos));
          mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;", false);
          break;
        }
      }
      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;", false);
      mv.visitInsn(Opcodes.ARETURN);
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    cw.visitEnd();
    File outputClass = new File(outputClassRoot, "bot/inker/bukkit/nbt/internal/loader/ConstMappings.class");
    outputClass.getParentFile().mkdirs();
    try (OutputStream out = new FileOutputStream(outputClass)) {
      out.write(cw.toByteArray());
    }
  }

  private static BufferedReader createOrNull(String url) throws IOException {
    if (url == null) {
      return null;
    }
    return new BufferedReader(new InputStreamReader(new URL(url).openStream()));
  }

  private static class WriterOutputStream extends OutputStream {
    private final Writer writer;
    private char[] buf = new char[0];

    private WriterOutputStream(Writer writer) {
      this.writer = writer;
    }

    @Override
    public void write(int b) throws IOException {
      if (buf.length < 1) {
        buf = new char[1];
      }
      buf[0] = (char) b;
      writer.write(buf, 0, 1);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      if (buf.length < len) {
        buf = new char[len];
      }
      for (int i = 0; i < len; i++) {
        buf[i] = (char) b[off + i];
      }
      writer.write(buf, 0, len);
    }

    @Override
    public void flush() throws IOException {
      writer.flush();
    }

    @Override
    public void close() throws IOException {
      writer.close();
    }
  }
}
