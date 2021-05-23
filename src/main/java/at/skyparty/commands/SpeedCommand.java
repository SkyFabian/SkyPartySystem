package at.skyparty.commands;

import at.skyparty.Main;
import at.skyparty.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    public SpeedCommand() {

    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("skyparty.speed")) {
            p.sendMessage(Util.Color(Main.getPrefix() + Main.noPerm()));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(Util.Color(Main.getPrefix() + Main.use() + "§a/speed <1-10>"));
            return false;
        }
        int speed;
        try {
            speed = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            p.sendMessage(Util.Color(Main.getPrefix() + Main.use() + "§a/speed <1-10>"));
            return false;
        }
        if (speed < 1 || speed > 10) {
            p.sendMessage(Util.Color(Main.getPrefix() + Main.use() + "§a/speed <1-10>"));
            return false;
        }
        if (p.isFlying()) {
            p.setFlySpeed((float) speed / 10);
        } else {
            p.setWalkSpeed((float) speed/ 10);
        }
        p.sendMessage(Util.Color(Main.getPrefix() + "§7Speed auf " + "§a" + speed + " §7geändert"));
        return true;
    }
}