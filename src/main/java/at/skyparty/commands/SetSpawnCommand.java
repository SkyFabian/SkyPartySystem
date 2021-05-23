package at.skyparty.commands;

import at.skyparty.Main;
import at.skyparty.util.ConfigHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            ConfigHandler configHandler = new ConfigHandler();

            if(command.getName().equalsIgnoreCase("setspawn"));
            if(player.hasPermission("sp.setspawn")) {
                configHandler.set("spawn.world", player.getLocation().getWorld().getName());
                configHandler.set("spawn.x", String.valueOf(player.getLocation().getX()));
                configHandler.set("spawn.y", String.valueOf(player.getLocation().getY()));
                configHandler.set("spawn.z", String.valueOf(player.getLocation().getZ()));
                configHandler.set("spawn.yaw", String.valueOf(player.getLocation().getYaw()));
                configHandler.set("spawn.pitch", String.valueOf(player.getLocation().getPitch()));
                configHandler.save();
                player.sendMessage(Main.getPrefix() + "Spawn wurde gesetzt.");
            }else {
                player.sendMessage(Main.getPrefix() + Main.noPerm());
            }
        }

        return false;
    }
}
