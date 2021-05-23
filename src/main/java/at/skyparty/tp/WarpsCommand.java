package at.skyparty.tp;

import at.skyparty.Main;
import at.skyparty.manager.WarpManager;
import com.sun.istack.internal.NotNull;
import de.omel.api.fun.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        String out = "";
        for (Warp warp : new WarpManager().getWarps()) {
            out = "§a" + warp.getName() + "§7, " + out;
        }
        out = out.trim();
        if (out.isEmpty()) {
            player.sendMessage(Main.getPrefix() + "Es gibt §ckeine §7Warps.");
            return false;
        }
        player.sendMessage(Main.getPrefix() + "Verfügbare Warps » " + out);
        return false;
    }
}
