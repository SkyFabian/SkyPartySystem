package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    ArrayList<String> vanish = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (player.hasPermission("sp.vanish")) {
            if (args.length == 0) {
                if (vanish.contains(player.getName())) {
                    vanish.remove(player.getName());
                    player.sendMessage(Main.getPrefix() + "Du bist nun §cnicht §7mehr im §aVanish§7.");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(player);

                    }
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.hidePlayer(player);
                    }
                    vanish.add(player.getName());
                    player.sendMessage(Main.getPrefix() + "Du bist nun im §aVanish§7.");
                }
            } else if (args.length == 1) {

            } else {
                player.sendMessage(Main.getPrefix() + "Es ist ein §cFehler §7aufgetreten bitte versuche es erneut.");
            }
        } else {
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }
        return false;
    }
}
