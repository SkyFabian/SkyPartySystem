package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        final Player player = ((Player) sender);

        if(args.length == 0){

            TeleportManager.acceptLastRequest(player);

        }else if(args.length == 1){

            TeleportManager.acceptRequest(player, args[0]);

        }else{
            player.sendMessage(Main.getPrefix() + Main.use() + "&a/tpaccept §7<Spieler>");
        }


        return false;
    }
}
