package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        final Player player = ((Player) sender);

        if(player.hasPermission("sp.teleport.all")){

            if(args.length == 0){

                TeleportManager.sendRequestToAll(player);

            }else{
                player.sendMessage(Main.getPrefix() + Main.use() + "§a/tpaall");
            }

        }else{
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }


        return false;
    }
}

