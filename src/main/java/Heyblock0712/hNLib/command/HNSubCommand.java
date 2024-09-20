package Heyblock0712.hNLib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class HNSubCommand {
    private final @NotNull String sublable;
    private String lable;
    private JavaPlugin plugin;

    public HNSubCommand(@NotNull String sublable) {
        this.sublable = sublable;
    }

    public abstract void onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings);

    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    };

    public void setLable(@NotNull String lable) {
        this.lable = lable;
    }

    public void setPlugin(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String sublableName() {return sublable;}

    public String getPermission() {
        if (lable == null) throw new RuntimeException("lable is Null");
        if (plugin == null) throw new RuntimeException("plugin is Null");
        return plugin.getName().toLowerCase() + ".command." + lable.toLowerCase() + "." + sublable.toLowerCase();}
}
