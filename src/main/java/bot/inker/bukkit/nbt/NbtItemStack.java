package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtComponentLike;
import bot.inker.bukkit.nbt.internal.ref.RefBukkitItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefCraftItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefCraftMetaItem;
import org.bukkit.inventory.ItemStack;

public final class NbtItemStack {
  private final ItemStack itemStack;
  private final RefBukkitItemStack bukkitItemStack;
  private final RefCraftItemStack craftItemStack;

  public NbtItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    this.bukkitItemStack = (RefBukkitItemStack) (Object) itemStack;
    if ((Object) itemStack instanceof RefCraftItemStack) {
      this.craftItemStack = (RefCraftItemStack) (Object) itemStack;
    } else {
      this.craftItemStack = null;
    }
  }

  public NbtComponentLike getDirectTag() {
    if (craftItemStack == null) {
      return new NbtBukkitItemComponent(itemStack);
    } else {
      return new NbtCraftItemComponent(craftItemStack);
    }
  }

  public NbtCompound getTag() {
    if (craftItemStack == null) {
      RefCraftMetaItem meta = (RefCraftMetaItem) (Object) bukkitItemStack.meta;
      if (meta == null) {
        return new NbtCompound();
      } else {
        NbtCompound compound = new NbtCompound();
        meta.applyToItem(compound.delegate);
        return compound;
      }
    } else {
      return new NbtCompound(craftItemStack.handle.getTag());
    }
  }

  public void setTag(NbtCompound compound) {
    if (craftItemStack == null) {
      bukkitItemStack.meta = null;
      RefCraftItemStack craftItemStack = RefCraftItemStack.asCraftCopy(itemStack);
      craftItemStack.handle.setTag(compound.delegate);
      bukkitItemStack.meta = craftItemStack.getItemMeta();
    } else {
      craftItemStack.handle.setTag(compound.delegate);
    }
  }

  public ItemStack asBukkitCopy() {
    if (craftItemStack == null) {
      return itemStack.clone();
    } else {
      return RefCraftItemStack.asBukkitCopy(craftItemStack.handle);
    }
  }

  public ItemStack asCraftCopy() {
    if (craftItemStack == null) {
      return (ItemStack) (Object) RefCraftItemStack.asCraftCopy(itemStack);
    } else {
      return itemStack.clone();
    }
  }

  public ItemStack asCopy() {
    return itemStack.clone();
  }

  public boolean isBukkitItemStack() {
    return craftItemStack == null;
  }

  public boolean isCraftItemStack() {
    return craftItemStack != null;
  }

  public ItemStack asItemStack() {
    return itemStack;
  }

  @Override
  public NbtItemStack clone() throws CloneNotSupportedException {
    return new NbtItemStack(itemStack.clone());
  }
}
