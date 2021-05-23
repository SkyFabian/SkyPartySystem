package at.skyparty.commands;

import at.skyparty.Main;
import at.skyparty.util.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            ConfigHandler c = new ConfigHandler();

            if(command.getName().equalsIgnoreCase("spawn"));
            World world = Bukkit.getWorld(c.get("spawn.world"));
            Double x = Double.parseDouble(c.get("spawn.x"));
            Double y = Double.parseDouble(c.get("spawn.y"));
            Double z = Double.parseDouble(c.get("spawn.z"));
            Float yaw = Float.parseFloat(c.get("spawn.yaw"));
            Float pitch = Float.parseFloat(c.get("spawn.pitch"));

            Location loc = new Location(world,x,y,z,yaw,pitch);
            player.teleport(loc);
            player.sendMessage(Main.getPrefix() + "Du wurdest zum §aSpawn §7teleportiert.");

        }

        return false;
    }
}
