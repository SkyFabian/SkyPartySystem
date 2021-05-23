package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.HomeManager;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        HomeManager manager = new HomeManager(player.getUniqueId());

        if (args.length == 0) {
            player.sendMessage(Main.getPrefix() + Main.use() + "§a/delhome §7<Name>");
            return true;
        }
        if (args.length == 1) {
            if (!manager.exist(args[0])) {
                player.sendMessage(Main.getPrefix() + "Dieses Home existiert §cnicht§7.");
                return true;
            }
            manager.delHome(args[0]);
            player.sendMessage(Main.getPrefix() + "Das Home" + "§c " + args[0] + " §7wurde §aerfolgreich §7gelöscht.");
            return true;
        }
        return false;
    }
}
