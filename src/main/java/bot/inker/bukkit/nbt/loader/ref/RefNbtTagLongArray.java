package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

import java.util.List;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagLongArray")
public final class RefNbtTagLongArray extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagLongArray;<init>([J)V")
  public RefNbtTagLongArray(long[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagLongArray;<init>(Ljava/util/List;)V")
  public RefNbtTagLongArray(List<Long> value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagLongArray;d()[J")
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagLongArray;getLongs()[J")
  public native long[] getLongs();
}
