package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BewerbungCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }

        BewerbungCommand bewerbungCommand = new BewerbungCommand();
        sender.sendMessage("§7§m--------------- §a§lSky§7§l-§a§lParty §r§7§m---------------");
        sender.sendMessage("§7 ");
        sender.sendMessage("§7Alle anforderungen für eine §e§lBewerbung.");
        sender.sendMessage("§a§l⋙ §7Du musst mindestens 13 Jahre jung sein.");
        sender.sendMessage("§a§l⋙ §7Du musst die deutsche Sprache beherrschen.");
        sender.sendMessage("§a§l⋙ §7Du musst mindestens 5h in der Woche online sein.");
        sender.sendMessage("§7Du erfüllst alles? Dann bewird dich jetzt: §2§lbewerbung@sky-party.eu");
        sender.sendMessage("§1 ");
        sender.sendMessage("§7§m--------------- §a§lSky§7§l-§a§lParty §r§7§m---------------§4");
        return false;
    }
}
