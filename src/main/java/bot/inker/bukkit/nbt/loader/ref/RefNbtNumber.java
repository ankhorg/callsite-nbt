package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTBase", accessor = true)
@HandleBy(version = CbVersion.v1_13_R2, reference = "net/minecraft/server/v1_13_R2/NBTNumber")
public abstract class RefNbtNumber extends RefNbtBase {
  RefNbtNumber() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;d()J", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asLong()J")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsLong()J", remap = true)
  public native long asLong();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;e()I", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asInt()I")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsInt()I", remap = true)
  public native int asInt();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;f()S", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asShort()S")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsShort()S", remap = true)
  public native short asShort();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;g()B", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asByte()B")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsByte()B", remap = true)
  public native byte asByte();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;asDouble()D", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asDouble()D")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsDouble()D", remap = true)
  public native double asDouble();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTNumber;i()F", accessor = true)
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;asFloat()F")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsFloat()F", remap = true)
  public native float asFloat();

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTNumber;j()Ljava/lang/Number;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/NumericTag;getAsNumber()Ljava/lang/Number;", remap = true)
  public native Number asNumber();
}
