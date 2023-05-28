package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTBase", isInterface = true)
public interface RefNbtBase {
  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTBase;getTypeId()B", isInterface = true)
  default byte getTypeId() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTBase;clone()Lnet/minecraft/server/v1_16_R3/NBTBase;", isInterface = true)
  default RefNbtBase rClone() {
    throw new UnsupportedOperationException();
  }
}
