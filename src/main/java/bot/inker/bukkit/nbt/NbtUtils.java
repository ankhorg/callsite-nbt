package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.internal.ref.RefCraftItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefNbtBase;
import bot.inker.bukkit.nbt.internal.ref.RefNbtTagCompound;
import bot.inker.bukkit.nbt.internal.ref.RefNmsItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NbtUtils {
    public static @NotNull RefNbtTagCompound getOrCreateTag(@NotNull RefNmsItemStack itemStack) {
        if (itemStack.getTag() == null) {
            itemStack.setTag(new RefNbtTagCompound());
        }
        return itemStack.getTag();
    }

    /**
     * 使用给定的 RefNbtTagCompound 覆盖当前 RefNbtTagCompound.
     *
     * @param baseCompound 被覆盖的基础 RefNbtTagCompound.
     * @param overlayCompound 用于提供覆盖值的 RefNbtTagCompound.
     * @return baseCompound.
     */
    public static @NotNull RefNbtTagCompound coverWith(@NotNull RefNbtTagCompound baseCompound, @NotNull RefNbtTagCompound overlayCompound) {
        // 遍历附加NBT
        overlayCompound.tags.forEach((key, value) -> {
            // 如果二者包含相同键
            RefNbtBase overrideValue = baseCompound.tags.get(key);
            if (overrideValue != null) {
                // 如果二者均为COMPOUND
                if (overrideValue instanceof RefNbtTagCompound && value instanceof RefNbtTagCompound) {
                    // 合并
                    baseCompound.tags.put(key, coverWith((RefNbtTagCompound) overrideValue, (RefNbtTagCompound) value));
                    // 类型不一致
                } else {
                    // 覆盖
                    baseCompound.tags.put(key, value);
                }
                // 这个键原NBT里没有
            } else {
                // 添加
                baseCompound.tags.put(key, value);
            }
        });
        return baseCompound;
    }

    /**
     * 检测给定的 ItemStack 实例是否属于 CraftItemStack 子类.
     *
     * @return 检测结果.
     */
    public static boolean isCraftItemStack(@NotNull ItemStack itemStack) {
        return (Object) itemStack instanceof RefCraftItemStack;
    }
}
