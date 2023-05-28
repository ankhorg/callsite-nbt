package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

import java.util.AbstractList;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTList")
public abstract class RefNbtList<T extends RefNbtBase> extends AbstractList<T> implements RefNbtBase {
  RefNbtList() {
    throw new UnsupportedOperationException();
  }

  @Override
  public native int size();

  @Override
  public native T get(int index);
}
