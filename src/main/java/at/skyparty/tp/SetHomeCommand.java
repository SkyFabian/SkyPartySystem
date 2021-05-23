package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.HomeManager;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;


            if (args.length == 0) {
                player.sendMessage(Main.getPrefix() + Main.use() + "§a/sethome §7<Name>");
                return true;
            }
            if (args.length == 1) {
                HomeManager manager = new HomeManager(player.getUniqueId());

                if (!(manager.getHomes().size() < 3 && player.hasPermission("sp.home.3"))) {
                    player.sendMessage(Main.getPrefix() + "Du darfst nur §c3 Homes §7gleichzeitig setzen.");
                    return true;
                }
                if (!(manager.getHomes().size() < 6 && player.hasPermission("sp.home.6"))) {
                    player.sendMessage(Main.getPrefix() + "Du darfst nur §c6 Homes §7gleichzeitig setzen.");
                    return true;
                }
                if (!(manager.getHomes().size() < 9 && player.hasPermission("sp.home.9"))) {
                    player.sendMessage(Main.getPrefix() + "Du darfst nur §c9 Homes §7gleichzeitig setzen.");
                    return true;
                }
                    manager.addHome(player.getLocation(), args[0]);

                    player.sendMessage(Main.getPrefix() + "Du hast das Home" + "§c " + args[0] + " §aerfolgreich §7gesetzt.");

                    return true;
                }


            return false;
        }
    }

