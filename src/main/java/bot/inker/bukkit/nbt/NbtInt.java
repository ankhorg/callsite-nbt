package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagInt;

public final class NbtInt extends NbtNumeric<RefNbtTagInt> {
  private static final NbtInt[] instanceCache = buildInstanceCache();

  NbtInt(RefNbtTagInt delegate) {
    super(delegate);
  }

  @Override
  public NbtInt clone() {
    return this;
  }

  private static NbtInt[] buildInstanceCache(){
    NbtInt[] result = new NbtInt[1153];
    for (int i = 0; i < result.length; i++) {
      result[i] = new NbtInt(RefNbtTagInt.of(i - 128));
    }
    return result;
  }

  public static NbtInt valueOf(int value){
    return (value >= -128 && value <= 1024) ? instanceCache[value + 128] : new NbtInt(RefNbtTagInt.of(value));
  }
}
