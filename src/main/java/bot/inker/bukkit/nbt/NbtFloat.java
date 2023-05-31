package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagFloat;

public final class NbtFloat extends NbtNumeric<RefNbtTagFloat> {
  private static final boolean OF_SUPPORTED = CbVersion.v1_15_R1.isSupport();
  private static final NbtFloat ZERO = new NbtFloat(OF_SUPPORTED
      ? RefNbtTagFloat.of(0.0F)
      : new RefNbtTagFloat(0.0F)
  );

  NbtFloat(RefNbtTagFloat delegate) {
    super(delegate);
  }

  public static NbtFloat valueOf(float value) {
    return value == 0.0F ? ZERO : new NbtFloat(OF_SUPPORTED
        ? RefNbtTagFloat.of(value)
        : new RefNbtTagFloat(value));
  }

  static NbtFloat fromNmsImpl(RefNbtTagFloat delegate) {
    return delegate.asFloat() == 0.0F ? ZERO : new NbtFloat(delegate);
  }

  @Override
  public NbtFloat clone() {
    return this;
  }
}
