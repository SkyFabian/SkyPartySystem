package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.Teleport.TeleportType;
import at.skyparty.manager.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        final Player player = ((Player) sender);

        if(args.length == 1){

            final Player target = Bukkit.getPlayer(args[0]);

            if(target != null){

                TeleportManager.sendRequest(player, target, TeleportType.NORMAL);

            }else{
                player.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht §7online.");
            }

        }else{
            player.sendMessage(Main.getPrefix() + Main.use() + "§a/tpa §7<Spieler>");
        }



        return false;
    }
}
