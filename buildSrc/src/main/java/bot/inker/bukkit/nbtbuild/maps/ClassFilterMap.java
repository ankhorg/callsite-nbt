package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassFilterMap extends AbstractMap {
  protected ClassFilterMap(Map<String, ClassNode> classNodeMap) {
    super(classNodeMap);
  }

  public static ClassFilterMap create(AbstractMap map) {
    Map<String, ClassNode> classNodeMap = new LinkedHashMap<>();
    for (Map.Entry<String, ClassNode> classNodeEntry : map.classNodeMap().entrySet()) {
      classNodeMap.put(classNodeEntry.getKey(), new ClassNode(
          classNodeEntry.getValue().raw(),
          classNodeEntry.getValue().remapped()
      ));
    }
    return new ClassFilterMap(classNodeMap);
  }
}
