package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;
import org.objectweb.asm.Type;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlobMap extends AbstractMap {
  private final Map<String, Integer> constPool = new LinkedHashMap<>();
  private final List<String> constPoolIndex = new ArrayList<>();

  public BlobMap(Map<String, ClassNode> classNodeMap) {
    super(classNodeMap);
  }

  private static boolean isAcceptClass(String name) {
    return name.startsWith("net/minecraft/nbt/")
            || name.startsWith("net/minecraft/world/item/ItemStack")
            || name.startsWith("net/minecraft/world/entity/Entity")
            || name.startsWith("net/minecraft/world/level/Level")
            || name.startsWith("net/minecraft/server/level/ServerLevel")
            || name.startsWith("net/minecraft/world/entity/item/ItemEntity");
  }
  private void buildConstPool() {
    for (Map.Entry<String, ClassNode> classEntry : classNodeMap().entrySet()) {
      if (!isAcceptClass(classEntry.getValue().raw())) {
        continue;
      }
      registerConstPool(classEntry.getValue().raw());
      registerConstPool(classEntry.getValue().remapped());

      for (Map.Entry<FieldNode, String> fieldEntry : classEntry.getValue().fieldMap().entrySet()) {
        registerConstPool(
            fieldEntry.getKey().name(),
            fieldEntry.getValue(),
            fieldEntry.getKey().desc()
        );
      }

      for (Map.Entry<MethodNode, String> methodEntry : classEntry.getValue().methodMap().entrySet()) {
        registerConstPool(
            methodEntry.getKey().name(),
            methodEntry.getValue()
        );
        Type methodType = Type.getMethodType(methodEntry.getKey().desc());
        registerConstPool(methodType.getReturnType().getDescriptor());
        for (Type argumentType : methodType.getArgumentTypes()) {
          registerConstPool(argumentType.getDescriptor());
        }
      }
    }
  }

  private void registerConstPool(String... contents) {
    for (String content : contents) {
      if (constPool.containsKey(content)) {
        continue;
      }
      int id = constPoolIndex.size();
      constPool.put(content, id);
      constPoolIndex.add(content);
    }
  }

  private int getConstPool(String content) {
    return constPool.get(content);
  }

  public void write(OutputStream outputStream) throws IOException {
    if (constPoolIndex.isEmpty()) {
      buildConstPool();
    }

    DataOutputStream out = new DataOutputStream(outputStream);

    out.writeInt(constPoolIndex.size());
    for (String content : constPoolIndex) {
      out.writeUTF(content);
    }

    out.writeInt((int) classNodeMap().entrySet()
        .stream()
        .filter(it -> isAcceptClass(it.getValue().raw()))
        .count());
    for (Map.Entry<String, ClassNode> classEntry : classNodeMap().entrySet()) {
      if (!isAcceptClass(classEntry.getValue().raw())) {
        continue;
      }
      out.writeInt(getConstPool(classEntry.getValue().raw()));
      out.writeInt(getConstPool(classEntry.getValue().remapped()));
    }
    for (Map.Entry<String, ClassNode> classEntry : classNodeMap().entrySet()) {
      if (!isAcceptClass(classEntry.getValue().raw())) {
        continue;
      }
      out.writeInt(classEntry.getValue().fieldMap().size());
      for (Map.Entry<FieldNode, String> fieldEntry : classEntry.getValue().fieldMap().entrySet()) {
        out.writeInt(getConstPool(fieldEntry.getKey().name()));
        out.writeInt(getConstPool(fieldEntry.getKey().desc()));
        out.writeInt(getConstPool(fieldEntry.getValue()));
      }

      out.writeInt(classEntry.getValue().methodMap().size());
      for (Map.Entry<MethodNode, String> methodEntry : classEntry.getValue().methodMap().entrySet()) {
        out.writeInt(getConstPool(methodEntry.getKey().name()));
        Type methodType = Type.getMethodType(methodEntry.getKey().desc());
        out.writeInt(getConstPool(methodType.getReturnType().getDescriptor()));
        Type[] argumentTypes = methodType.getArgumentTypes();
        out.writeByte(argumentTypes.length);
        for (Type argedType : argumentTypes) {
          out.writeInt(getConstPool(argedType.getDescriptor()));
        }
        out.writeInt(getConstPool(methodEntry.getValue()));
      }
    }

    out.flush();
  }
}
