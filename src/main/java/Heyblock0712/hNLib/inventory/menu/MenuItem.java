package Heyblock0712.hNLib.inventory.menu;

import Heyblock0712.hNLib.data.HNNamespacedKey;
import org.bukkit.Material;
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

    public static String getKey(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (!container.has(HNNamespacedKey.get(HNNamespacedKey.MENU), PersistentDataType.STRING)) {
            return null;
        }

        return container.get(HNNamespacedKey.get(HNNamespacedKey.MENU), PersistentDataType.STRING);
    }
}
