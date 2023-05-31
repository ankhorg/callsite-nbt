package bot.inker.bukkit.nbtbuild.nodes;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ClassNode {
  private final String raw;
  private final String remapped;

  private final Map<FieldNode, String> fieldMap;
  private final Map<MethodNode, String> methodMap;

  public ClassNode(String raw, String remapped) {
    this.raw = raw;
    this.remapped = remapped;
    this.fieldMap = new LinkedHashMap<>();
    this.methodMap = new LinkedHashMap<>();
  }

  public String raw() {
    return raw;
  }

  public String remapped() {
    return remapped;
  }

  public Map<FieldNode, String> fieldMap() {
    return fieldMap;
  }

  public Map<MethodNode, String> methodMap() {
    return methodMap;
  }

  @Override
  public String toString() {
    return raw + " -> " + remapped;
  }
}
