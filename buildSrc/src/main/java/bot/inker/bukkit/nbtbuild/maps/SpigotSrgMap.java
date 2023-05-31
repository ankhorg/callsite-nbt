package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpigotSrgMap extends AbstractMap {
  public SpigotSrgMap(Map<String, ClassNode> map) {
    super(map);
  }

  public static SpigotSrgMap create(
      BufferedReader classReader,
      BufferedReader memberReader
  ) throws IOException {
    String line;
    Map<String, String> classMap = new LinkedHashMap<>();
    Map<String, ClassNode> classNodeMap = new LinkedHashMap<>();
    if (classReader != null) {
      while ((line = classReader.readLine()) != null) {
        if (line.startsWith("#")) {
          continue;
        }
        String[] lineEntry = StringUtils.split(line, ' ');
        classMap.put(lineEntry[1], lineEntry[0]);
        classNodeMap.put(lineEntry[1], new ClassNode(lineEntry[1], lineEntry[0]));
      }
    }

    if (memberReader != null) {
      while ((line = memberReader.readLine()) != null) {
        if (line.startsWith("#")) {
          continue;
        }
        String[] lineEntry = StringUtils.split(line, ' ');
        String owner = lineEntry[0];
        ClassNode ownerClassNode = classNodeMap.computeIfAbsent(owner, it -> new ClassNode(
            owner, classMap.getOrDefault(owner, owner)
        ));
        String desc = lineEntry[2];
        ownerClassNode.methodMap().put(
            new MethodNode(owner, lineEntry[3], desc),
            lineEntry[1]
        );
      }
    }
    return new SpigotSrgMap(classNodeMap);
  }
}
