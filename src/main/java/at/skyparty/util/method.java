package at.skyparty.util;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class method {

    public static void teleport(final Player sender, final String[] args){

        Player player = sender;

        switch (args.length) {
            case 1:
                if (!player.hasPermission("sp.teleport")) {
                    player.sendMessage(Main.getPrefix() + Main.noPerm());
                    return;
                }
                final Player target1 = Bukkit.getPlayer(args[0]);
                if (target1 != null) {
                    player.teleport(target1);
                    player.sendMessage(Main.getPrefix() + "§7Du hast dich zu §c" + target1.getName() + " §7teleportiert.");
                } else {
                    player.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht &7online.");
                }
                break;
            case 2:
                if (!player.hasPermission("sp.teleport.others")) {
                    player.sendMessage(Main.getPrefix() + Main.noPerm());
                    return;
                } else {
                    player.sendMessage(Main.getPrefix() + Main.noPerm());
                }
                final Player target2 = Bukkit.getPlayer(args[0]);
                final Player target3 = Bukkit.getPlayer(args[1]);
                if ((target2 != null) && (target3 != null)) {
                    target2.teleport(target3);
                    target2.sendMessage(Main.getPrefix() + "§7Du wurdest zu §c" + target3.getName() + " §7teleportiert.");
                    player.sendMessage(Main.getPrefix() + "§7Du hast §c" + target2.getName() + " §7zu §c" + target3.getName() + " §7teleportiert.");
                } else {
                    player.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht §7online.");
                }
                break;
            case 3:
                if (!player.hasPermission("sp.teleport.position")) {
                    player.sendMessage(Main.getPrefix() + Main.noPerm());
                    return;
                }
                final Double x2;
                final Double y2;
                final Double z2;
                try {
                    x2 = args[0].startsWith("~") ? player.getLocation().getX() + Integer.parseInt(args[0].substring(1)) : Integer.parseInt(args[0]);
                    y2 = args[1].startsWith("~") ? player.getLocation().getY() + Integer.parseInt(args[1].substring(1)) : Integer.parseInt(args[1]);
                    z2 = args[2].startsWith("~") ? player.getLocation().getZ() + Integer.parseInt(args[2].substring(1)) : Integer.parseInt(args[2]);
                } catch (NumberFormatException e1) {
                    player.sendMessage(Main.getPrefix() + Main.use() + "§a/tp §7<~0> <~0> <~0>");
                    return;
                }

                if (x2 > 30000000 || y2 > 30000000 || z2 > 30000000 || x2 < -30000000 || y2 < -30000000 || z2 < -30000000) {
                    player.sendMessage(Main.getPrefix() + "§7Deine angegebenen Koordinaten sind zu groß oder zu klein.");
                    return;
                }
                final Location locpos = new Location(player.getWorld(), x2, y2, z2, player.getLocation().getYaw(), player.getLocation().getPitch());
                player.teleport(locpos);
                player.sendMessage(Main.getPrefix() + "§7Du hast dich zu §c" + x2.intValue() + " " + y2.intValue() + " " + z2.intValue() + " §cteleportiert.");
                break;
            case 4:
                if (!player.hasPermission("sp.pos")) {
                    player.sendMessage(Main.getPrefix() + Main.noPerm());
                    return;
                }
                final Player target4 = Bukkit.getPlayer(args[0]);
                if (target4 != null) {
                    final Double x;
                    final Double y;
                    final Double z;
                    try {
                        x = args[1].startsWith("~") ? target4.getLocation().getX() + Integer.parseInt(args[1].substring(1)) : Integer.parseInt(args[1]);
                        y = args[2].startsWith("~") ? target4.getLocation().getY() + Integer.parseInt(args[2].substring(1)) : Integer.parseInt(args[2]);
                        z = args[3].startsWith("~") ? target4.getLocation().getZ() + Integer.parseInt(args[3].substring(1)) : Integer.parseInt(args[3]);
                    } catch (NumberFormatException e1) {
                        player.sendMessage(Main.getPrefix() + Main.use() + "§a/tp §7<Spieler> <~0> <~0> <~0>");
                        return;
                    }
                    if (x > 30000000 || y > 30000000 || z > 30000000 || x < -30000000 || y < -30000000 || z < -30000000) {
                        player.sendMessage(Main.getPrefix() + "§7Deine angegebenen Koordinaten sind zu groß oder zu klein.");
                    }
                    final Location locposother = new Location(target4.getWorld(), x, y, z, target4.getLocation().getYaw(), target4.getLocation().getPitch());
                    target4.teleport(locposother);
                    target4.sendMessage(Main.getPrefix() + "§7Du wurdest zu §c" + x.intValue() + " " + y.intValue() + " " + z.intValue() + " §7teleportiert.");
                    player.sendMessage(Main.getPrefix() + "§7Du hast §c" + target4.getName() + " §7zu §c" + x.intValue() + " " + y.intValue() + " " + z.intValue() + " §7teleportiert.");
                } else {
                    sender.sendMessage(Main.getPrefix() + "§7Der angegebene Spieler ist §cnicht §7online.");
                }
                break;
            default:
                if (player.hasPermission("skyparty.teleport")
                        || player.hasPermission("sp.teleport.others")
                        || player.hasPermission("sp.teleport.pos")
                        || player.hasPermission("sp.teleport.others.position")) {
                    player.sendMessage(Main.getPrefix() + Main.use()+ "§a/teleport §7<Spieler>");
                    player.sendMessage(Main.getPrefix() + Main.use()+ "§a/teleport §7<Spieler> <Spieler>");
                    player.sendMessage(Main.getPrefix() + Main.use()+ "§a/teleport §7<x> <y> <z>");
                    player.sendMessage(Main.getPrefix() + Main.use()+ "§a/teleport §7<Spieler> <x> <y> <z>");
                    return;
                }
                sender.sendMessage(Main.getPrefix() + Main.noPerm());
                break;

        }
    }
}
