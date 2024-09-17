package Heyblock0712.hNLib.inventory.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
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


}
