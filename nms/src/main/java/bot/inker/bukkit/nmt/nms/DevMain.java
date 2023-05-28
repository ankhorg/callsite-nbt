package bot.inker.bukkit.nmt.nms;

import net.minecraft.server.v1_12_R1.*;

public class DevMain {
  private static String parseClass(String reference) {
    String matchedPrefix;
    if(reference.startsWith("org/bukkit/craftbukkit/")){
      matchedPrefix = "org/bukkit/craftbukkit/";
    } else if (reference.startsWith("net/minecraft/server/")) {
      matchedPrefix = "net/minecraft/server/";
    } else {
      return reference;
    }
    int splitIndex = reference.indexOf('/', matchedPrefix.length());
    return matchedPrefix + "TTT" + reference.substring(splitIndex);
  }

  public static void main(String[] args) {
    System.out.println(parseClass("net/minecraft/server/v1_16_R3/NBTTagByte"));
  }
}
