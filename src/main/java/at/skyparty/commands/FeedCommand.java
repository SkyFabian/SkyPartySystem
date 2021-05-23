package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("sp.feed")) {
                if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage(Main.getPrefix() + "Du wurdest §agefüttert§7.");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        target.setHealth(20);
                        target.sendMessage(Main.getPrefix() + "Du wurdest §agefüttert§7.");
                        player.sendMessage(Main.getPrefix() + "Du hast §2" + target.getName() + " §agefüttert§7.");
                    } else
                        player.sendMessage(Main.getPrefix() + "§7Der Spieler §2" + args[0] + " §7ist §cnicht §7online.");
                } else
                    player.sendMessage(Main.getPrefix() + Main.use() + "§a/feed §7<Spieler>");
            } else
                player.sendMessage(Main.getPrefix() + Main.noPerm());
        } else
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
        return false;
    }
}