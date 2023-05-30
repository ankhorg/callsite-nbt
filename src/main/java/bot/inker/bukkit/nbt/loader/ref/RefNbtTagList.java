package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagList")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/ListTag", remap = true)
public final class RefNbtTagList extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;<init>()V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;get(I)Lnet/minecraft/nbt/Tag;", remap = true)
  public RefNbtTagList() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;i(I)Lnet/minecraft/server/v1_12_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagList;c(I)Lnet/minecraft/server/v1_13_R2/NBTBase;")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_14_R1/NBTTagList;get(I)Lnet/minecraft/server/v1_14_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;get(I)Lnet/minecraft/nbt/Tag;", remap = true)
  public native RefNbtBase get(int index);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;size()I")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;size()I", remap = true)
  public native int size();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;a(ILnet/minecraft/server/v1_12_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "")
  public native void set0(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagList;set(ILnet/minecraft/server/v1_13_R2/NBTBase;)Lnet/minecraft/server/v1_13_R2/NBTBase;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;set(ILnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;", remap = true)
  public native RefNbtBase set1(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;setTag(ILnet/minecraft/nbt/Tag;)Z", remap = true)
  public native boolean set2(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;add(Lnet/minecraft/server/v1_12_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "")
  public native void add0(RefNbtBase element);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagList;add(Lnet/minecraft/server/v1_13_R2/NBTBase;)Z")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "")
  public native boolean add1(RefNbtBase element);

  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_14_R1/NBTTagList;add(ILnet/minecraft/server/v1_14_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/ListTag;add(ILnet/minecraft/nbt/Tag;)V", remap = true)
  public native void add2(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;remove(I)Lnet/minecraft/server/v1_12_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "", remap = true)
  public native RefNbtBase remove(int index);
}