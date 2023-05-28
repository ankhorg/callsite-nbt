package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagShort;

public final class NbtShort extends NbtNumeric<RefNbtTagShort> {
  private static final boolean OF_SUPPORTED = CbVersion.v1_16_R3.isSupport();
  private static final NbtShort[] instanceCache = buildInstanceCache();

  NbtShort(RefNbtTagShort delegate) {
    super(delegate);
  }

  private static NbtShort[] buildInstanceCache() {
    NbtShort[] result = new NbtShort[1153];
    for (int i = 0; i < result.length; i++) {
      result[i] = new NbtShort(OF_SUPPORTED
          ? RefNbtTagShort.of((short) (i - 128))
          : new RefNbtTagShort((short) (i - 128)));
    }
    return result;
  }

  public static NbtShort valueOf(short value) {
    return (value >= -128 && value <= 1024)
        ? instanceCache[value + 128]
        : new NbtShort(OF_SUPPORTED
            ? RefNbtTagShort.of(value)
            : new RefNbtTagShort(value));
  }

  @Override
  public NbtShort clone() {
    return this;
  }
}
