package Heyblock0712.hNLib.inventory.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuManager {
    private static final HashMap<Player, PlayerMenuUtil> playerMenuUtilMap = new HashMap<>();

    public static PlayerMenuUtil getPlayerMenuUtil(Player player) {
        PlayerMenuUtil playerMenuUtil;

        if (playerMenuUtilMap.containsKey(player)) {
            return playerMenuUtilMap.get(player);
        } else {
            playerMenuUtil = new PlayerMenuUtil(player);
            playerMenuUtilMap.put(player, playerMenuUtil);
            return playerMenuUtil;
        }
    }
}
