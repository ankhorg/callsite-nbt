package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTBase")
@HandleBy(version = CbVersion.v1_13_R2, reference = "net/minecraft/server/v1_13_R2/NBTBase", isInterface = true)
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/Tag", isInterface = true, remap = true)
public abstract class RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTBase;createTag(B)Lnet/minecraft/server/v1_12_R1/NBTBase;", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTBase;createTag(B)Lnet/minecraft/server/v1_13_R2/NBTBase;", isInterface = true)
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public static native RefNbtBase createTag(byte typeId);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTBase;getTypeId()B")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTBase;getTypeId()B", isInterface = true)
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/Tag;getId()B", isInterface = true, remap = true)
  public native byte getTypeId();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTBase;clone()Lnet/minecraft/server/v1_12_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTBase;clone()Lnet/minecraft/server/v1_13_R2/NBTBase;", isInterface = true)
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/Tag;copy()Lnet/minecraft/nbt/Tag;", isInterface = true, remap = true)
  public native RefNbtBase rClone();
}
