package Heyblock0712.hNLib.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemStackUtil {

    private static boolean hasItemMeta(ItemStack item) {
        return item != null && item.hasItemMeta();
    }

    public static <P, C> boolean has(ItemStack item, NamespacedKey key, PersistentDataType<P, C> type) {
        if (!hasItemMeta(item)) {return false;}
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().has(key, type);
    }

    public static boolean has(ItemStack item, NamespacedKey key) {
        if (!hasItemMeta(item)) {return false;}
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().has(key);
    }

    public static <P, C> C get(ItemStack item, NamespacedKey key, PersistentDataType<P, C> type) {
        if (!hasItemMeta(item)) {return null;}
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().get(key, type);
    }

    public static String get(ItemStack item, NamespacedKey key) {
        if (!hasItemMeta(item)) {return null;}
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }
}
