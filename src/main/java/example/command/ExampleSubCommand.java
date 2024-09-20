package example.command;

import Heyblock0712.hNLib.command.HNSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExampleSubCommand extends HNSubCommand {

    public ExampleSubCommand(@NotNull String sublable) {
        super(sublable);
    }

    @Override
    public void onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length > 0) {
            String value = strings[0];
            commandSender.sendMessage("is " + value);
        } else {
            commandSender.sendMessage("example subcommand");
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();

        if (strings.length == 1) {
            list.add("subcommand");
        }

        return list;
    }
}
