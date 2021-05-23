package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }
        Player player=(Player) sender;
        if(player.hasPermission("sp.gm")) {
            if(args.length==1) {
                if(args[0].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(Main.getPrefix() + "Spielmodus gewechselt zu: §aÜberleben");
                } else if(args[0].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(Main.getPrefix() + "Spielmodus gewechselt zu: §4Kreativ");
                } else if(args[0].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(Main.getPrefix() + "Spielmodus gewechselt zu: §2Abenteuer");
                } else if(args[0].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(Main.getPrefix() + "Spielmodus gewechselt zu: §bZuschauer");
                }
            }else
                player.sendMessage(Main.getPrefix() + Main.use() + "§a/gm §7<1-3>");
        } else {
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }
        return false;
    }
}
