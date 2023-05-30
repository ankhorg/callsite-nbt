package bot.inker.bukkit.nbtbuild;

import java.util.Map;

public class FetchBuildInfo {
  public static void main(String[] args) {
    for (Map.Entry<String, SpigotBuildInfo> entry : SpigotBuildInfo.BUILD_INFO_MAP.entrySet()) {
      String craftBukkitVersion = entry.getKey();
      SpigotBuildInfo buildInfo = entry.getValue();


    }
  }
}
