package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagByte;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagByteArray;

import java.util.List;

public final class NbtByteArray extends NbtCollection<RefNbtTagByteArray, RefNbtTagByte, NbtByte> {
  NbtByteArray(RefNbtTagByteArray delegate) {
    super(delegate);
  }

  public NbtByteArray(byte[] value) {
    super(new RefNbtTagByteArray(value));
  }

  public NbtByteArray(List<Byte> value) {
    super(new RefNbtTagByteArray(value));
  }

  @Override
  public NbtByteArray clone() {
    return new NbtByteArray(cloneNms());
  }
}
