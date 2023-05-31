package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;
import bot.inker.bukkit.nbtbuild.util.RemapUtils;
import org.objectweb.asm.Type;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReverseMap extends AbstractMap {
  public ReverseMap(Map<String, ClassNode> classNodeMap) {
    super(classNodeMap);
  }

  public static ReverseMap create(AbstractMap map) {
    Map<String, ClassNode> inputMap = map.classNodeMap();
    Map<String, String> classMap = new LinkedHashMap<>();
    Map<String, ClassNode> classNodeMap = new LinkedHashMap<>();
    for (ClassNode node : inputMap.values()) {
      classMap.put(node.raw(), node.remapped());
    }
    for (ClassNode inputNode : inputMap.values()) {
      ClassNode node = classNodeMap.computeIfAbsent(inputNode.remapped(), it -> new ClassNode(inputNode.remapped(), inputNode.raw()));
      for (Map.Entry<FieldNode, String> inputEntry : inputNode.fieldMap().entrySet()) {
        node.fieldMap().put(new FieldNode(
            inputNode.remapped(),
            inputEntry.getValue(),
            RemapUtils.mapType(classMap, Type.getType(inputEntry.getKey().desc())).getDescriptor()
        ), inputEntry.getKey().name());
      }
      for (Map.Entry<MethodNode, String> inputEntry : inputNode.methodMap().entrySet()) {
        node.methodMap().put(new MethodNode(
            inputNode.remapped(),
            inputEntry.getValue(),
            RemapUtils.mapType(classMap, Type.getMethodType(inputEntry.getKey().desc())).getDescriptor()
        ), inputEntry.getKey().name());
      }
    }
    return new ReverseMap(classNodeMap);
  }
}
