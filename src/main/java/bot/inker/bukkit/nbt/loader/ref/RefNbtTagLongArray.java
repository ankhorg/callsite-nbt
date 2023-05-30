package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

import java.util.List;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagLongArray")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/nbt/LongArrayTag", remap = true)
public final class RefNbtTagLongArray extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagLongArray;<init>([J)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/LongArrayTag;<init>([J)V", remap = true)
  public RefNbtTagLongArray(long[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagLongArray;<init>(Ljava/util/List;)V")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/LongArrayTag;<init>(Ljava/util/List;)V", remap = true)
  public RefNbtTagLongArray(List<Long> value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagLongArray;d()[J")
  @HandleBy(version = CbVersion.v1_14_R1, reference = "Lnet/minecraft/server/v1_14_R1/NBTTagLongArray;getLongs()[J")
  @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/nbt/LongArrayTag;getAsLongArray()[J", remap = true)
  public native long[] getLongs();
}
