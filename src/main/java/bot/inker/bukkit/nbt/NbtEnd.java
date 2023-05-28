package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagEnd;

public final class NbtEnd extends Nbt<RefNbtTagEnd> {
  public static final NbtEnd INSTANCE = new NbtEnd(RefNbtTagEnd.INSTANCE);

  private NbtEnd(RefNbtTagEnd delegate) {
    super(delegate);
  }

  @Override
  public Nbt<RefNbtTagEnd> clone() {
    return this;
  }
}
