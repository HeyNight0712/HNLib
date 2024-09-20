package Heyblock0712.hNLib;

import Heyblock0712.hNLib.listeners.MenuListener;
import example.command.ExampleCommandGroup;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class HNLib extends JavaPlugin {
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        PluginCommand hnlibCommand = getCommand("hnlib");
        if (hnlibCommand != null) {
            hnlibCommand.setExecutor(new ExampleCommandGroup(this));
        }

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
