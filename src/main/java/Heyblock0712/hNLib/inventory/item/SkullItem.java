package Heyblock0712.hNLib.inventory.item;

import Heyblock0712.hNLib.HNLib;
import Heyblock0712.hNLib.data.HNNamespacedKey;
import Heyblock0712.hNLib.utils.ItemStackUtil;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerTextures;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkullItem {
    private static final Map<String, ItemStack> skulls = new HashMap<>();

    public static ItemStack fromTextures(String textures) {
        String url = "http://textures.minecraft.net/texture/" + textures;

        if (skulls.containsKey(url)) {
            return skulls.get(url).clone();
        }

        return getSkull(url);
    }

    public static ItemStack fromBase64(String base64) {
        String url = getUrlFormBase64(base64);

        if (skulls.containsKey(url)) {
            return skulls.get(url).clone();
        }

        return getSkull(url);
    }

    public static String getUrlFormBase64(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        String json = new String(bytes, StandardCharsets.UTF_8);

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject texturesObject = jsonObject.getAsJsonObject("textures");
        JsonObject skinObject = texturesObject.getAsJsonObject("SKIN");
        return skinObject.get("url").getAsString();
    }

    private static ItemStack getSkull(String url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        PlayerProfile profile = Bukkit.createProfile(uuid);

        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(new URI(url).toURL());
        } catch (Exception e) {
            HNLib.getInstance().getLogger().warning("Failed to set skull textures for " + url);
            return null;
        }

        profile.setTextures(textures);
        skullMeta.setPlayerProfile(profile);
        skullMeta.getPersistentDataContainer().set(HNNamespacedKey.SKULL.get(), PersistentDataType.STRING, getTextures(url));
        skull.setItemMeta(skullMeta);

        skulls.put(getTextures(url), skull);
        return skull;
    }

    public static boolean has(ItemStack item) {
        return !ItemStackUtil.has(item, HNNamespacedKey.SKULL.get());
    }

    public static String get(ItemStack item) {
        return ItemStackUtil.get(item, HNNamespacedKey.SKULL.get());
    }

    private static String getTextures(String url) {
        String[] segments = url.split("/");
        return segments[segments.length - 1];
    }
}
