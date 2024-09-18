package example;

import Heyblock0712.hNLib.HNLib;
import Heyblock0712.hNLib.data.HNNamespacedKey;
import Heyblock0712.hNLib.utils.ItemStackUtil;
import Heyblock0712.hNLib.inventory.menu.PaginatedMenu;
import Heyblock0712.hNLib.inventory.menu.PlayerMenuUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ExamplePaginatedMenu extends PaginatedMenu {
    protected NamespacedKey ownerKey = new NamespacedKey(HNLib.getInstance(), "owner");
    private final ArrayList<UUID> players = new ArrayList<>();

    public ExamplePaginatedMenu(PlayerMenuUtil playerMenuUtil) {
        super(playerMenuUtil);
    }

    @Override
    public int getMaxItemPerPage() {
        return 28;
    }

    @Override
    public Component getMenuName() {
        return Component.text("Example Paginated Menu");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        Player player = playerMenuUtil.getOwner();

        if (item == null) return;
        if (!ItemStackUtil.has(item, HNNamespacedKey.MENUITEM.get())) return;
        event.setCancelled(true);

        if (ItemStackUtil.has(item, ownerKey)) {
            String value = Objects.requireNonNull(ItemStackUtil.get(item, ownerKey), "未知");
            player.sendMessage(Component.text("你點擊了 " + value));
            close();
            return;
        }

        String key = ItemStackUtil.get(item, HNNamespacedKey.MENUITEM.get());
        if (key == null) return;

        if (key.equals(super.left)) {
            if (page == 0) {
                event.getWhoClicked().sendMessage("沒有第 0 頁");
            } else {
                page = page - 1;
                super.open();
            }
        } else if (key.equals(super.right)) {
            if (!((index + 1) >= players.size())) {
                page = page + 1;
                super.open();
            } else {
                event.getWhoClicked().sendMessage("這已經是最後一頁");
            }
        }
    }

    @Override
    public void setMenuItem() {
        ItemStack left = getLeftPageItem(Material.STONE_BUTTON);
        ItemStack right = getRightPageItem(Material.STONE_BUTTON);
        ItemStack fill = createFillItem(Material.BLACK_STAINED_GLASS_PANE);

        inventory.setItem(48, left);
        inventory.setItem(50, right);

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fill);
            }
        }

        inventory.setItem(17, fill);
        inventory.setItem(18, fill);
        inventory.setItem(26, fill);
        inventory.setItem(27, fill);
        inventory.setItem(35, fill);
        inventory.setItem(36, fill);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fill);
            }
        }

        for (int items = 0; items < 36; items++) {
            players.add(UUID.randomUUID());
        }

        for (int i = 0; i < getMaxItemPerPage(); i++) {
            index = getMaxItemPerPage() * page + i;
            if (index >= players.size()) break;
            if (players.get(index) != null) {
                String uuid = players.get(index).toString();

                ItemStack item = createMenuItem("player", Material.PLAYER_HEAD);
                ItemMeta meta = item.getItemMeta();
                meta.displayName(Component.text(uuid));
                meta.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, uuid);
                item.setItemMeta(meta);

                inventory.addItem(item);
            }
        }
    }
}
