package Heyblock0712.hNLib.data;

import Heyblock0712.hNLib.HNLib;
import org.bukkit.NamespacedKey;

public enum HNNamespacedKey {
    MENUITEM,
    SKULL;

    public NamespacedKey get() {
        return new NamespacedKey(HNLib.getInstance(), name());
    }
}
