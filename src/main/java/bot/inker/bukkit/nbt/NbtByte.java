package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtTagByte;

public final class NbtByte extends NbtNumeric<RefNbtTagByte> {
  private static final NbtByte[] instanceCache = buildInstanceCache();
  private static final NbtByte falseInstance = valueOf((byte) 0);
  private static final NbtByte trueInstance = valueOf((byte) 1);

  private NbtByte(RefNbtTagByte delegate) {
    super(delegate);
  }

  @Override
  public NbtByte clone() {
    return this;
  }

  private static NbtByte[] buildInstanceCache(){
    NbtByte[] result = new NbtByte[256];
    for (int i = 0; i < result.length; i++) {
      result[i] = new NbtByte(RefNbtTagByte.of((byte) (i - 128)));
    }
    return result;
  }

  public static NbtByte valueOf(byte value){
    return instanceCache[value + 128];
  }

  public static NbtByte valueOf(boolean value){
    return value ? trueInstance : falseInstance;
  }
}
