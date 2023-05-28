package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagList")
public final class RefNbtTagList extends RefNbtList<RefNbtBase> {
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagList;<init>()V")
  public RefNbtTagList() {
    throw new UnsupportedOperationException();
  }
}