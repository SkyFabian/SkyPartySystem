package at.skyparty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.LinkedList;
import java.util.List;

public class BookEditListener implements Listener {
    public BookEditListener() {
    }

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("sp.color.book")) {
            BookMeta bm = e.getNewBookMeta();
            List<String> list = new LinkedList(bm.getPages());
            list.replaceAll((page) -> {
                return page.replace('&', '§');
            });
            bm.setPages(list);
            e.setNewBookMeta(bm);
        }
    }
}
