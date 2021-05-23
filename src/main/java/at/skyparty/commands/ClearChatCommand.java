package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        if (sender.hasPermission("sp.cc")) {

            if (args.length == 0) {

                Bukkit.getOnlinePlayers().forEach(target -> {

                    if (target.hasPermission("sp.cc.bypass")) {
                        target.sendMessage(Main.getPrefix() + "§e§lDer Chat wurde soeben von §a" + sender.getName() + " §e§lgeleert.");
                    } else {
                        for (int i = 0; i < 100; i++) {
                            target.sendMessage(Main.getPrefix() + "");
                        }
                        target.sendMessage(Main.getPrefix() + "§e§lDer Chat wurde soeben von §a" + sender.getName() + " §e§lgeleert.");
                    }

                });

            } else {
                sender.sendMessage(Main.getPrefix() + Main.use() + "§a/cc");
            }


        } else {
            sender.sendMessage(Main.getPrefix() + Main.noPerm());
        }

        return false;
    }


}