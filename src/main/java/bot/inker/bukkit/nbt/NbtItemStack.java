package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.ref.RefCraftItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagCompound;
import org.bukkit.inventory.ItemStack;

public class NbtItemStack {
    private final boolean bukkitItemStackMode;

    private final ItemStack itemStack;

    private final RefCraftItemStack craftItemStack;

    private final RefItemStack nmsItemStack;

    public NbtItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        if ((Object)itemStack instanceof RefCraftItemStack) {
            this.bukkitItemStackMode = false;
            this.craftItemStack = (RefCraftItemStack)(Object)itemStack;
            this.nmsItemStack = this.craftItemStack.handle;
        } else {
            this.bukkitItemStackMode = true;
            this.craftItemStack = RefCraftItemStack.asCraftCopy(itemStack);
            this.nmsItemStack = this.craftItemStack.handle;
        }
    }

    public boolean hasTag() {
        return nmsItemStack.hasTag();
    }

    public NbtCompound getTag() {
        return new NbtCompound(nmsItemStack.getTag());
    }

    public NbtCompound getTagClone() {
        return new NbtCompound(nmsItemStack.getTag() == null ? null : (RefNbtTagCompound)nmsItemStack.getTag().rClone());
    }

    public NbtCompound getOrCreateTag() {
        if (nmsItemStack.getTag() == null) {
            nmsItemStack.setTag(new RefNbtTagCompound());
            save();
        }

        return new NbtCompound(nmsItemStack.getTag());
    }

    public NbtCompound getOrCreateTagClone() {
        if (nmsItemStack.getTag() == null) {
            nmsItemStack.setTag(new RefNbtTagCompound());
            save();
        }

        return new NbtCompound((RefNbtTagCompound)nmsItemStack.getTag().rClone());
    }

    public void setTag(NbtCompound nbt) {
        nmsItemStack.setTag(nbt.delegate);
        save();
    }

    public void setTagClone(NbtCompound nbt) {
        nmsItemStack.setTag(nbt == null ? null : (RefNbtTagCompound)nbt.delegate.rClone());
        save();
    }

    public void save() {
        if (bukkitItemStackMode) {
            itemStack.setItemMeta(craftItemStack.getItemMeta());
        }
    }
}
