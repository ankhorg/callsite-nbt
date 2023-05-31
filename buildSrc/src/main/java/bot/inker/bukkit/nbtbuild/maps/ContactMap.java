package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactMap extends AbstractMap {

  protected ContactMap(Map<String, ClassNode> classNodeMap) {
    super(classNodeMap);
  }

  public static ContactMap create(IMap... maps) {
    Map<String, ClassNode> inputMap = ((AbstractMap) maps[0]).classNodeMap();
    Map<String, String> classMap = new LinkedHashMap<>();
    Map<String, ClassNode> classNodeMap = new LinkedHashMap<>();

    for (ClassNode inputNode : inputMap.values()) {
      String internalName = inputNode.raw();
      for (IMap map : maps) {
        internalName = map.map(internalName);
      }
      classMap.put(inputNode.raw(), internalName);
    }

    for (ClassNode inputNode : inputMap.values()) {
      String remappedType = classMap.get(inputNode.raw());
      ClassNode classNode = classNodeMap.computeIfAbsent(inputNode.raw(), it -> new ClassNode(inputNode.raw(), remappedType));

      for (Map.Entry<FieldNode, String> inputEntry : inputNode.fieldMap().entrySet()) {
        FieldNode currentFieldNode = inputEntry.getKey();
        for (IMap map : maps) {
          currentFieldNode = map.mapField(currentFieldNode);
        }
        classNode.fieldMap().put(inputEntry.getKey(), currentFieldNode.name());
      }

      for (Map.Entry<MethodNode, String> inputEntry : inputNode.methodMap().entrySet()) {
        MethodNode currentMethodNode = inputEntry.getKey();
        for (IMap map : maps) {
          currentMethodNode = map.mapMethod(currentMethodNode);
        }
        classNode.methodMap().put(inputEntry.getKey(), currentMethodNode.name());
      }
    }

    return new ContactMap(classNodeMap);
  }
}
