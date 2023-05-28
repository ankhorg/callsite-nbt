package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagFloat")
public final class RefNbtTagFloat extends RefNbtNumber {
  private RefNbtTagFloat(){
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagFloat;a(F)Lnet/minecraft/server/v1_16_R3/NBTTagFloat;")
  public static native RefNbtTagFloat of(float value);
}
