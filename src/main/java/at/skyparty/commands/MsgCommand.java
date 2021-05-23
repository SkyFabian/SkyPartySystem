package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage(Main.getPrefix() + "Dieser Spieler ist §cnicht §7Online.");
                return true;
            }
            if(target instanceof Player) {
                String msg = "";
                for(int i = 1; i < args.length; i++) {
                    msg = msg + " " + args[i];
                }

                player.sendMessage(Main.getPrefix() + "§a" + player.getName() + "§7 ● §c" + target.getName() + "§7 ➜ " +msg);
                target.sendMessage(Main.getPrefix() + "§c" + player.getName() + "§7 ● §a" + player.getName() + "§7 ➜ " +msg);

            }
        } else {
            player.sendMessage(Main.getPrefix() + Main.use() + "§a/msg §7<Spieler> <Nachricht>");
        }

        return false;
    }
}
