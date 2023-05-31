package bot.inker.bukkit.nbtbuild.nodes;

public final class MethodNode {
  private final String owner;
  private final String name;
  private final String desc;

  public MethodNode(String owner, String name, String desc) {
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
    return name + desc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MethodNode that = (MethodNode) o;

    if (!owner.equals(that.owner)) return false;
    if (!name.equals(that.name)) return false;
    return desc.equals(that.desc);
  }

  @Override
  public int hashCode() {
    int result = owner.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + desc.hashCode();
    return result;
  }
}
