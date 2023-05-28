package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagByte")
public final class RefNbtTagByte extends RefNbtNumber {
  private RefNbtTagByte() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByte;a(B)Lnet/minecraft/server/v1_16_R3/NBTTagByte;")
  public static native RefNbtTagByte of(byte value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByte;a(Z)Lnet/minecraft/server/v1_16_R3/NBTTagByte;")
  public static native RefNbtTagByte of(boolean value);
}
