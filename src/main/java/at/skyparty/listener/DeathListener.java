package at.skyparty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();
            if (event.getEntity().getKiller() != null) {
                if (event.getEntity().getKiller() instanceof Player) {
                    event.setDeathMessage(" ");
                } else {
                    event.setDeathMessage(" ");
                }
            } else {
                event.setDeathMessage(" ");
            }
        }
    }
}
