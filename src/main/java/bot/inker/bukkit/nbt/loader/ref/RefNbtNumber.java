package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTNumber")
public abstract class RefNbtNumber implements RefNbtBase {
  RefNbtNumber(){
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asLong()J")
  public native long asLong();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asInt()I")
  public native int asInt();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asShort()S")
  public native short asShort();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asByte()B")
  public native byte asByte();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asDouble()D")
  public native double asDouble();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;asFloat()F")
  public native float asFloat();

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTNumber;k()Ljava/lang/Number;")
  public native Number asNumber();
}
