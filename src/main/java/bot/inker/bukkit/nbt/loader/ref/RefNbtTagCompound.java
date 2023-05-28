package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagCompound")
public final class RefNbtTagCompound implements RefNbtBase {
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;<init>()V")
  public RefNbtTagCompound() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;set(Ljava/lang/String;Lnet/minecraft/server/v1_16_R3/NBTBase;)Lnet/minecraft/server/v1_16_R3/NBTBase;")
  public native RefNbtBase set(String key, RefNbtBase value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setByte(Ljava/lang/String;B)V")
  public native void setByte(String key, byte value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setShort(Ljava/lang/String;S)V")
  public native void setShort(String key, short value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setInt(Ljava/lang/String;I)V")
  public native void setInt(String key, int value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setLong(Ljava/lang/String;J)V")
  public native void setLong(String key, long value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;a(Ljava/lang/String;Ljava/util/UUID;)V")
  public native void setUUID(String key, UUID uuid);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setFloat(Ljava/lang/String;F)V")
  public native void setFloat(String key, float value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setDouble(Ljava/lang/String;D)V")
  public native void setDouble(String key, double value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setString(Ljava/lang/String;Ljava/lang/String;)V")
  public native void setString(String key, String value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setByteArray(Ljava/lang/String;[B)V")
  public native void setByteArray(String key, byte[] value);

  @Deprecated // missing in some version
  public native void setByteArray(String key, List<Byte> value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setIntArray(Ljava/lang/String;[I)V")
  public native void setIntArray(String key, int[] value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;b(Ljava/lang/String;Ljava/util/List;)V")
  public native void setIntArray(String key, List<Integer> value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;a(Ljava/lang/String;[J)V")
  public native void setLongArray(String key, long[] value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;c(Ljava/lang/String;Ljava/util/List;)V")
  public native void setLongArray(String key, List<Long> value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;setBoolean(Ljava/lang/String;Z)V")
  public native void setBoolean(String key, boolean value);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;get(Ljava/lang/String;)Lnet/minecraft/server/v1_16_R3/NBTBase;")
  public native RefNbtBase get(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;d(Ljava/lang/String;)B")
  public native byte getType(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;hasKey(Ljava/lang/String;)Z")
  public native boolean hasKey(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;hasKeyOfType(Ljava/lang/String;I)Z")
  public native boolean hasKeyOfType(String key, int type);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getByte(Ljava/lang/String;)B")
  public native byte getByte(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getShort(Ljava/lang/String;)S")
  public native short getShort(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getShort(Ljava/lang/String;)S")
  public native int getInt(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getLong(Ljava/lang/String;)J")
  public native long getLong(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;a(Ljava/lang/String;)Ljava/util/UUID;")
  public native UUID getUUID(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getFloat(Ljava/lang/String;)F")
  public native float getFloat(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getDouble(Ljava/lang/String;)D")
  public native double getDouble(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getString(Ljava/lang/String;)Ljava/lang/String;")
  public native String getString(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getByteArray(Ljava/lang/String;)[B")
  public native byte[] getByteArray(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getIntArray(Ljava/lang/String;)[I")
  public native int[] getIntArray(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getLongArray(Ljava/lang/String;)[J")
  public native long[] getLongArray(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getCompound(Ljava/lang/String;)Lnet/minecraft/server/v1_16_R3/NBTTagCompound;")
  public native RefNbtTagCompound getCompound(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getList(Ljava/lang/String;I)Lnet/minecraft/server/v1_16_R3/NBTTagList;")
  public native RefNbtTagList getList(String key, int var1);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getBoolean(Ljava/lang/String;)Z")
  public native boolean getBoolean(String key);

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;getKeys()Ljava/util/Set;")
  public native Set<String> getKeys();

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;e()I")
  public native int size();

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;isEmpty()Z")
  public native boolean isEmpty();

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagCompound;remove(Ljava/lang/String;)V")
  public native void remove(String key);
}
