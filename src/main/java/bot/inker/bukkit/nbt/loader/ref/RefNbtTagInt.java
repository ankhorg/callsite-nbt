package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagInt")
public final class RefNbtTagInt extends RefNbtNumber {
  private RefNbtTagInt(){
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagInt;a(I)Lnet/minecraft/server/v1_16_R3/NBTTagInt;")
  public static native RefNbtTagInt of(int value);
}
