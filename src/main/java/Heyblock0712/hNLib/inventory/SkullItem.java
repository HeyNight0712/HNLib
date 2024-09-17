package Heyblock0712.hNLib.inventory;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkullItem {
    private static final Map<String, ItemStack> skulls = new HashMap<>();

    public static ItemStack fromTextures(String textureHash) {

        if (skulls.containsKey(textureHash)) {
            return skulls.get(textureHash).clone();
        }

        String url = "http://textures.minecraft.net/texture/" + textureHash;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        PlayerProfile profile = Bukkit.createProfile(uuid);

        PlayerTextures textures = profile.getTextures();
        try {
            textures.setSkin(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        profile.setTextures(textures);
        skullMeta.setPlayerProfile(profile);
        skull.setItemMeta(skullMeta);

        skulls.put(textureHash, skull);

        return skull;
    }
}
