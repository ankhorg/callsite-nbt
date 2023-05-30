package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagShort")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/ShortTag", remap = true)
public final class RefNbtTagShort extends RefNbtNumber {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagShort;<init>(S)V")
  @HandleBy(version = CbVersion.v1_15_R1, reference = "")
  public RefNbtTagShort(short value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_15_R1/NBTTagShort;a(S)Lnet/minecraft/server/v1_15_R1/NBTTagShort;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ShortTag;valueOf(S)Lnet/minecraft/nbt/ShortTag;", remap = true)
  public static native RefNbtTagShort of(short value);
}
