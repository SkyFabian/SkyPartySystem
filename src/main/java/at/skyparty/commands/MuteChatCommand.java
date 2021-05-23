package at.skyparty.commands;

import at.skyparty.Main;
import at.skyparty.listener.MuteChatListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChatCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("sp.mutechat")){

            if(args.length == 0){

                if(MuteChatListener.isMuted()){
                    MuteChatListener.setMuted(false);
                    sender.sendMessage(Main.getPrefix() + "Du hast den Chat §aentmuted§7.");
                }else{
                    MuteChatListener.setMuted(true);
                    sender.sendMessage(Main.getPrefix() + "Du hast den Chat §cgemuted§7.");
                }

            }else{
                sender.sendMessage(Main.getPrefix() + Main.use() + "§a/mutechat");
            }

        }else{
            sender.sendMessage(Main.getPrefix() + Main.noPerm());
        }

        return false;
    }


}

