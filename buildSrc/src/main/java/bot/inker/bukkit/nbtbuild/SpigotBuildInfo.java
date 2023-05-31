package bot.inker.bukkit.nbtbuild;

import java.io.Serializable;

public class SpigotBuildInfo implements Serializable {
  private final String craftBukkitVersion;
  private final String mojang;
  private final String bukkitCl;
  private final String bukkitMembers;

  public SpigotBuildInfo(String craftBukkitVersion, String mojang, String bukkitCl, String bukkitMembers) {
    this.craftBukkitVersion = craftBukkitVersion;
    this.mojang = mojang;
    this.bukkitCl = bukkitCl;
    this.bukkitMembers = bukkitMembers;
  }

  public String craftBukkitVersion() {
    return craftBukkitVersion;
  }

  public String mojang() {
    return mojang;
  }

  public String bukkitCl() {
    return bukkitCl;
  }

  public String bukkitMembers() {
    return bukkitMembers;
  }
}
