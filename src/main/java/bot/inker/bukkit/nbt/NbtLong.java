package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagLong;

public final class NbtLong extends NbtNumeric<RefNbtTagLong> {
  private static final NbtLong[] instanceCache = buildInstanceCache();

  NbtLong(RefNbtTagLong delegate) {
    super(delegate);
  }

  @Override
  public NbtLong clone() {
    return this;
  }

  private static NbtLong[] buildInstanceCache(){
    NbtLong[] result = new NbtLong[1153];
    for (int i = 0; i < result.length; i++) {
      result[i] = new NbtLong(RefNbtTagLong.of(i - 128));
    }
    return result;
  }

  public static NbtLong valueOf(long value){
    return (value >= -128 && value <= 1024) ? instanceCache[(int) value + 128] : new NbtLong(RefNbtTagLong.of(value));
  }
}
