package bot.inker.bukkit.nbt.loader;

import bot.inker.bukkit.nbt.internal.loader.CallSiteInstaller;

public class CallSiteNbt {
  private CallSiteNbt() {
    throw new UnsupportedOperationException();
  }

  public static void install(Class<?> clazz) {
    CallSiteInstaller.install(clazz);
  }
}