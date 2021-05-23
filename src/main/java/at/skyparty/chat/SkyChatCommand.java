package at.skyparty.chat;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkyChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + "Du musst ein Spieler sein.");
            return false;
        }

        final Player player = ((Player) sender);

        if(player.hasPermission("skyparty.skychat")){

            if(args.length >= 1){
                StringBuilder stringBuilder = new StringBuilder();

                for(String arg : args){
                    stringBuilder.append(arg).append(" ");
                }

                sendTeamChat(player.getName(), stringBuilder.toString(), "skyparty.skychat");

            }else{
                player.sendMessage(Main.getPrefix() + Main.use() + "§a/sky §7<Nachricht>");
            }

        }else{
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }

        return false;
    }

    private void sendTeamChat(final String fromName, final String message, final String perm){
        Bukkit.getOnlinePlayers().forEach(players -> {
            if(players.hasPermission(perm)){
                players.sendMessage(("§9S§bK§9Y§bc§9h§ba§9t§7│ " + "§3" + fromName + "§7 » §e" + message.replace('&', '§')));
            }
        });
    }

}
