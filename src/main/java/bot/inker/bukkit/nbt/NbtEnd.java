package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagEnd;

public final class NbtEnd extends Nbt<RefNbtTagEnd> {
  private static final boolean INSTANCE_FIELD_SUPPORT = CbVersion.v1_16_R3.isSupport();

  public static final NbtEnd INSTANCE = INSTANCE_FIELD_SUPPORT
      ? new NbtEnd(RefNbtTagEnd.INSTANCE)
      : new NbtEnd((RefNbtTagEnd) RefNbtBase.createTag((byte) 0));

  private NbtEnd(RefNbtTagEnd delegate) {
    super(delegate);
  }

  @Override
  public Nbt<RefNbtTagEnd> clone() {
    return this;
  }
}
