package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagString;

public class NbtString extends Nbt<RefNbtTagString> {
  private static final boolean OF_SUPPORTED = CbVersion.v1_16_R3.isSupport();
  private static final NbtString EMPTY = new NbtString(OF_SUPPORTED
      ? RefNbtTagString.of("")
      : new RefNbtTagString(""));

  NbtString(RefNbtTagString delegate) {
    super(delegate);
  }

  public static NbtString valueOf(String value) {
    return value.isEmpty()
        ? EMPTY
        : new NbtString(OF_SUPPORTED
            ? RefNbtTagString.of(value)
            : new RefNbtTagString(value));
  }

  @Override
  public NbtString clone() {
    return new NbtString(cloneNms());
  }
}
