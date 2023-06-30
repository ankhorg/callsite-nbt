package bot.inker.bukkit.nbt.internal.ref;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;
import java.util.Set;

@HandleBy(version = CbVersion.v1_12_R1, reference = "org/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem")
public final class RefCraftMetaItem {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;applyToItem(Lnet/minecraft/server/v1_12_R1/NBTTagCompound;)V")
    public native void applyToItem(RefNbtTagCompound itemTag);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;internalTag:Lnet/minecraft/server/v1_12_R1/NBTTagCompound;")
    public native RefNbtTagCompound internalTag();

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;unhandledTags:Ljava/util/Map;")
    public native Map<String, RefNbtBase> unhandledTags();

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;enchantments:Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem$EnchantmentMap;", accessor = true)
    public native Map<Enchantment, String> enchantments();

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftMetaItem;HANDLED_TAGS:Ljava/util/Set;", accessor = true)
    public static native Set<String> HANDLED_TAGS();
}
