package at.skyparty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.HashMap;

public class PlayerChatEvent implements Listener {

    public PlayerChatEvent() {
    }

    HashMap<Player, Long> spam = new HashMap<Player, Long>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("sp.color.chat")) {
            event.setMessage(event.getMessage().replace('&', '§'));
        }

        String Message = event.getMessage();
        Message.replace("%", "%%");

        if (PermissionsEx.getUser(player).inGroup("default")) {
          event.setFormat("§7Spieler§7 ┃ §7" + player.getName() + "§7 » " + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Premium")) {
            event.setFormat("§6Premium§7 ┃ §6" + player.getName() + "§7 » §6" + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("SKY")) {
            event.setFormat("§9SKY§7 ┃ §9" + player.getName() + "§7 » §9" + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Media")) {
            event.setFormat("§5Media§7 ┃ §5" + player.getName() + "§7 » §5" + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Content")) {
            event.setFormat("§eContent§7 ┃ §e" + player.getName() + "§7 » §a§l " + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Builder")) {
            event.setFormat("§eBuilder§7 ┃ §e" + player.getName() + "§7 » §a§l " + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Supporter")) {
               event.setFormat("§aSupporter§7 ┃ §a" + player.getName() + "§7 » §a§l " + Message);
             }
        if (PermissionsEx.getUser(player).inGroup("Developer")) {
            event.setFormat("§bDeveloper§7 ┃ §b" + player.getName() + "§7 » §a§l " + Message);
         }
        if (PermissionsEx.getUser(player).inGroup("Moderator")) {
            event.setFormat("§2Moderator§7 ┃ §2" + player.getName() + "§7 » §a§l " + Message);
        }
        if (PermissionsEx.getUser(player).inGroup("Admin")) {
            event.setFormat("§cAdmin§7 ┃ §c" + player.getName() + "§7 » §a§l " + Message);
         }
         if (PermissionsEx.getUser(player).inGroup("HeadAdmin")) {
             event.setFormat("§4HeadAdmin §7┃ §4" + player.getName() + "§7 » §a§l" + Message);
         }
        if (PermissionsEx.getUser(player).inGroup("Owner")) {
            event.setFormat("§4§lOwner §7┃ §4" + player.getName() + "§7 » §a§l" + Message);
        }
    }
}
