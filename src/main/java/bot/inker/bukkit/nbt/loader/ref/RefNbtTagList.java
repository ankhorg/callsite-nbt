package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagList")
public final class RefNbtTagList extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;<init>()V")
  public RefNbtTagList() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;i(I)Lnet/minecraft/server/v1_12_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagList;get(I)Lnet/minecraft/server/v1_16_R3/NBTBase;")
  public native RefNbtBase get(int index);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;size()I")
  public native int size();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;a(ILnet/minecraft/server/v1_12_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public native void set0(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagList;set(ILnet/minecraft/server/v1_16_R3/NBTBase;)Lnet/minecraft/server/v1_16_R3/NBTBase;")
  public native RefNbtBase set1(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;add(Lnet/minecraft/server/v1_12_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "")
  public native void add0(RefNbtBase element);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagList;add(ILnet/minecraft/server/v1_16_R3/NBTBase;)V")
  public native void add1(int index, RefNbtBase element);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagList;remove(I)Lnet/minecraft/server/v1_12_R1/NBTBase;")
  public native RefNbtBase remove(int index);
}