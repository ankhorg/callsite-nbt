package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtBase;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagCompound;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagLongArray;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class NbtCompound extends Nbt<RefNbtTagCompound> {
  private static final boolean SET_RETURN_SUPPORT = CbVersion.v1_14_R1.isSupport();
  private static final boolean LONG_ARRAY_SUPPORT = CbVersion.v1_13_R1.isSupport();
  private static final boolean PUT_BYTE_LIST_SUPPORT = CbVersion.v1_17_R1.isSupport();
  private static final boolean PUT_INT_LIST_SUPPORT = CbVersion.v1_13_R1.isSupport();
  private static final long[] EMPTY_LONG_ARRAY = new long[0];

  NbtCompound(RefNbtTagCompound delegate) {
    super(delegate);
  }

  public NbtCompound() {
    super(new RefNbtTagCompound());
  }

  public Set<String> getAllKeys() {
    return delegate.getKeys();
  }

  public int size() {
    return delegate.size();
  }

  public Nbt<?> put(String key, Nbt<?> value) {
    if (SET_RETURN_SUPPORT) {
      return Nbt.fromNms(delegate.set1(key, value.delegate));
    } else {
      Nbt<?> previous = Nbt.fromNms(delegate.get(key));
      delegate.set0(key, value.delegate);
      return previous;
    }
  }

  public void set(String key, Nbt<?> value) {
    if (SET_RETURN_SUPPORT) {
      delegate.set1(key, value.delegate);
    } else {
      delegate.set0(key, value.delegate);
    }
  }

  public void putByte(String key, byte value) {
    delegate.setByte(key, value);
  }

  public void putShort(String key, short value) {
    delegate.setShort(key, value);
  }

  public void putInt(String key, int value) {
    delegate.setInt(key, value);
  }

  public void putLong(String key, long value) {
    delegate.setLong(key, value);
  }

  public void putUUID(String key, UUID value) {
    delegate.setUUID(key, value);
  }

  public void putFloat(String key, float value) {
    delegate.setFloat(key, value);
  }

  public void putDouble(String key, double value) {
    delegate.setDouble(key, value);
  }

  public void putString(String key, String value) {
    delegate.setString(key, value);
  }

  public void putByteArray(String key, byte[] value) {
    delegate.setByteArray(key, value);
  }

  public void putByteArray(String key, List<Byte> value) {
    if (PUT_BYTE_LIST_SUPPORT) {
      delegate.setByteArray(key, value);
    } else {
      byte[] bytes = new byte[value.size()];
      for (int i = 0; i < value.size(); i++) {
        Byte element = value.get(i);
        bytes[i] = (element == null) ? 0 : element;
      }
      delegate.setByteArray(key, bytes);
    }
  }

  public void putIntArray(String key, int[] value) {
    delegate.setIntArray(key, value);
  }

  public void putIntArray(String key, List<Integer> value) {
    if (PUT_INT_LIST_SUPPORT) {
      delegate.setIntArray(key, value);
    } else {
      int[] ints = new int[value.size()];
      for (int i = 0; i < value.size(); i++) {
        Integer element = value.get(i);
        ints[i] = (element == null) ? 0 : element;
      }
      delegate.setIntArray(key, ints);
    }
  }

  public void putLongArray(String key, long[] value) {
    if (LONG_ARRAY_SUPPORT) {
      delegate.setLongArray(key, value);
    } else if (SET_RETURN_SUPPORT) {
      delegate.set1(key, new RefNbtTagLongArray(value));
    } else {
      delegate.set0(key, new RefNbtTagLongArray(value));
    }
  }

  public void putLongArray(String key, List<Long> value) {
    if (LONG_ARRAY_SUPPORT) {
      delegate.setLongArray(key, value);
    } else if (SET_RETURN_SUPPORT) {
      delegate.set1(key, new RefNbtTagLongArray(value));
    } else {
      delegate.set0(key, new RefNbtTagLongArray(value));
    }
  }

  public void putBoolean(String key, boolean value) {
    delegate.setBoolean(key, value);
  }

  public Nbt<RefNbtBase> get(String key) {
    return Nbt.fromNms(delegate.get(key));
  }

  public byte getTagType(String key) {
    return delegate.getType(key);
  }

  public boolean contains(String key) {
    return delegate.hasKey(key);
  }

  public boolean contains(String key, int value) {
    return delegate.hasKeyOfType(key, value);
  }

  public byte getByte(String key) {
    return delegate.getByte(key);
  }

  public short getShort(String key) {
    return delegate.getShort(key);
  }

  public int getInt(String key) {
    return delegate.getInt(key);
  }

  public long getLong(String key) {
    return delegate.getLong(key);
  }

  public UUID getUUID(String key) {
    return delegate.getUUID(key);
  }

  public float getFloat(String key) {
    return delegate.getFloat(key);
  }

  public double getDouble(String key) {
    return delegate.getDouble(key);
  }

  public String getString(String key) {
    return delegate.getString(key);
  }

  public byte[] getByteArray(String key) {
    return delegate.getByteArray(key);
  }

  public int[] getIntArray(String key) {
    return delegate.getIntArray(key);
  }

  public long[] getLongArray(String key) {
    if (LONG_ARRAY_SUPPORT) {
      return delegate.getLongArray(key);
    } else {
      if (delegate.hasKeyOfType(key, 12)) {
        return ((NbtLongArray) (Object) this.get(key)).getAsLongArray();
      } else {
        return EMPTY_LONG_ARRAY;
      }
    }
  }

  public NbtCompound getCompound(String key) {
    return new NbtCompound(delegate.getCompound(key));
  }

  public NbtList getList(String key, int elementType) {
    return new NbtList(delegate.getList(key, elementType));
  }

  public boolean getBoolean(String key) {
    return delegate.getBoolean(key);
  }

  public void remove(String key) {
    delegate.remove(key);
  }

  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public NbtCompound clone() {
    return new NbtCompound(cloneNms());
  }

  /**
   * 根据 NBT键 获取对应的 Nbt, 如果没有找到对应的 Nbt 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 Nbt 的 NBT键.
   * @return 待查找的 Nbt.
   */
  public Nbt<?> getDeepNbt(String key) {
    String[] keys = key.split("\\.");

    NbtCompound currentNbtCompound = this;
    Nbt<?> value = null;

    for (String k : keys) {
      if (currentNbtCompound.contains(k)) {
        Nbt<?> obj = currentNbtCompound.get(k);
        if (obj instanceof NbtCompound) {
          currentNbtCompound = (NbtCompound) obj;
        } else {
          value = obj;
          break;
        }
      } else {
        break;
      }
    }

    return value;
  }

  /**
   * 根据 NBT键 获取对应的 byte, 如果没有找到对应的 byte 则返回 (byte) 0.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte 的 NBT键.
   * @return 待查找的 byte.
   */
  public byte getDeepByte(String key) {
    return getDeepByte(key, (byte) 0);
  }

  /**
   * 根据 NBT键 获取对应的 byte, 如果没有找到对应的 byte 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 byte, 则返回的默认值.
   * @return 待查找的 byte.
   */
  public byte getDeepByte(String key, byte def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsByte();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 short, 如果没有找到对应的 short 则返回 (short) 0.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 short 的 NBT键.
   * @return 待查找的 short.
   */
  public short getDeepShort(String key) {
    return getDeepShort(key, (short) 0);
  }

  /**
   * 根据 NBT键 获取对应的 short, 如果没有找到对应的 short 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 short 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 short, 则返回的默认值.
   * @return 待查找的 short.
   */
  public short getDeepShort(String key, short def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsShort();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 int, 如果没有找到对应的 int 则返回 0.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 int 的 NBT键.
   * @return 待查找的 int.
   */
  public int getDeepInt(String key) {
    return getDeepInt(key, 0);
  }

  /**
   * 根据 NBT键 获取对应的 int, 如果没有找到对应的 int 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 int 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 int, 则返回的默认值.
   * @return 待查找的 int.
   */
  public int getDeepInt(String key, int def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsInt();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 long, 如果没有找到对应的 long 则返回 0.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 long 的 NBT键.
   * @return 待查找的 long.
   */
  public long getDeepLong(String key) {
    return getDeepLong(key, 0);
  }

  /**
   * 根据 NBT键 获取对应的 long, 如果没有找到对应的 long 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 long 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 long, 则返回的默认值.
   * @return 待查找的 long.
   */
  public long getDeepLong(String key, long def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsLong();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 float, 如果没有找到对应的 float 则返回 0.0F.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 float 的 NBT键.
   * @return 待查找的 float.
   */
  public float getDeepFloat(String key) {
    return getDeepFloat(key, 0.0F);
  }

  /**
   * 根据 NBT键 获取对应的 float, 如果没有找到对应的 float 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 float 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 float, 则返回的默认值.
   * @return 待查找的 float.
   */
  public float getDeepFloat(String key, float def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsFloat();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 double, 如果没有找到对应的 double 则返回 0.0D.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 double 的 NBT键.
   * @return 待查找的 double.
   */
  public double getDeepDouble(String key) {
    return getDeepDouble(key, 0.0D);
  }

  /**
   * 根据 NBT键 获取对应的 double, 如果没有找到对应的 double 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 double 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 double, 则返回的默认值.
   * @return 待查找的 double.
   */
  public double getDeepDouble(String key, double def) {
    try {
      if (contains(key, NbtType.TAG_ANY_NUMBER))
        return ((NbtNumeric<?>) getDeepNbt(key)).getAsDouble();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 String, 如果没有找到对应的 String 则返回空字符串.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 String 的 NBT键.
   * @return 待查找的 String.
   */
  public String getDeepString(String key) {
    return getDeepString(key, "");
  }

  /**
   * 根据 NBT键 获取对应的 String, 如果没有找到对应的 String 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 String 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 String, 则返回的默认值.
   * @return 待查找的 String.
   */
  public String getDeepString(String key, String def) {
    try {
      if (contains(key, NbtType.TAG_STRING))
        return getDeepNbt(key).getAsString();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 byte[], 如果没有找到对应的 byte[] 则返回 new byte[0].
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte[] 的 NBT键.
   * @return 待查找的 byte[].
   */
  public byte[] getDeepByteArray(String key) {
    return getDeepByteArray(key, new byte[0]);
  }

  /**
   * 根据 NBT键 获取对应的 byte[], 如果没有找到对应的 byte[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 byte[], 则返回的默认值.
   * @return 待查找的 byte[].
   */
  public byte[] getDeepByteArray(String key, byte[] def) {
    try {
      if (contains(key, NbtType.TAG_BYTE_ARRAY))
        return ((NbtByteArray) getDeepNbt(key)).getAsByteArray();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 int[], 如果没有找到对应的 int[] 则返回 new int[0].
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 int[] 的 NBT键.
   * @return 待查找的 int[].
   */
  public int[] getDeepIntArray(String key) {
    return getDeepIntArray(key, new int[0]);
  }

  /**
   * 根据 NBT键 获取对应的 int[], 如果没有找到对应的 int[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 int[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 int[], 则返回的默认值.
   * @return 待查找的 int[].
   */
  public int[] getDeepIntArray(String key, int[] def) {
    try {
      if (contains(key, NbtType.TAG_INT_ARRAY))
        return ((NbtIntArray) getDeepNbt(key)).getAsIntArray();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 long[], 如果没有找到对应的 long[] 则返回 new long[0].
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 long[] 的 NBT键.
   * @return 待查找的 long[].
   */
  public long[] getDeepLongArray(String key) {
    return getDeepLongArray(key, new long[0]);
  }

  /**
   * 根据 NBT键 获取对应的 long[], 如果没有找到对应的 long[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 long[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 long[], 则返回的默认值.
   * @return 待查找的 long[].
   */
  public long[] getDeepLongArray(String key, long[] def) {
    try {
      if (contains(key, NbtType.TAG_LONG_ARRAY))
        return ((NbtLongArray) getDeepNbt(key)).getAsLongArray();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 NbtCompound, 如果没有找到对应的 NbtCompound 则返回 new NbtCompound().
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtCompound 的 NBT键.
   * @return 待查找的 NbtCompound.
   */
  public NbtCompound getDeepCompound(String key) {
    return getDeepCompound(key, new NbtCompound());
  }

  /**
   * 根据 NBT键 获取对应的 NbtCompound, 如果没有找到对应的 NbtCompound 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtCompound 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 NbtCompound, 则返回的默认值.
   * @return 待查找的 NbtCompound.
   */
  public NbtCompound getDeepCompound(String key, NbtCompound def) {
    try {
      if (contains(key, NbtType.TAG_COMPOUND))
        return (NbtCompound) getDeepNbt(key);
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 NbtList, 如果没有找到对应的 NbtList 则返回 new NbtList().
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtList 的 NBT键.
   * @return 待查找的 NbtList.
   */
  public NbtList getDeepList(String key) {
    return getDeepList(key, new NbtList());
  }


  /**
   * 根据 NBT键 获取对应的 NbtList, 如果没有找到对应的 NbtList 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtList 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 NbtList, 则返回的默认值.
   * @return 待查找的 NbtList.
   */
  public NbtList getDeepList(String key, NbtList def) {
    try {
      if (contains(key, NbtType.TAG_LIST))
        return (NbtList) getDeepNbt(key);
    } catch (ClassCastException ignored) {}
    return def;
  }
}
