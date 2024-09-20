package example.command;

import Heyblock0712.hNLib.command.HNCommandGroup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ExampleCommandGroup extends HNCommandGroup {

    public ExampleCommandGroup(@NotNull JavaPlugin plugin) {
        super(plugin, "hnlib");
        registerSubCommand(new ExampleSubCommand("example"));
    }

    @Override
    public void onCommand(@NotNull CommandSender commandSender, @NotNull Command command) {
        if (commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage("This is just a test command");
        } else {
            commandSender.sendMessage("No permission");
        }
    }
}
