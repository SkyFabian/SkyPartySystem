package at.skyparty.chat;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamChatCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        final Player player = ((Player) sender);

        if(player.hasPermission("skyparty.teamchat")){

            if(args.length >= 1){
                StringBuilder stringBuilder = new StringBuilder();

                for(String arg : args){
                    stringBuilder.append(arg).append(" ");
                }

                sendTeamChat(player.getName(), stringBuilder.toString(), "skyparty.teamchat");

            }else{
                player.sendMessage(Main.getPrefix() + "§a/tc §7<Nachricht>");
            }

        }else{
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }

        return false;
    }

    private void sendTeamChat(final String fromName, final String message, final String perm){
        Bukkit.getOnlinePlayers().forEach(players -> {
            if(players.hasPermission(perm)){
                players.sendMessage("§2Team§7│ " + "§a" + fromName + " §7» §e" + message.replace('&', '§'));
            }
        });
    }

}
