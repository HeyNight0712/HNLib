package Heyblock0712.hNLib.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HNCommandGroup implements CommandExecutor, TabCompleter {
    private final @NotNull JavaPlugin plugin;
    private final @NotNull String lable;
    private final Map<String, HNSubCommand> subCommands =new HashMap<>();

    public HNCommandGroup(@NotNull JavaPlugin plugin, @NotNull String lable) {
        this.plugin = plugin;
        this.lable = lable;
    }

    public abstract void onCommand(@NotNull CommandSender commandSender, @NotNull Command command);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (strings.length > 0) {
            HNSubCommand subCommand = subCommands.get(strings[0].toLowerCase());
            if (subCommand != null) {
                String permission = getSubPermission(subCommand);
                if (commandSender.hasPermission(permission)) {
                    String[] args = removeFirstElement(strings);
                    subCommand.onCommand(commandSender, command, s, args);
                }
            } else {
                commandSender.sendMessage(Component.text("unknown command").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true));
            }
        } else {
            onCommand(commandSender, command);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> list = new ArrayList<>();

        if (strings.length == 1) {
            List<String> finalList = list;
            subCommands.forEach((subName, subCommand) -> {
                String permission = getSubPermission(subCommand);
                if (commandSender.hasPermission(permission)) {
                    finalList.add(subName.toLowerCase());
                }
            });

            list.addAll(finalList);
        }

        if (strings.length > 1) {
            HNSubCommand subCommand = subCommands.get(strings[0].toLowerCase());
            if (subCommand != null) {
                String permission = getSubPermission(subCommand);
                if (commandSender.hasPermission(permission)) {
                    list = subCommand.onTabComplete(commandSender, command, s, strings);
                }
            }
        }

        return list;
    }

    public void registerSubCommand(HNSubCommand subCommand) {
        subCommand.setLable(lable);
        subCommand.setPlugin(plugin);
        subCommands.put(subCommand.sublableName().toLowerCase(), subCommand);
    }

    public String getPermission() {
        return plugin.getName().toLowerCase() + ".command." + lable.toLowerCase();
    }

    public String getSubPermission(HNSubCommand subCommand) {
        return getPermission() + "." + subCommand.sublableName().toLowerCase();
    }

    private static String[] removeFirstElement(String[] strings) {
        if (strings == null || strings.length == 0) {
            return strings;
        }

        String[] newArray = new String[strings.length - 1];

        System.arraycopy(strings, 1, newArray, 0, newArray.length);

        return newArray;
    }
}
