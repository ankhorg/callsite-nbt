package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagByte")
public final class RefNbtTagByte extends RefNbtNumber {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagByte;<init>(B)V")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public RefNbtTagByte(byte value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByte;a(B)Lnet/minecraft/server/v1_16_R3/NBTTagByte;")
  public static native RefNbtTagByte of(byte value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByte;a(Z)Lnet/minecraft/server/v1_16_R3/NBTTagByte;")
  public static native RefNbtTagByte of(boolean value);
}
