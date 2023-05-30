package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagFloat")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/FloatTag", remap = true)
public final class RefNbtTagFloat extends RefNbtNumber {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagFloat;<init>(F)V")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public RefNbtTagFloat(float value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_15_R1/NBTTagFloat;a(F)Lnet/minecraft/server/v1_15_R1/NBTTagFloat;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/FloatTag;valueOf(F)Lnet/minecraft/nbt/FloatTag;", remap = true)
  public static native RefNbtTagFloat of(float value);
}
