package at.skyparty.listener;

import at.skyparty.Main;
import at.skyparty.manager.CooldownManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SlowChatListener implements Listener {

    public static boolean isSlowed() {
        return slowed;
    }

    public static void setSlowed(boolean slowed) {
        SlowChatListener.slowed = slowed;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    private static boolean slowed = false;

    private final CooldownManager cooldownManager = new CooldownManager();

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event){

        if(isSlowed() && !event.getPlayer().hasPermission("sp.slowchat.bypass")){

            if(!getCooldownManager().isDone(event.getPlayer())){
                event.setCancelled(true);
                event.getPlayer().sendMessage(Main.getPrefix() + "Du kannst nur alle 3 Sekunden schreiben.");
            }else{
                getCooldownManager().addPlayerToCooldown(event.getPlayer(), 3);
            }

        }

    }


}