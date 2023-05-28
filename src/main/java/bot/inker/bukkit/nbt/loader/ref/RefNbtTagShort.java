package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagShort")
public final class RefNbtTagShort extends RefNbtNumber {
  private RefNbtTagShort() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagLong;a(J)Lnet/minecraft/server/v1_16_R3/NBTTagLong;")
  public static native RefNbtTagShort of(short value);
}
