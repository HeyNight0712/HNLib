package Heyblock0712.hNLib.inventory.menu;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class MenuItem {

    public static ItemStack createItem(Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        return item;
    }

    public static ItemStack createItem(Material material) {
        return createItem(material, 1);
    }
}
