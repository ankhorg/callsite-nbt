package bot.inker.bukkit.test;

import bot.inker.bukkit.nbt.loader.CallSiteNbt;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TestPlugin extends JavaPlugin {
  private static final boolean isCallSiteNbtShaded;
  private static final Random testRandom;

  static {
    String currentCallSiteNbtName = "bot.inker.bukkit.nbt.loader.CallSiteNbt";
    String realCallSiteNbtName = new String(new byte[]{'b', 'o', 't', '.', 'i', 'n', 'k', 'e', 'r', '.', 'b', 'u', 'k', 'k', 'i', 't', '.', 'n', 'b', 't', '.', 'l', 'o', 'a', 'd', 'e', 'r', '.', 'C', 'a', 'l', 'l', 'S', 'i', 't', 'e', 'N', 'b', 't'});
    isCallSiteNbtShaded = !currentCallSiteNbtName.equals(realCallSiteNbtName);
    if (true || isCallSiteNbtShaded) {
      System.out.println("===== TestPlugin used shadowed CallSiteNbt");
      Exception exception = null;
      try {
        CallSiteNbt.install(TestPlugin.class);
      } catch (Exception e) {
        exception = e;
      }
      if (exception == null) {
        System.out.println("===== install " + currentCallSiteNbtName);
      } else {
        System.out.println("===== failed install " + currentCallSiteNbtName);
        exception.printStackTrace();
      }
    } else {
      System.out.println("===== TestPlugin used single CallSiteNbt");
    }

    testRandom = new Random();
  }

  @Override
  public void onEnable() {
    new TestTargets().run();
  }
}
