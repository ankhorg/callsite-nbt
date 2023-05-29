package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagLong;

public final class NbtLong extends NbtNumeric<RefNbtTagLong> {
  private static final boolean OF_SUPPORTED = CbVersion.v1_16_R3.isSupport();
  private static final NbtLong[] instanceCache = buildInstanceCache();

  NbtLong(RefNbtTagLong delegate) {
    super(delegate);
  }

  private static NbtLong[] buildInstanceCache() {
    NbtLong[] result = new NbtLong[1153];
    for (int i = 0; i < result.length; i++) {
      result[i] = new NbtLong(OF_SUPPORTED
          ? RefNbtTagLong.of(i - 128)
          : new RefNbtTagLong(i - 128));
    }
    return result;
  }

  public static NbtLong valueOf(long value) {
    return (value >= -128 && value <= 1024)
        ? instanceCache[(int) value + 128]
        : new NbtLong(OF_SUPPORTED
        ? RefNbtTagLong.of(value)
        : new RefNbtTagLong(value));
  }

  static NbtLong fromNmsImpl(RefNbtTagLong delegate) {
    long value = delegate.asLong();
    return (value >= -128 && value <= 1024) ? instanceCache[(int) value + 128] : new NbtLong(delegate);
  }

  @Override
  public NbtLong clone() {
    return this;
  }
}
