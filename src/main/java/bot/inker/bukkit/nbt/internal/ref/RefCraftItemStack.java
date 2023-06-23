package bot.inker.bukkit.nbt.internal.ref;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@HandleBy(version = CbVersion.v1_12_R1, reference = "org/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack")
public class RefCraftItemStack {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "")
    public RefCraftItemStack(RefItemStack item) {
        throw new UnsupportedOperationException();
    }

//    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;<init>(Lorg/bukkit/inventory/ItemStack;)V")
    @HandleBy(version = CbVersion.v1_12_R1, reference = "")
    public RefCraftItemStack(ItemStack item) {
        throw new UnsupportedOperationException();
    }

    @HandleBy(version = CbVersion.v1_12_R1, reference = "")
    public RefCraftItemStack(Material type, int amount, short durability, ItemMeta itemMeta) {
        throw new UnsupportedOperationException();
    }

    @HandleBy(version = CbVersion.v1_12_R1, reference = "")
    public RefCraftItemStack(int typeId, int amount, short durability, ItemMeta itemMeta) {
        throw new UnsupportedOperationException();
    }

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asNMSCopy(Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/server/v1_12_R1/ItemStack")
    public static native RefItemStack asNMSCopy(ItemStack original);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;copyNMSStack(Lnet/minecraft/server/v1_12_R1/ItemStack;I)Lnet/minecraft/server/v1_12_R1/ItemStack")
    public static native RefItemStack copyNMSStack(RefItemStack original, int amount);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asBukkitCopy(Lnet/minecraft/server/v1_12_R1/ItemStack;)Lorg/bukkit/inventory/ItemStack")
    public static native ItemStack asBukkitCopy(RefItemStack original);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asCraftMirror(Lnet/minecraft/server/v1_12_R1/ItemStack;)Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack")
    public static native RefCraftItemStack asCraftMirror(RefItemStack original);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asCraftCopy(Lorg/bukkit/inventory/ItemStack;)Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack")
    public static native RefCraftItemStack asCraftCopy(ItemStack original);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asNewCraftStack(Lnet/minecraft/server/v1_12_R1/Item;)Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack")
    public static native RefCraftItemStack asNewCraftStack(RefItem original);

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack;asNewCraftStack(Lnet/minecraft/server/v1_12_R1/Item;I)Lorg/bukkit/craftbukkit/v1_12_R1/inventory/CraftItemStack")
    public static native RefCraftItemStack asNewCraftStack(RefItem original, int amount);
}
