package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.ref.RefNbtTagLongArray;

import java.util.List;

public final class NbtLongArray extends NbtCollection<RefNbtTagLongArray, Long> {
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
  public Long get(int index) {
    return delegate.getLongs()[index];
  }

  public long getLong(int index) {
    return delegate.getLongs()[index];
  }

  @Override
  public int size() {
    return delegate.getLongs().length;
  }

  @Override
  public Long set(int index, Long element) {
    throw new UnsupportedOperationException("NbtLongArray is immutable");
  }

  @Override
  public void add(int index, Long element) {
    throw new UnsupportedOperationException("NbtLongArray is immutable");
  }

  @Override
  public Long remove(int index) {
    throw new UnsupportedOperationException("NbtLongArray is immutable");
  }

  public long[] getAsLongArray() {
    return delegate.getLongs();
  }

  @Override
  public NbtLongArray clone() {
    return new NbtLongArray(cloneNms());
  }
}
