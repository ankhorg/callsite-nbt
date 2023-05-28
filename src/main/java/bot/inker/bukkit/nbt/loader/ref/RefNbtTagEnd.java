package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

public class RefNbtTagEnd implements RefNbtBase {
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagEnd;b:Lnet/minecraft/server/v1_16_R3/NBTTagEnd;")
  public static final RefNbtTagEnd INSTANCE = null;

  private RefNbtTagEnd() {
    throw new UnsupportedOperationException();
  }
}
