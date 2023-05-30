package bot.inker.bukkit.nbtbuild;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpigotBuildInfo {
  public static final Map<String, SpigotBuildInfo> BUILD_INFO_MAP;

  static {
    Map<String, SpigotBuildInfo> buildInfoMap = new LinkedHashMap<>();
    buildInfoMap.put("v1_17_R1", new SpigotBuildInfo(
        "https://launcher.mojang.com/v1/objects/f6cae1c5c1255f68ba4834b16a0da6a09621fe13/server.txt",
        "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-cl.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03",
        "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-members.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03"
    ));
    BUILD_INFO_MAP = Collections.unmodifiableMap(buildInfoMap);
  }

  private final String mojang;
  private final String bukkitCl;
  private final String bukkitMembers;

  public SpigotBuildInfo(String mojang, String bukkitCl, String bukkitMembers) {
    this.mojang = mojang;
    this.bukkitCl = bukkitCl;
    this.bukkitMembers = bukkitMembers;
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
