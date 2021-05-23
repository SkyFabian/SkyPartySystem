package at.skyparty.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveListener implements Listener {

    @EventHandler
    public  void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Location loc = player.getLocation();

        if(loc.add(0, -1 , 0).getBlock().getType().toString().equalsIgnoreCase("iron_block")) {
            Vector v = player.getLocation().getDirection().multiply(50).setY(3);

            player.setVelocity(v);
        }
    }
}
