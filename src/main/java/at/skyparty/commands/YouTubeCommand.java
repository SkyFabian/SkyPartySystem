package at.skyparty.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class YouTubeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YouTubeCommand youTubeCommand = new YouTubeCommand();
        sender.sendMessage("§7§m--------------- §a§lSky§7§l-§a§lParty §r§7§m---------------");
        sender.sendMessage("§7 ");
        sender.sendMessage("§7Alle anforderungen für den §5§lYoutuber§7§l-§5§lRang");
        sender.sendMessage("§a§l⋙ §7mindestens 250 Abonnenten.");
        sender.sendMessage("§a§l⋙ §7mindestens 1 Video in der Woche vom Server.");
        sender.sendMessage("§a§l⋙ §7mindestens 100 Klicks pro Video.");
        sender.sendMessage("§7Du erfüllst alles? Dann besuche unseren §2§lDiscord§7.");
        sender.sendMessage("§1 ");
        sender.sendMessage("§7§m--------------- §a§lSky§7§l-§a§lParty §r§7§m---------------§4");
        return false;
    }
}
