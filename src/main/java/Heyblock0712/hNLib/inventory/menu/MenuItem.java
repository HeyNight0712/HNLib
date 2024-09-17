package Heyblock0712.hNLib.inventory.menu;

import Heyblock0712.hNLib.data.HNNamespacedKey;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MenuItem {

    public static ItemStack createItem(String id, Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(HNNamespacedKey.get(HNNamespacedKey.MENU), PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(String id, Material material) {
        return createItem(id, material, 1);
    }

    public static ItemStack createFillItem(Material material) {
        ItemStack item = createItem("fill_item", material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(" "));
        item.setItemMeta(meta);
        return item;
    }

    public static String getKey(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (!hasKey(item)) {
            return null;
        }

        return container.get(HNNamespacedKey.get(HNNamespacedKey.MENU), PersistentDataType.STRING);
    }

    public static String getKey(ItemStack item, NamespacedKey key) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (!hasKey(item)) {
            return null;
        }

        return container.get(key, PersistentDataType.STRING);
    }

    public static boolean hasKey(ItemStack item, NamespacedKey key) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(key, PersistentDataType.STRING);
    }

    public static boolean hasKey(ItemStack item) {
        return hasKey(item, HNNamespacedKey.get(HNNamespacedKey.MENU));
    }
}
