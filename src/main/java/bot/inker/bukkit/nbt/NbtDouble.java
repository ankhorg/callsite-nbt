package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagDouble;

public final class NbtDouble extends NbtNumeric<RefNbtTagDouble> {
  private static final boolean OF_SUPPORTED = CbVersion.v1_15_R1.isSupport();
  private static final NbtDouble ZERO = new NbtDouble(OF_SUPPORTED
      ? RefNbtTagDouble.of(0.0)
      : new RefNbtTagDouble(0.0));

  NbtDouble(RefNbtTagDouble delegate) {
    super(delegate);
  }

  public static NbtDouble valueOf(double value) {
    return value == 0.0 ? ZERO : new NbtDouble(OF_SUPPORTED
        ? RefNbtTagDouble.of(value)
        : new RefNbtTagDouble(value));
  }

  static NbtDouble fromNmsImpl(RefNbtTagDouble delegate) {
    return delegate.asDouble() == 0.0 ? ZERO : new NbtDouble(delegate);
  }

  @Override
  public NbtDouble clone() {
    return this;
  }
}
