package bot.inker.bukkit.nbt.api;

import bot.inker.bukkit.nbt.Nbt;

public interface NbtListLike extends NbtCollectionLike<Nbt<?>> {
  NbtListLike clone();
}
