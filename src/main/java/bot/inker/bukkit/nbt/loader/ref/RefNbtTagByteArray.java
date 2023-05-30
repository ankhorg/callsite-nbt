package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

import java.util.List;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagByteArray")
public final class RefNbtTagByteArray extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagByteArray;<init>([B)V")
  public RefNbtTagByteArray(byte[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagByteArray;<init>(Ljava/util/List;)V")
  public RefNbtTagByteArray(List<Byte> value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagByteArray;c()[B")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagByteArray;getBytes()[B")
  public native byte[] getBytes();
}
