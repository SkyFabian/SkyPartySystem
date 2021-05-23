package at.skyparty.commands;

import at.skyparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveAllCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + "Du musst ein Spieler sein.");
            return false;
        }

        final Player player = ((Player) sender);

        if(player.hasPermission("sp.giveall")){

            if(args.length == 0){

                ItemStack itemStack = player.getInventory().getItemInHand();

                if(itemStack.getType() != Material.AIR){

                    player.getInventory().setItemInHand(null);

                    Bukkit.getOnlinePlayers().forEach(players -> {
                        players.getInventory().addItem(itemStack);
                        if(itemStack.getItemMeta().getDisplayName() != null){
                            players.sendMessage(Main.getPrefix() + "§aAlle haben das Item §6" + itemStack.getItemMeta().getDisplayName() + " §abekommen.");
                        }else{
                            players.sendMessage(Main.getPrefix() + "§aAlle haben das Item §6" + itemStack.getType() + " §abekommen.");
                        }
                    });

                }else{
                    player.sendMessage(Main.getPrefix() + "Du musst ein Item in der Hand halten.");
                }

            }else{
                player.sendMessage(Main.getPrefix() + Main.use()+ "§a/giveall");
            }

        }else{
            player.sendMessage(Main.getPrefix() + Main.noPerm());
        }

        return false;
    }

}