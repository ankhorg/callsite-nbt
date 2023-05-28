package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.HandleBy;
import bot.inker.bukkit.nbt.loader.annotation.CbVersion;

@HandleBy(version = CbVersion.v1_16_R3, reference = "net/minecraft/server/v1_16_R3/NBTTagString")
public class RefNbtTagString implements RefNbtBase {
  private RefNbtTagString() {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_16_R3, reference = "Lnet/minecraft/server/v1_16_R3/NBTTagString;a(Ljava/lang/String;)Lnet/minecraft/server/v1_16_R3/NBTTagString;")
  public native static RefNbtTagString of(String value);
}
