package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagIntArray;

import java.util.List;

public final class NbtIntArray extends Nbt<RefNbtTagIntArray> {
  NbtIntArray(RefNbtTagIntArray delegate) {
    super(delegate);
  }

  public NbtIntArray(int[] value) {
    super(new RefNbtTagIntArray(value));
  }

  public NbtIntArray(List<Integer> value) {
    super(new RefNbtTagIntArray(value));
  }

  public int[] getAsIntArray() {
    return delegate.getInts();
  }

  @Override
  public NbtIntArray clone() {
    return new NbtIntArray(cloneNms());
  }
}
