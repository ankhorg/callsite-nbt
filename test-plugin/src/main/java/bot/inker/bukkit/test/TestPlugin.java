package bot.inker.bukkit.test;

import bot.inker.bukkit.nbt.loader.CallSiteNbt;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TestPlugin extends JavaPlugin {
  private static final Random testRandom;

  static {
    CallSiteNbt.install(TestPlugin.class);
    testRandom = new Random();
  }

  @Override
  public void onEnable() {
    new TestTargets().run();
  }
}
