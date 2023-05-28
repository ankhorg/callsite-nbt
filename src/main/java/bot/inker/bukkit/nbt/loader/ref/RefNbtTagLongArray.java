package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

import java.util.List;

public final class RefNbtTagLongArray extends RefNbtList<RefNbtTagLong> {
  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagLongArray;<init>([J)V")
  public RefNbtTagLongArray(long[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagLongArray;<init>(Ljava/util/List;)V")
  public RefNbtTagLongArray(List<Long> value) {
    throw new UnsupportedOperationException();
  }
}
