package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + Main.onlyPlayer());
            return false;
        }
        DiscordCommand disccord = new DiscordCommand();
        sender.sendMessage(Main.getPrefix() + "Offizieller Discord: §2https://discord.gg/Eqb3AM23D9");
        return false;
    }
}
