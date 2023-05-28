package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagFloat;

public final class NbtFloat extends NbtNumeric<RefNbtTagFloat> {
  private static final NbtFloat ZERO = new NbtFloat(RefNbtTagFloat.of(0.0F));
  NbtFloat(RefNbtTagFloat delegate) {
    super(delegate);
  }

  @Override
  public NbtFloat clone() {
    return this;
  }

  public static NbtFloat valueOf(float value){
    return value == 0.0F ? ZERO : new NbtFloat(RefNbtTagFloat.of(value));
  }
}
