package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagCompound")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/CompoundTag", remap = true)
public final class RefNbtTagCompound extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;<init>()V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;<init>()V", remap = true)
  public RefNbtTagCompound() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;set(Ljava/lang/String;Lnet/minecraft/server/v1_12_R1/NBTBase;)V")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "")
  public native void set0(String key, RefNbtBase value);

  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_14_R1/NBTTagCompound;set(Ljava/lang/String;Lnet/minecraft/server/v1_14_R1/NBTBase;)Lnet/minecraft/server/v1_14_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;put(Ljava/lang/String;Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;", remap = true)
  public native RefNbtBase set1(String key, RefNbtBase value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setByte(Ljava/lang/String;B)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putByte(Ljava/lang/String;B)V", remap = true)
  public native void setByte(String key, byte value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setShort(Ljava/lang/String;S)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putShort(Ljava/lang/String;S)V", remap = true)
  public native void setShort(String key, short value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setInt(Ljava/lang/String;I)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putInt(Ljava/lang/String;I)V", remap = true)
  public native void setInt(String key, int value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setLong(Ljava/lang/String;J)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putLong(Ljava/lang/String;J)V", remap = true)
  public native void setLong(String key, long value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;a(Ljava/lang/String;Ljava/util/UUID;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putUUID(Ljava/lang/String;Ljava/util/UUID;)V", remap = true)
  public native void setUUID(String key, UUID uuid);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setFloat(Ljava/lang/String;F)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putFloat(Ljava/lang/String;F)V", remap = true)
  public native void setFloat(String key, float value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setDouble(Ljava/lang/String;D)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putDouble(Ljava/lang/String;D)V", remap = true)
  public native void setDouble(String key, double value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setString(Ljava/lang/String;Ljava/lang/String;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putString(Ljava/lang/String;Ljava/lang/String;)V", remap = true)
  public native void setString(String key, String value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setByteArray(Ljava/lang/String;[B)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putByteArray(Ljava/lang/String;[B)V", remap = true)
  public native void setByteArray(String key, byte[] value);

  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putByteArray(Ljava/lang/String;Ljava/util/List;)V", remap = true)
  public native void setByteArray(String key, List<Byte> value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setIntArray(Ljava/lang/String;[I)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putIntArray(Ljava/lang/String;[I)V", remap = true)
  public native void setIntArray(String key, int[] value);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagCompound;b(Ljava/lang/String;Ljava/util/List;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putIntArray(Ljava/lang/String;Ljava/util/List;)V", remap = true)
  public native void setIntArray(String key, List<Integer> value);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagCompound;a(Ljava/lang/String;[J)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putLongArray(Ljava/lang/String;[J)V", remap = true)
  public native void setLongArray(String key, long[] value);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagCompound;c(Ljava/lang/String;Ljava/util/List;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putLongArray(Ljava/lang/String;Ljava/util/List;)V", remap = true)
  public native void setLongArray(String key, List<Long> value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;setBoolean(Ljava/lang/String;Z)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;putBoolean(Ljava/lang/String;Z)V", remap = true)
  public native void setBoolean(String key, boolean value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;get(Ljava/lang/String;)Lnet/minecraft/server/v1_12_R1/NBTBase;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;get(Ljava/lang/String;)Lnet/minecraft/nbt/Tag;", remap = true)
  public native RefNbtBase get(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;d(Ljava/lang/String;)B")
  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;e(Ljava/lang/String;)B")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;d(Ljava/lang/String;)B")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getTagType(Ljava/lang/String;)B", remap = true)
  public native byte getType(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;hasKey(Ljava/lang/String;)Z")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;contains(Ljava/lang/String;)Z", remap = true)
  public native boolean hasKey(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;hasKeyOfType(Ljava/lang/String;I)Z")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;contains(Ljava/lang/String;I)Z", remap = true)
  public native boolean hasKeyOfType(String key, int type);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getByte(Ljava/lang/String;)B")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getByte(Ljava/lang/String;)B", remap = true)
  public native byte getByte(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getShort(Ljava/lang/String;)S")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getShort(Ljava/lang/String;)S", remap = true)
  public native short getShort(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getInt(Ljava/lang/String;)I")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getInt(Ljava/lang/String;)I", remap = true)
  public native int getInt(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getLong(Ljava/lang/String;)J")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getLong(Ljava/lang/String;)J", remap = true)
  public native long getLong(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;a(Ljava/lang/String;)Ljava/util/UUID;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getUUID(Ljava/lang/String;)Ljava/util/UUID;", remap = true)
  public native UUID getUUID(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getFloat(Ljava/lang/String;)F")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getFloat(Ljava/lang/String;)F", remap = true)
  public native float getFloat(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getDouble(Ljava/lang/String;)D")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getDouble(Ljava/lang/String;)D", remap = true)
  public native double getDouble(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getString(Ljava/lang/String;)Ljava/lang/String;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getString(Ljava/lang/String;)Ljava/lang/String;", remap = true)
  public native String getString(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getByteArray(Ljava/lang/String;)[B")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getByteArray(Ljava/lang/String;)[B", remap = true)
  public native byte[] getByteArray(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getIntArray(Ljava/lang/String;)[I")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getIntArray(Ljava/lang/String;)[I", remap = true)
  public native int[] getIntArray(String key);

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagCompound;o(Ljava/lang/String;)[J")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_14_R1/NBTTagCompound;getLongArray(Ljava/lang/String;)[J")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getLongArray(Ljava/lang/String;)[J", remap = true)
  public native long[] getLongArray(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getCompound(Ljava/lang/String;)Lnet/minecraft/server/v1_12_R1/NBTTagCompound;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getCompound(Ljava/lang/String;)Lnet/minecraft/nbt/CompoundTag;", remap = true)
  public native RefNbtTagCompound getCompound(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getList(Ljava/lang/String;I)Lnet/minecraft/server/v1_12_R1/NBTTagList;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getList(Ljava/lang/String;I)Lnet/minecraft/nbt/ListTag;", remap = true)
  public native RefNbtTagList getList(String key, int var1);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;getBoolean(Ljava/lang/String;)Z")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getBoolean(Ljava/lang/String;)Z", remap = true)
  public native boolean getBoolean(String key);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;c()Ljava/util/Set;")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagCompound;getKeys()Ljava/util/Set;")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getAllKeys()Ljava/util/Set;", remap = true)
  public native Set<String> getKeys();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;d()I")
  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_15_R1/NBTTagCompound;e()I")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;getAllKeys()Ljava/util/Set;", remap = true)
  public native int size();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;isEmpty()Z")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;isEmpty()Z", remap = true)
  public native boolean isEmpty();

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagCompound;remove(Ljava/lang/String;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/CompoundTag;remove(Ljava/lang/String;)V", remap = true)
  public native void remove(String key);
}
