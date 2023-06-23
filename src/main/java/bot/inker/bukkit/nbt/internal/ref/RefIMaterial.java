package bot.inker.bukkit.nbt.internal.ref;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "")
@HandleBy(version = CbVersion.v1_13_R1, reference = "net/minecraft/server/v1_13_R2/IMaterial")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/world/level/ItemLike")
public interface RefIMaterial {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "")
    @HandleBy(version = CbVersion.v1_13_R1, reference = "Lnet/minecraft/server/v1_16_R3/IMaterial;getItem()Lnet/minecraft/server/v1_16_R3/Item;")
    @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/world/level/ItemLike;asItem()Lnet/minecraft/world/item/Item;")
    RefItem getItem();
}
