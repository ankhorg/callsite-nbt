package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagString;

public class NbtString extends Nbt<RefNbtTagString> {
  private static final NbtString EMPTY = new NbtString(RefNbtTagString.of(""));

  NbtString(RefNbtTagString delegate) {
    super(delegate);
  }

  @Override
  public NbtString clone() {
    return new NbtString(cloneNms());
  }

  public static NbtString valueOf(String value){
    return value.isEmpty() ? EMPTY : new NbtString(RefNbtTagString.of(value));
  }
}
