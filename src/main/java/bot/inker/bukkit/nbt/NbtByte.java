package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtByteLike;
import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagByte;

public final class NbtByte extends NbtNumeric<RefNbtTagByte> implements NbtByteLike {
  private static final boolean OF_SUPPORTED = CbVersion.v1_15_R1.isSupport();
  private static final NbtByte[] instanceCache = buildInstanceCache();
  private static final NbtByte falseInstance = valueOf((byte) 0);
  private static final NbtByte trueInstance = valueOf((byte) 1);

  private NbtByte(RefNbtTagByte delegate) {
    super(delegate);
  }

  private static NbtByte[] buildInstanceCache() {
    NbtByte[] result = new NbtByte[256];
    for (int i = 0; i < result.length; i++) {
      result[i] = OF_SUPPORTED
          ? new NbtByte(RefNbtTagByte.of((byte) (i - 128)))
          : new NbtByte(new RefNbtTagByte((byte) (i - 128)));
    }
    return result;
  }

  public static NbtByte valueOf(byte value) {
    return instanceCache[value + 128];
  }

  static NbtByte fromNmsImpl(RefNbtTagByte delegate) {
    return instanceCache[delegate.asByte() + 128];
  }

  public static NbtByte valueOf(boolean value) {
    return value ? trueInstance : falseInstance;
  }

  public boolean getAsBoolean() {
    return getAsByte() != 0;
  }

  @Override
  public NbtByte clone() {
    return this;
  }
}
