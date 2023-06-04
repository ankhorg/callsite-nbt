package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class NbtCompound extends Nbt<RefNbtTagCompound> {
  private static final boolean SET_RETURN_SUPPORT = CbVersion.v1_14_R1.isSupport();
  private static final boolean LONG_ARRAY_SUPPORT = CbVersion.v1_13_R1.isSupport();
  private static final boolean PUT_BYTE_LIST_SUPPORT = CbVersion.v1_17_R1.isSupport();
  private static final boolean PUT_INT_LIST_SUPPORT = CbVersion.v1_13_R1.isSupport();
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  private static final int[] EMPTY_INT_ARRAY = new int[0];
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
   * 根据 NBT键 获取对应的 RefNbtBase, 如果没有找到对应的 RefNbtBase 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 RefNbtBase 的 NBT键.
   * @return 待查找的 RefNbtBase.
   */
  @Nullable
  private RefNbtBase getDeepRefNbt(@NotNull String key) {
    String[] keys = key.split("\\.");

    RefNbtTagCompound currentNbtCompound = this.delegate;
    RefNbtBase value = null;

    for (String k : keys) {
      if (currentNbtCompound.hasKey(k)) {
        RefNbtBase obj = currentNbtCompound.get(k);
        if (obj instanceof RefNbtTagCompound) {
          currentNbtCompound = (RefNbtTagCompound) obj;
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
   * 根据 NBT键 获取对应的 Nbt, 如果没有找到对应的 Nbt 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 Nbt 的 NBT键.
   * @return 待查找的 Nbt.
   */
  @Nullable
  public Nbt<RefNbtBase> getDeepNbt(@NotNull String key) {
    return fromNms(getDeepRefNbt(key));
  }

  /**
   * 根据 NBT键 获取对应的 byte, 如果没有找到对应的 byte 则返回 (byte) 0.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte 的 NBT键.
   * @return 待查找的 byte.
   */
  @SuppressWarnings("unused")
  public byte getDeepByte(@NotNull String key) {
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
  public byte getDeepByte(@NotNull String key, byte def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asByte();
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
  @SuppressWarnings("unused")
  public short getDeepShort(@NotNull String key) {
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
  public short getDeepShort(@NotNull String key, short def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asShort();
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
  @SuppressWarnings("unused")
  public int getDeepInt(@NotNull String key) {
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
  public int getDeepInt(@NotNull String key, int def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asInt();
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
  @SuppressWarnings("unused")
  public long getDeepLong(@NotNull String key) {
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
  public long getDeepLong(@NotNull String key, long def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asLong();
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
  @SuppressWarnings("unused")
  public float getDeepFloat(@NotNull String key) {
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
  public float getDeepFloat(@NotNull String key, float def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asFloat();
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
  @SuppressWarnings("unused")
  public double getDeepDouble(@NotNull String key) {
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
  public double getDeepDouble(@NotNull String key, double def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asDouble();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 String, 如果没有找到对应的 String 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 String 的 NBT键.
   * @return 待查找的 String.
   */
  @SuppressWarnings("unused")
  @Nullable
  public String getDeepString(@NotNull String key) {
    return getDeepString(key, null);
  }

  /**
   * 根据 NBT键 获取对应的 String, 如果没有找到对应的 String 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 String 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 String, 则返回的默认值.
   * @return 待查找的 String.
   */
  @Nullable
  public String getDeepString(@NotNull String key, @Nullable String def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_STRING)
        return value.asString();
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
  @SuppressWarnings("unused")
  public byte[] getDeepByteArray(@NotNull String key) {
    return getDeepByteArray(key, EMPTY_BYTE_ARRAY);
  }

  /**
   * 根据 NBT键 获取对应的 byte[], 如果没有找到对应的 byte[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 byte[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 byte[], 则返回的默认值.
   * @return 待查找的 byte[].
   */
  public byte[] getDeepByteArray(@NotNull String key, byte[] def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_BYTE_ARRAY)
        return ((RefNbtTagByteArray) value).getBytes();
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
  @SuppressWarnings("unused")
  public int[] getDeepIntArray(@NotNull String key) {
    return getDeepIntArray(key, EMPTY_INT_ARRAY);
  }

  /**
   * 根据 NBT键 获取对应的 int[], 如果没有找到对应的 int[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 int[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 int[], 则返回的默认值.
   * @return 待查找的 int[].
   */
  public int[] getDeepIntArray(@NotNull String key, int[] def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_INT_ARRAY)
        return ((RefNbtTagIntArray) value).getInts();
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
  @SuppressWarnings("unused")
  public long[] getDeepLongArray(@NotNull String key) {
    return getDeepLongArray(key, EMPTY_LONG_ARRAY);
  }

  /**
   * 根据 NBT键 获取对应的 long[], 如果没有找到对应的 long[] 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 long[] 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 long[], 则返回的默认值.
   * @return 待查找的 long[].
   */
  public long[] getDeepLongArray(@NotNull String key, long[] def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_LONG_ARRAY)
        return ((RefNbtTagLongArray) value).getLongs();
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 NbtCompound, 如果没有找到对应的 NbtCompound 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtCompound 的 NBT键.
   * @return 待查找的 NbtCompound.
   */
  @SuppressWarnings("unused")
  @Nullable
  public NbtCompound getDeepCompound(@NotNull String key) {
    return getDeepCompound(key, null);
  }

  /**
   * 根据 NBT键 获取对应的 NbtCompound, 如果没有找到对应的 NbtCompound 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtCompound 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 NbtCompound, 则返回的默认值.
   * @return 待查找的 NbtCompound.
   */
  @Nullable
  public NbtCompound getDeepCompound(@NotNull String key, @Nullable NbtCompound def) {
    try {
      Nbt<?> value = getDeepNbt(key);
      if (value != null && value.getId() == NbtType.TAG_COMPOUND)
        return (NbtCompound) value;
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 NbtList, 如果没有找到对应的 NbtList 则返回 null.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtList 的 NBT键.
   * @return 待查找的 NbtList.
   */
  @SuppressWarnings("unused")
  @Nullable
  public NbtList getDeepList(@NotNull String key) {
    return getDeepList(key, null);
  }


  /**
   * 根据 NBT键 获取对应的 NbtList, 如果没有找到对应的 NbtList 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 NbtList 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 NbtList, 则返回的默认值.
   * @return 待查找的 NbtList.
   */
  @Nullable
  public NbtList getDeepList(@NotNull String key, @Nullable NbtList def) {
    try {
      Nbt<?> value = getDeepNbt(key);
      if (value != null && value.getId() == NbtType.TAG_LIST)
        return (NbtList) value;
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 根据 NBT键 获取对应的 boolean, 如果没有找到对应的 boolean 则返回 false.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 boolean 的 NBT键.
   * @return 待查找的 boolean.
   */
  @SuppressWarnings("unused")
  public boolean getDeepBoolean(@NotNull String key) {
    return getDeepBoolean(key, false);
  }

  /**
   * 根据 NBT键 获取对应的 boolean, 如果没有找到对应的 boolean 则返回默认值.
   * NBT键 以 . 做分隔符.
   *
   * @param key 要获取 boolean 的 NBT键.
   * @param def 如果找不到对应的 NBT 或对应的 NBT 不是 boolean, 则返回的默认值.
   * @return 待查找的 boolean.
   */
  public boolean getDeepBoolean(@NotNull String key, boolean def) {
    try {
      RefNbtBase value = getDeepRefNbt(key);
      if (value != null && value.getTypeId() == NbtType.TAG_ANY_NUMBER)
        return ((RefNbtNumber) value).asByte() != (byte) 0;
    } catch (ClassCastException ignored) {}
    return def;
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 如果设置前 NBT键 没有对应的 NBT值, 则返回 null.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置并返回 null.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置并返回 null.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   * @return 设置前与 NBT键 对应的 NBT值, 如果没有对应的值, 或 force 为 false 且对应的 key 无效, 则返回 null.
   */
  @Nullable
  private RefNbtBase putDeepRefNbt(@NotNull String key, @NotNull RefNbtBase value, boolean force) {
    String[] keys = key.split("\\.");

    RefNbtTagCompound currentNbtCompound = this.delegate;

    // 遍历key
    for (int i = 0; i < keys.length; i++) {
      String k = keys[i];

      // 未达末级
      if (i != (keys.length - 1)) {
        // 存在key
        if (currentNbtCompound.hasKey(k)) {
          RefNbtBase obj = currentNbtCompound.get(k);
          // 属于RefNbtTagCompound
          if (obj instanceof RefNbtTagCompound) {
            currentNbtCompound = (RefNbtTagCompound) obj;
            // 不属于RefNbtTagCompound
          } else {
            // 如果需要强制设置
            if (force) {
              // new RefNbtTagCompound()并设置
              obj = new RefNbtTagCompound();
              if (SET_RETURN_SUPPORT) {
                currentNbtCompound.set1(key, obj);
              } else {
                currentNbtCompound.set0(key, obj);
              }
              currentNbtCompound = (RefNbtTagCompound) obj;
              // 不需要强制设置
            } else {
              // 返回null
              return null;
            }
          }
          // 不存在key
        } else {
          // 创建并设置
          RefNbtTagCompound obj = new RefNbtTagCompound();
          if (SET_RETURN_SUPPORT) {
            currentNbtCompound.set1(key, obj);
          } else {
            currentNbtCompound.set0(key, obj);
          }
          currentNbtCompound = obj;
        }
        // 已达末级
      } else {
        RefNbtBase previous = currentNbtCompound.get(key);
        if (SET_RETURN_SUPPORT) {
          currentNbtCompound.set1(key, value);
        } else {
          currentNbtCompound.set0(key, value);
        }
        return previous;
      }
    }
    return null;
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 如果设置前 NBT键 没有对应的 NBT值, 则返回 null.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置并返回 null.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置并返回 null.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   * @return 设置前与 NBT键 对应的 NBT值, 如果没有对应的值, 或 force 为 false 且对应的 key 无效, 则返回 null.
   */
  @SuppressWarnings("unused")
  @Nullable
  public Nbt<?> putDeepNbt(@NotNull String key, @NotNull Nbt<?> value, boolean force) {
    return fromNms(putDeepRefNbt(key, value.delegate, force));
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 如果设置前 NBT键 没有对应的 NBT值, 则返回 null.
   * 若对应的 key 无效, 则不进行设置并返回 null.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @return 设置前与 NBT键 对应的 NBT值, 如果没有对应的值, 或 force 为 false 且对应的 key 无效, 则返回 null.
   */
  @SuppressWarnings("unused")
  @Nullable
  public Nbt<?> putDeepNbt(@NotNull String key, @NotNull Nbt<?> value) {
    return fromNms(putDeepRefNbt(key, value.delegate, false));
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepByte(@NotNull String key, byte value, boolean force) {
    putDeepRefNbt(key, NbtByte.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepByte(@NotNull String key, byte value) {
    putDeepByte(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepShort(@NotNull String key, short value, boolean force) {
    putDeepRefNbt(key, NbtShort.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepShort(@NotNull String key, short value) {
    putDeepShort(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepInt(@NotNull String key, int value, boolean force) {
    putDeepRefNbt(key, NbtInt.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepInt(@NotNull String key, int value) {
    putDeepInt(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepLong(@NotNull String key, long value, boolean force) {
    putDeepRefNbt(key, NbtLong.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepLong(@NotNull String key, long value) {
    putDeepLong(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepFloat(@NotNull String key, float value, boolean force) {
    putDeepRefNbt(key, NbtFloat.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepFloat(@NotNull String key, float value) {
    putDeepFloat(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepDouble(@NotNull String key, double value, boolean force) {
    putDeepRefNbt(key, NbtDouble.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepDouble(@NotNull String key, double value) {
    putDeepDouble(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepString(@NotNull String key, @NotNull String value, boolean force) {
    putDeepRefNbt(key, NbtString.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepString(@NotNull String key, @NotNull String value) {
    putDeepString(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepByteArray(@NotNull String key, byte[] value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagByteArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepByteArray(@NotNull String key, byte[] value) {
    putDeepByteArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepByteArray(@NotNull String key, @NotNull List<Byte> value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagByteArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepByteArray(@NotNull String key, @NotNull List<Byte> value) {
    putDeepByteArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepIntArray(@NotNull String key, int[] value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagIntArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepIntArray(@NotNull String key, int[] value) {
    putDeepIntArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepIntArray(@NotNull String key, @NotNull List<Integer> value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagIntArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepIntArray(@NotNull String key, @NotNull List<Integer> value) {
    putDeepIntArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepLongArray(@NotNull String key, long[] value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagLongArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepLongArray(@NotNull String key, long[] value) {
    putDeepLongArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepLongArray(@NotNull String key, @NotNull List<Long> value, boolean force) {
    putDeepRefNbt(key, new RefNbtTagLongArray(value), force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepLongArray(@NotNull String key, @NotNull List<Long> value) {
    putDeepLongArray(key, value, false);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若 force 为 false 且对应的 key 无效, 则不进行设置.
   * 若 force 为 true 且对应的 key 无效, 则进行强制设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * 强制设置的例子: 你想要将 t1.t2.t3 设置为一个 1, t1.t2 的值不是 NbtCompound, 则强制将 t1.t2 的值设置为 new NbtCompound()
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   * @param force key 无效时是否强制设置.
   */
  public void putDeepBoolean(@NotNull String key, boolean value, boolean force) {
    putDeepRefNbt(key, NbtByte.valueOf(value).delegate, force);
  }

  /**
   * 将指定的 NBT键 设置为给定值.
   * 若对应的 key 无效, 则不进行设置.
   * key 无效的例子: 你想要将 t1.t2.t3 设置为一个 1, 这需要 t1.t2 的值是 NbtCompound, 如果 t1.t2 的值不是 NbtCompound, 则 key 无效.
   * NBT键 以 . 做分隔符.
   *
   * @param key 待设置的 NBT键.
   * @param value 待设置的 NBT键 的新值.
   */
  @SuppressWarnings("unused")
  public void putDeepBoolean(@NotNull String key, boolean value) {
    putDeepBoolean(key, value, false);
  }
}
