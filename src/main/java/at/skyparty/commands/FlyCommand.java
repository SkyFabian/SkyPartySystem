package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    ArrayList<Player> fly = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }
        Player player = (Player) sender;
        if (fly.contains(player)) {
            player.setAllowFlight(false);
            player.setFlying(false);
            fly.remove(player);
            player.sendMessage(Main.getPrefix() + "Der Flugmodus wurde §cdeaktiviert§7.");
        } else {
            if (player.hasPermission("sp.fly")) {
                player.setAllowFlight(true);
                player.setFlying(true);
                fly.add(player);
                player.sendMessage(Main.getPrefix() + "Der Flugmodus wurde §aaktiviert§7.");
            } else if (!player.hasPermission("sp.fly")) {
                player.sendMessage(Main.getPrefix() + Main.noPerm());
            }
        }
        return false;
    }
}
