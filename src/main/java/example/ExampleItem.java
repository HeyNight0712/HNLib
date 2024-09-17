package example;

import Heyblock0712.hNLib.inventory.item.SkullItem;
import org.bukkit.inventory.ItemStack;

public class ExampleItem {
    private ExampleItem() {}

    private void getSkull() {

        // EX https://minecraft-heads.com/custom-heads/head/102896-cake
        ItemStack skullTextures = SkullItem.fromTextures("5e2c6fdb388a519856ba73760159894d31ad71a279eaf34b91ebb187c25cde51");

        ItemStack skullBase64 = SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWUyYzZmZGIzODhhNTE5ODU2YmE3Mzc2MDE1OTg5NGQzMWFkNzFhMjc5ZWFmMzRiOTFlYmIxODdjMjVjZGU1MSJ9fX0=");

    }
}
