package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.MinecraftVersion;

import java.util.List;

@HandleBy(version = MinecraftVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagByteArray")
public final class RefNbtTagByteArray extends RefNbtList<RefNbtTagByte> {
  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByteArray;<init>([B)V")
  public RefNbtTagByteArray(byte[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = MinecraftVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByteArray;<init>(Ljava/util/List;)V")
  public RefNbtTagByteArray(List<Byte> value) {
    throw new UnsupportedOperationException();
  }
}
