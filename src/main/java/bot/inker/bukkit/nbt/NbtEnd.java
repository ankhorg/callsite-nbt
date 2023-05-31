package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtBase;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagEnd;

public final class NbtEnd extends Nbt<RefNbtTagEnd> {
  private static final boolean INSTANCE_FIELD_SUPPORT = CbVersion.v1_15_R1.isSupport();

  static final NbtEnd INSTANCE = INSTANCE_FIELD_SUPPORT
      ? new NbtEnd(RefNbtTagEnd.INSTANCE)
      : new NbtEnd((RefNbtTagEnd) RefNbtBase.createTag((byte) 0));

  private NbtEnd(RefNbtTagEnd delegate) {
    super(delegate);
  }

  public static NbtEnd instance() {
    return INSTANCE;
  }

  @Override
  public Nbt<RefNbtTagEnd> clone() {
    return this;
  }
}
