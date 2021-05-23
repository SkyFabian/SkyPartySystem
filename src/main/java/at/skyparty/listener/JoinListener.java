package at.skyparty.listener;

import at.skyparty.util.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage("");

        ConfigHandler configHandler = new ConfigHandler();

        if (configHandler.get("spawn.world") != null) {
            World world = Bukkit.getWorld(configHandler.get("spawn.world"));
            Double x = Double.parseDouble(configHandler.get("spawn.x"));
            Double y = Double.parseDouble(configHandler.get("spawn.y"));
            Double z = Double.parseDouble(configHandler.get("spawn.z"));
            Float yaw = Float.parseFloat(configHandler.get("spawn.yaw"));
            Float pitch = Float.parseFloat(configHandler.get("spawn.pitch"));

            Location loc = new Location(world, x, y, z, yaw, pitch);
            player.teleport(loc);
        }
    }
}
