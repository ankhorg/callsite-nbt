package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagDouble")
public final class RefNbtTagDouble extends RefNbtNumber {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagDouble;<init>(D)V")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public RefNbtTagDouble(double value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagDouble;a(D)Lnet/minecraft/server/v1_16_R3/NBTTagDouble;")
  public static native RefNbtTagDouble of(double value);
}
