package at.skyparty.listener;

import at.skyparty.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteChatListener implements Listener {

    public static boolean isMuted() {
        return muted;
    }

    public static void setMuted(boolean muted) {
        MuteChatListener.muted = muted;
    }

    private static boolean muted = false;

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event){

        if(isMuted() && !event.getPlayer().hasPermission("sp.mutechat.bypass")){
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.getPrefix() + "§7Der Chat ist zuzeit §cdeaktiviert.");
        }

    }

}