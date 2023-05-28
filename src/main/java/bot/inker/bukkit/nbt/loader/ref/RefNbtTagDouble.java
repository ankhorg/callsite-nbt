package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagDouble")
public final class RefNbtTagDouble extends RefNbtNumber {
  private RefNbtTagDouble() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagDouble;a(D)Lnet/minecraft/server/v1_16_R3/NBTTagDouble;")
  public static native RefNbtTagDouble of(double value);
}
