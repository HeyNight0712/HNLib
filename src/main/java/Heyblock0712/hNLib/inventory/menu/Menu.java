package Heyblock0712.hNLib.inventory.menu;

import Heyblock0712.hNLib.data.HNNamespacedKey;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected PlayerMenuUtil playerMenuUtil;

    public Menu(PlayerMenuUtil playerMenuUtil) {
        this.playerMenuUtil = playerMenuUtil;
    }

    public abstract Component getMenuName();
    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent event);

    public abstract void setMenuItem();

    public void open() {
        inventory = Bukkit.createInventory(this  , getSlots(), getMenuName());

        this.setMenuItem();

        playerMenuUtil.getOwner().openInventory(inventory);
    }

    public void close() {
        playerMenuUtil.getOwner().closeInventory();
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack createMenuItem(String id, Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(HNNamespacedKey.MENUITEM.get(), PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createMenuItem(String id, Material material) {
        return createMenuItem(id, material, 1);
    }

    public ItemStack createFillItem(Material material) {
        ItemStack item = createMenuItem("fill_item", material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(" "));
        item.setItemMeta(meta);
        return item;
    }
}
