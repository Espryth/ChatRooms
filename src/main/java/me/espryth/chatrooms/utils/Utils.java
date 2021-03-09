package me.espryth.chatrooms.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
