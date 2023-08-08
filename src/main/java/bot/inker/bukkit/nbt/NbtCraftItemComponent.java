package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtComponentLike;
import bot.inker.bukkit.nbt.internal.ref.RefCraftItemStack;

public final class NbtCraftItemComponent extends NbtCompound implements NbtComponentLike {
  NbtCraftItemComponent(RefCraftItemStack itemStack) {
    super(NbtUtils.getOrCreateTag(itemStack.handle));
  }
}
