package bot.inker.bukkit;

import java.util.StringJoiner;

public class NmsMain {
  public static void main(String[] args) {
    StringJoiner joiner = new StringJoiner(", ");
    for (char c : "bot.inker.bukkit.nbt.loader.CallSiteNbt".toCharArray()) {
      joiner.add("'" + c + "'");
    }
    System.out.println(joiner.toString());
  }
}
