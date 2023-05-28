package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

import java.util.List;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagIntArray")
public class RefNbtTagIntArray extends RefNbtList<RefNbtTagInt> {
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagIntArray;<init>([I)V")
  public RefNbtTagIntArray(int[] value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagIntArray;<init>(Ljava/util/List;)V")
  public RefNbtTagIntArray(List<Integer> value) {
    throw new UnsupportedOperationException();
  }
}
