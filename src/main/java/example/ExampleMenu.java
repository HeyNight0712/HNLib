package example;

import Heyblock0712.hNLib.data.HNNamespacedKey;
import Heyblock0712.hNLib.inventory.menu.Menu;
import Heyblock0712.hNLib.utils.ItemStackUtil;
import Heyblock0712.hNLib.inventory.menu.PlayerMenuUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExampleMenu extends Menu {

    public ExampleMenu(PlayerMenuUtil playerMenuUtil) {
        super(playerMenuUtil);
    }

    @Override
    public Component getMenuName() {
        return Component.text("Example Menu");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        // Write your inventory events
        ItemStack clickItem = event.getCurrentItem();
        if (clickItem == null) return;
        String key = ItemStackUtil.get(clickItem, HNNamespacedKey.MENUITEM.get());
        if (key == null) return;

        close();
        playerMenuUtil.getOwner().sendMessage("你關閉了選單");
    }

    @Override
    public void setMenuItem() {
        ItemStack itemExample = createMenuItem("example", Material.NAME_TAG);
        ItemMeta itemExampleMeta = itemExample.getItemMeta();
        itemExampleMeta.displayName(Component.text("Example Item"));
        itemExample.setItemMeta(itemExampleMeta);

        inventory.setItem(21, itemExample);
    }
}
