package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagCompound;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NbtCompound extends Nbt<RefNbtTagCompound> {
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

  public Nbt<RefNbtBase> put(String key, Nbt<RefNbtBase> value) {
    return Nbt.fromNms(delegate.set(key, value.delegate));
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
    delegate.setByteArray(key, value);
  }

  public void putIntArray(String key, int[] value) {
    delegate.setIntArray(key, value);

  }

  public void putIntArray(String key, List<Integer> value) {
    delegate.setIntArray(key, value);
  }

  public void putLongArray(String key, long[] value) {
    delegate.setLongArray(key, value);
  }

  public void putLongArray(String key, List<Long> value) {
    delegate.setLongArray(key, value);
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
    return delegate.getLongArray(key);
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
}
