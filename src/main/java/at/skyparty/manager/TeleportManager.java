package at.skyparty.manager;

import at.skyparty.Main;
import at.skyparty.Teleport.EffectLib;
import at.skyparty.Teleport.TeleportType;
import at.skyparty.Teleport.TeleportUser;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TeleportManager implements Listener {

    public static HashMap<UUID, NavigableMap<String, TeleportUser>> getNewRequests() {
        return newRequests;
    }

    public static void setNewRequests(HashMap<UUID, NavigableMap<String, TeleportUser>> newRequests) {
        TeleportManager.newRequests = newRequests;
    }

    public static HashMap<UUID, Integer> getDelay() {
        return delay;
    }

    public static void setDelay(HashMap<UUID, Integer> delay) {
        TeleportManager.delay = delay;
    }

    private static HashMap<UUID, NavigableMap<String, TeleportUser>> newRequests = new HashMap<>();

    private static HashMap<UUID, Integer> delay = new HashMap<>();

    public static void sendRequest(Player sender, Player becommer, TeleportType teleportType){
        if(sender == becommer){
            sender.sendMessage(Main.getPrefix() + "§7Du kannst dir §cnicht selbst §7eine anfrage senden.");
            return;
        }

        if(newRequests.containsKey(becommer.getUniqueId())){
            NavigableMap<String, TeleportUser> list = newRequests.get(becommer.getUniqueId());
            if(list.containsKey(sender.getName())){
                TeleportUser teleportUser = list.get(sender.getName());
                if(teleportType == teleportUser.getTeleportType()){
                    sender.sendMessage(Main.getPrefix() + "§7Du hast diesem Spieler §cbereits §7eine anfrage gesendet.");
                    return;
                }
            }
        }
        newRequests.put(becommer.getUniqueId(), addToList(sender.getName(), new TeleportUser(sender.getName(), teleportType, sender.getLocation()), newRequests.get(becommer.getUniqueId())));
        sender.sendMessage(Main.getPrefix() + "§7Deine Teleportanfrage wurde gesendet.");
        if(teleportType == TeleportType.NORMAL){
            becommer.sendMessage(Main.getPrefix() + "§c" + sender.getName() + " §7möchte sich zu dir teleportieren.");
            becommer.spigot().sendMessage(getAcceptMessage(sender));
            return;
        }
        becommer.sendMessage(Main.getPrefix() + "§c" + sender.getName() + " §7möchte das du dich zu ihm telepotierst.");
        becommer.spigot().sendMessage(getAcceptMessage(sender));
    }

    public static void sendRequestToAll(Player sender){
        Bukkit.getOnlinePlayers().forEach(all -> {
            if(sender == all){
                return;
            }
            newRequests.put(all.getUniqueId(), addToList(sender.getName(), new TeleportUser(sender.getName(), TeleportType.AllHERE, sender.getLocation()), newRequests.get(all.getUniqueId())));
            all.sendMessage(Main.getPrefix() + "§c" + sender.getName() + " §7möchte das du dich zu ihm telepotierst.");
            all.spigot().sendMessage(getAcceptMessage(sender));
        });
        sender.sendMessage(Main.getPrefix() + "§7Du hast §aALLEN §7Spieler eine TpaHere anfrage gesendet.");
    }

    public static void acceptRequest(Player becommer, String acceptName){
        if(newRequests.containsKey(becommer.getUniqueId())) {
            NavigableMap<String, TeleportUser> list = newRequests.get(becommer.getUniqueId());
            if (list.containsKey(acceptName)) {
                TeleportUser teleportUser = list.get(acceptName);
                Player sender = Bukkit.getPlayer(teleportUser.getName());
                if (sender != null) {
                    if(teleportUser.getTeleportType() == TeleportType.AllHERE){
                        becommer.teleport(teleportUser.getHereLoc());
                        becommer.sendMessage(Main.getPrefix() + "Teleportiere...");
                        sender.sendMessage(Main.getPrefix() + "§c" + becommer.getName() + " §7hat deine Anfrage §aangenommen.");
                    }else{
                        startDelay(sender, becommer, teleportUser.getTeleportType(), teleportUser.getHereLoc());
                    }
                    list.remove(acceptName);
                    newRequests.put(becommer.getUniqueId(), list);
                } else {
                    list.remove(acceptName);
                    becommer.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht §7mehr online.");
                    newRequests.put(becommer.getUniqueId(), list);
                }
            } else {
                becommer.sendMessage(Main.getPrefix() + "§7Du hast keine anfrage von §c" + acceptName + "§c.");
            }
        }else{
            becommer.sendMessage(Main.getPrefix() + "§7Du hast keine anfrage von §c" + acceptName + "§c.");
        }
    }

    public static void acceptLastRequest(Player becommer){
        if(newRequests.containsKey(becommer.getUniqueId())) {
            NavigableMap<String, TeleportUser> list = newRequests.get(becommer.getUniqueId());
            if (!list.isEmpty()) {
                Map.Entry<String, TeleportUser> entry = list.firstEntry();
                Player sender = Bukkit.getPlayer(entry.getValue().getName());
                if (sender != null) {
                    if(entry.getValue().getTeleportType() == TeleportType.AllHERE){
                        becommer.teleport(entry.getValue().getHereLoc());
                        becommer.sendMessage(Main.getPrefix() + "Teleportiere...");
                        sender.sendMessage(Main.getPrefix() + "§c" + becommer.getName() + " §7hat deine Anfrage angenommen.");
                    }else{
                        startDelay(sender, becommer, entry.getValue().getTeleportType(), entry.getValue().getHereLoc());
                    }
                    list.clear();
                    newRequests.put(becommer.getUniqueId(), list);
                } else {
                    list.clear();
                    becommer.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht §7mehr online.");
                    newRequests.put(becommer.getUniqueId(), list);
                }
            } else {
                becommer.sendMessage(Main.getPrefix() + "§7Du hast §ckeine anfragen.");
            }
        }else {
            becommer.sendMessage(Main.getPrefix() + "§7Du hast §ckeine §7anfragen.");
        }
    }

    private static NavigableMap<String, TeleportUser> addToList(String name, TeleportUser teleportUser, NavigableMap<String, TeleportUser> list){
        if(list != null){
            list.put(name, teleportUser);
        }else{
            list = new TreeMap<>();
            list.put(name, teleportUser);
        }
        return list;
    }

    private static void startDelay(final Player sender, final Player becommer, final TeleportType teleportType, final Location hereLoc){

        if(teleportType == TeleportType.NORMAL){
            delay.put(sender.getUniqueId(), 60);
            sender.sendMessage(Main.getPrefix() + "§c" + becommer.getName() + " §7hat deine anfrage angenommen.");
            sender.sendMessage(Main.getPrefix() + "Bewege dich nicht, teleportation wurde gestartet.");
            becommer.sendMessage(Main.getPrefix() + "Du hast die anfrage von §c" + sender.getName() + " §7angenommen.");
        }else{
            delay.put(becommer.getUniqueId(), 60);
            becommer.sendMessage(Main.getPrefix() + "Du hast die anfrage von §c" + sender.getName() + " §7angenommen.");
            becommer.sendMessage(Main.getPrefix() + "Bewege dich nicht, teleportation wurde gestartet.");
            sender.sendMessage(Main.getPrefix() + "§c" + becommer.getName() + " §7hat dein anfrage angenommen.");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if(teleportType == TeleportType.NORMAL){
                    if(delay.containsKey(sender.getUniqueId())){
                        EffectLib.playParticleEffect(sender, delay.get(sender.getUniqueId()));

                        if(delay.get(sender.getUniqueId()) == 0){
                            delay.remove(sender.getUniqueId());
                            sender.teleport(becommer);
                            sender.sendMessage(Main.getPrefix() + "Teleportiere...");
                            cancel();
                            return;
                        }
                        delay.put(sender.getUniqueId(), delay.get(sender.getUniqueId())-1);
                    }else{
                        sender.sendMessage(Main.getPrefix() + "§7Teleport §7abgebrochen.");
                        cancel();
                    }
                }else{
                    if(delay.containsKey(becommer.getUniqueId())){
                        EffectLib.playParticleEffect(becommer, delay.get(becommer.getUniqueId()));
                        if(delay.get(becommer.getUniqueId()) == 0){
                            delay.remove(becommer.getUniqueId());
                            becommer.teleport(hereLoc);
                            becommer.sendMessage(Main.getPrefix() + "Teleportiere...");
                            cancel();
                            return;
                        }
                        delay.put(becommer.getUniqueId(), delay.get(becommer.getUniqueId())-1);
                    }else{
                        becommer.sendMessage(Main.getPrefix() + "§7Teleport §cabgebrochen§7.");
                        cancel();
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, 1);

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(delay.containsKey(event.getPlayer().getUniqueId())){
            if(hasMoved(event)){
                delay.remove(event.getPlayer().getUniqueId());
            }
        }
    }

    private boolean hasMoved(PlayerMoveEvent event){
        int x = (int) event.getFrom().getX();
        int y = (int) event.getFrom().getY();
        int z = (int) event.getFrom().getZ();
        int newX = (int) event.getTo().getX();
        int newY = (int) event.getTo().getY();
        int newZ = (int) event.getTo().getZ();
        return (x != newX) || (y != newY) || (z != newZ);
    }

    private static TextComponent getClickableMessage(String message,  String hover, String command){
        TextComponent textComponent = new TextComponent(message);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        return textComponent;
    }

    private static TextComponent getAcceptMessage(Player sender){

        TextComponent textComponent = new TextComponent(Main.getPrefix() + Main.use());
        textComponent.addExtra(getClickableMessage( "§a/tpaccept", "§7[§aKlicke hier um anzunehmen§7]", "/tpaccept " + sender.getName()));
        textComponent.addExtra(" §7um die anfrage anzunehmen.");

        return textComponent;
    }

}

