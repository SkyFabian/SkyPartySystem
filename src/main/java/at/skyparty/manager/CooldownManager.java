package at.skyparty.manager;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    private final HashMap<UUID, Long> cooldowns;
    private String bypassPerm = null;

    public CooldownManager(final String bypassPerm){
        cooldowns = Maps.newHashMap();
        this.bypassPerm = bypassPerm;
    }

    public CooldownManager(){
        cooldowns = Maps.newHashMap();
    }

    public CooldownManager addPlayerToCooldown(final UUID uuid, final Integer seconds){
        cooldowns.put(uuid, System.currentTimeMillis() + (seconds*1000));
        return this;
    }

    public CooldownManager removePlayerFromCooldown(final UUID uuid){
        cooldowns.remove(uuid);
        return this;
    }

    public boolean isDone(final UUID uuid){
        if(cooldowns.containsKey(uuid)){
            return cooldowns.get(uuid) <= System.currentTimeMillis();
        }else{
            return true;
        }
    }

    public Integer getReminding(final UUID uuid){
        if(cooldowns.containsKey(uuid)){
            return Math.toIntExact((cooldowns.get(uuid) - System.currentTimeMillis()) / 1000);
        }else{
            return null;
        }
    }

    public CooldownManager addPlayerToCooldown(final Player player, final Integer seconds){
        if(bypassPerm != null){
            if(!player.hasPermission(this.bypassPerm)){
                addPlayerToCooldown(player.getUniqueId(), seconds);
            }
        }else{
            addPlayerToCooldown(player.getUniqueId(), seconds);
        }
        return this;
    }

    public CooldownManager removePlayerFromCooldown(final Player player){
        removePlayerFromCooldown(player.getUniqueId());
        return this;
    }

    public boolean isDone(final Player player){
        return isDone(player.getUniqueId());
    }

    public Integer getReminding(final Player player){
        return getReminding(player.getUniqueId());
    }


}

