package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagLong")
public final class RefNbtTagLong extends RefNbtNumber {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagLong;<init>(J)V")
  @HandleBy(version = CbVersion.v1_15_R1, reference = "")
  public RefNbtTagLong(long value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_15_R1/NBTTagLong;a(J)Lnet/minecraft/server/v1_15_R1/NBTTagLong;")
  public static native RefNbtTagLong of(long value);
}
