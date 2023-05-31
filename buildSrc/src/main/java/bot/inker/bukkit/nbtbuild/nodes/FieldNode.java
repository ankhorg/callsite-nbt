package bot.inker.bukkit.nbtbuild.nodes;

import java.util.Objects;

public final class FieldNode {
  private final String owner;
  private final String name;
  private final String desc;

  public FieldNode(String owner, String name, String desc) {
    Objects.requireNonNull(owner);
    Objects.requireNonNull(name);
    Objects.requireNonNull(desc);

    this.owner = owner;
    this.name = name;
    this.desc = desc;
  }

  public String owner() {
    return owner;
  }

  public String name() {
    return name;
  }

  public String desc() {
    return desc;
  }

  @Override
  public String toString() {
    return name + ":" + desc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FieldNode fieldNode = (FieldNode) o;

    if (!owner.equals(fieldNode.owner)) return false;
    if (!name.equals(fieldNode.name)) return false;
    return desc.equals(fieldNode.desc);
  }

  @Override
  public int hashCode() {
    int result = owner.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + desc.hashCode();
    return result;
  }
}
