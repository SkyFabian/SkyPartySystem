package at.skyparty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
    private static final int LINES = 4;

    public SignChangeListener() {
    }

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("sp.color.sign")) {
            for(int i = 0; i < LINES; ++i) {
                e.setLine(i, e.getLine(i).replace('&', '§'));
            }

        }
    }
}
