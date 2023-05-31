package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;

import java.util.Map;

public abstract class AbstractMap implements IMap {
  private final Map<String, ClassNode> classNodeMap;

  protected AbstractMap(Map<String, ClassNode> classNodeMap) {
    this.classNodeMap = classNodeMap;
  }

  public Map<String, ClassNode> classNodeMap() {
    return classNodeMap;
  }

  @Override
  public String map(String internalName) {
    ClassNode classNode = classNodeMap.get(internalName);
    return classNode == null ? internalName : classNode.remapped();
  }

  @Override
  public String mapMethodName(String owner, String name, String descriptor) {
    ClassNode classNode = classNodeMap.get(owner);
    if (classNode == null) {
      return name;
    }
    String remapped = classNode.methodMap().get(new MethodNode(owner, name, descriptor));
    return remapped == null ? name : remapped;
  }

  public String mapFieldName(String owner, String name, String descriptor) {
    ClassNode classNode = classNodeMap.get(owner);
    if (classNode == null) {
      return name;
    }
    String remapped = classNode.fieldMap().get(new FieldNode(owner, name, descriptor));
    return remapped == null ? name : remapped;
  }
}
