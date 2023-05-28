package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagLong")
public final class RefNbtTagLong extends RefNbtNumber {
  private RefNbtTagLong() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagLong;a(J)Lnet/minecraft/server/v1_16_R3/NBTTagLong;")
  public static native RefNbtTagLong of(long value);
}
