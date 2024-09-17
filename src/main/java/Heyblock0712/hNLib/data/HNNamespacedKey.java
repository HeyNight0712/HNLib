package Heyblock0712.hNLib.data;

import Heyblock0712.hNLib.HNLib;
import org.bukkit.NamespacedKey;

public enum HNNamespacedKey {
    MENU;

    public static NamespacedKey get(HNNamespacedKey key) {
        return new NamespacedKey(HNLib.getInstance(), key.name());
    }
}
