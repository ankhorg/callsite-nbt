package bot.inker.bukkit.nbt.loader.ref;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/NBTTagString")
public final class RefNbtTagString extends RefNbtBase {
  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagString;<init>(Ljava/lang/String;)V")
  @HandleBy(version = CbVersion.v1_15_R1, reference = "")
  public RefNbtTagString(String value) {
    throw new UnsupportedOperationException();
  }

  @HandleBy(version = CbVersion.v1_15_R1, reference = "Lnet/minecraft/server/v1_15_R1/NBTTagString;a(Ljava/lang/String;)Lnet/minecraft/server/v1_15_R1/NBTTagString;")
  public native static RefNbtTagString of(String value);

  @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/NBTTagString;c_()Ljava/lang/String;")
  @HandleBy(version = CbVersion.v1_13_R2, reference = "Lnet/minecraft/server/v1_13_R2/NBTTagString;asString()Ljava/lang/String;")
  public native String asString();
}
