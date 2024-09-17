package Heyblock0712.hNLib.inventory.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected int index = 0;

    protected String left = "left_page";
    protected String right = "right_page";

    public PaginatedMenu(PlayerMenuUtil playerMenuUtil) {
        super(playerMenuUtil);
    }

    public abstract int getMaxItemPerPage();

    public ItemStack getLeftPageItem(Material material) {
        return MenuItem.createItem(left, material);
    }

    public ItemStack getRightPageItem(Material material) {
        return MenuItem.createItem(right, material);
    }
}
