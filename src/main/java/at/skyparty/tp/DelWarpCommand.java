package at.skyparty.tp;

import at.skyparty.Main;
import com.sun.istack.internal.NotNull;
import de.omel.api.fun.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        if (!player.hasPermission("sp.delwarp")) return true;
        if (args.length == 0) {
            player.sendMessage(Main.getPrefix() + Main.use() + "§a/delwarp §7<Name>");
            return true;
        }
        if(args.length == 1) {
            Warp warp = new Warp(args[0]);
            if (!warp.exist()){
                player.sendMessage(Main.getPrefix() + "Dieser Warp existiert §cnicht§7.");
                return true;
            }
            warp.remove();
            player.sendMessage(Main.getPrefix() + "Du hast den Warp §aerfolgreich §7gelöscht.");
            return true;
        }
        return false;
    }
}
