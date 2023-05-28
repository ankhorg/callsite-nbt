package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagLong;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagLongArray;

import java.util.List;

public final class NbtLongArray extends NbtCollection<RefNbtTagLongArray, RefNbtTagLong, NbtLong> {
  NbtLongArray(RefNbtTagLongArray delegate) {
    super(delegate);
  }

  public NbtLongArray(long[] value) {
    super(new RefNbtTagLongArray(value));
  }

  public NbtLongArray(List<Long> value) {
    super(new RefNbtTagLongArray(value));
  }

  @Override
  public NbtLongArray clone() {
    return new NbtLongArray(cloneNms());
  }
}
