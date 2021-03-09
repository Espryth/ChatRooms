package me.espryth.chatrooms.command;

import me.espryth.chatrooms.ChatRoomsPlugin;
import me.espryth.chatrooms.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final ChatRoomsPlugin plugin;

    public MainCommand(ChatRoomsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender.hasPermission("chatrooms.admin")) {
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("reload")) {
                    commandSender.sendMessage(Utils.colorize("&9[ChatRooms] &bConfiguration reloaded"));
                    plugin.getCore().getConfig().reload();
                    plugin.getCore().reloadPlugin();
                    return true;
                }
            }
        }
        commandSender.sendMessage("&9ChatRooms &f" + plugin.getDescription().getVersion() + " by Espryth");
        return true;
    }
}
