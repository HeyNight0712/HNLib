package Heyblock0712.hNLib.inventory;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
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
        byte[] bytes = Base64.getDecoder().decode(base64);
        String json = new String(bytes, StandardCharsets.UTF_8);

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject texturesObject = jsonObject.getAsJsonObject("textures");
        JsonObject skinObject = texturesObject.getAsJsonObject("SKIN");
        String url = skinObject.get("url").getAsString();

        if (skulls.containsKey(url)) {
            return skulls.get(url).clone();
        }

        return getSkull(url);
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
            e.printStackTrace();
            return null;
        }

        profile.setTextures(textures);
        skullMeta.setPlayerProfile(profile);
        skull.setItemMeta(skullMeta);

        skulls.put(getTextures(url), skull);
        return skull;
    }

    private static String getTextures(String url) {
        String[] segments = url.split("/");
        return segments[segments.length - 1];
    }
}
