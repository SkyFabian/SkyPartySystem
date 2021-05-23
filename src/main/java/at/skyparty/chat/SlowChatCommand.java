package at.skyparty.chat;

import at.skyparty.Main;
import at.skyparty.listener.SlowChatListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlowChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        if(sender.hasPermission("sp.slowchat")){

            if(args.length == 0){

                if(SlowChatListener.isSlowed()){
                    SlowChatListener.setSlowed(false);
                    sender.sendMessage(Main.getPrefix() + "Du hast den Chat auf §anormal §7gestellt.");
                }else{
                    SlowChatListener.setSlowed(true);
                    sender.sendMessage(Main.getPrefix() + "Du hast den Chat auf §clangsam §7gestellt.");
                }

            }else{
                sender.sendMessage(Main.getPrefix() + Main.use() + "§a/slowchat");
            }

        }else {
            sender.sendMessage(Main.getPrefix() + Main.getPrefix());
        }
        return false;
    }


}
