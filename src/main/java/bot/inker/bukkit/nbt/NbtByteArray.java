package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagByteArray;

import java.util.List;

public final class NbtByteArray extends Nbt<RefNbtTagByteArray> {
  NbtByteArray(RefNbtTagByteArray delegate) {
    super(delegate);
  }

  public NbtByteArray(byte[] value) {
    super(new RefNbtTagByteArray(value));
  }

  public NbtByteArray(List<Byte> value) {
    super(new RefNbtTagByteArray(value));
  }

  public byte[] getAsByteArray() {
    return delegate.getBytes();
  }

  @Override
  public NbtByteArray clone() {
    return new NbtByteArray(cloneNms());
  }
}
