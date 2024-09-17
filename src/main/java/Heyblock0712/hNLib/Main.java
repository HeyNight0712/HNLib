package Heyblock0712.hNLib;

import Heyblock0712.hNLib.command.HNLibCommand;
import Heyblock0712.hNLib.listeners.MenuListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginCommand hnlibCommand = getCommand("hnlib");
        if (hnlibCommand != null) {
            hnlibCommand.setExecutor(new HNLibCommand());
        }

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
