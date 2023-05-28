package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagDouble;

public final class NbtDouble extends NbtNumeric<RefNbtTagDouble> {
  private static final NbtDouble ZERO = new NbtDouble(RefNbtTagDouble.of(0.0));

  NbtDouble(RefNbtTagDouble delegate) {
    super(delegate);
  }

  public static NbtDouble valueOf(double value) {
    return value == 0.0 ? ZERO : new NbtDouble(RefNbtTagDouble.of(value));
  }

  @Override
  public NbtDouble clone() {
    return this;
  }
}
