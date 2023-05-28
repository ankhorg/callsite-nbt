package bot.inker.bukkit.nbt.loader;

import org.bukkit.plugin.java.JavaPlugin;

public class CallsiteNbtPlugin extends JavaPlugin {
  static {
    CallSiteNbt.install(CallsiteNbtPlugin.class);
  }

  @Override
  public void onEnable() {
    System.out.println();
  }
}
