package at.skyparty.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Util {

    public static HashMap<Player, Player> chats = new HashMap<>();

    public static String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
