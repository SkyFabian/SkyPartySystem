package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.HomeManager;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        HomeManager manager = new HomeManager(player.getUniqueId());

        player.hasPermission("sp.home");
        {

            if (args.length == 0) {
                if (manager.getHomes().size() == 0) {
                    player.sendMessage(Main.getPrefix() + "Du hast §ckein §7Home.");
                    return true;
                }
                String out = "";
                for (String s : manager.getHomes()) {
                    out = "§c" + s + "§7, " + out;

                }
                out = out.trim();
                out = out.substring(0, out.length() - 1);

                if(manager.getHomes().size() > 1)
                {
                    player.sendMessage(Main.getPrefix() + "Deine Homes: " + out);
                }
                else {
                    player.sendMessage(Main.getPrefix() + "Dein Home: " + out);
                }

                return true;
            }

            if (args.length == 1) {
                if (!manager.exist(args[0])) {
                    player.sendMessage(Main.getPrefix() + "Dieses Home existiert §cnicht§7.");
                    return true;
                }

                player.teleport(manager.getLocation(args[0]));
                player.sendMessage(Main.getPrefix() + "Du wurdest zu deinem Home" +"§c " + args[0] + " §7teleportiert.");
                return true;
            }

        }

        return false;
    }
}
