package bot.inker.bukkit.nbt.internal.ref;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "org/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem")
public final class RefCraftMetaItem {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;applyToItem(Lnet/minecraft/server/v1_12_R1/NBTTagCompound;)V")
    native void applyToItem(RefNbtTagCompound itemTag);
}
