package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagLongArray;

import java.lang.reflect.Field;
import java.util.List;

public final class NbtLongArray extends Nbt<RefNbtTagLongArray> {
  private static final boolean ACCESS_ARRAY_SUPPORT = CbVersion.v1_16_R3.isSupport();
  private static final Field accessField = ACCESS_ARRAY_SUPPORT ? null : provideAccessField();

  private static Field provideAccessField() {
    for (Field field : RefNbtTagLongArray.class.getDeclaredFields()) {
      if (field.getType() == long[].class) {
        field.setAccessible(true);
        return field;
      }
    }
    throw new IllegalStateException("No direct access field found in " + RefNbtTagLongArray.class);
  }

  NbtLongArray(RefNbtTagLongArray delegate) {
    super(delegate);
  }

  public NbtLongArray(long[] value) {
    super(new RefNbtTagLongArray(value));
  }

  public NbtLongArray(List<Long> value) {
    super(new RefNbtTagLongArray(value));
  }

  public long[] getAsLongArray() {
    if(ACCESS_ARRAY_SUPPORT) {
      return delegate.getLongs();
    }else{
      try {
        return (long[]) accessField.get(delegate);
      } catch (IllegalAccessException e) {
        throw new IllegalStateException(e.getMessage(), e.getCause());
      }
    }
  }

  @Override
  public NbtLongArray clone() {
    return new NbtLongArray(cloneNms());
  }
}
