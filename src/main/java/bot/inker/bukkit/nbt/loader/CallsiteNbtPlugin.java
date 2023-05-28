package bot.inker.bukkit.nbt.loader;

import bot.inker.bukkit.nbt.NbtByte;
import bot.inker.bukkit.nbt.NbtByteArray;
import org.bukkit.plugin.java.JavaPlugin;

public class CallsiteNbtPlugin extends JavaPlugin {
  static {
    CallSiteNbt.install(CallsiteNbtPlugin.class);
  }

  @Override
  public void onEnable() {
    NbtByte.valueOf((byte) 123);
    new NbtByteArray(new byte[]{1, 2, 3, 4, 5});
  }
}
