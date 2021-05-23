package at.skyparty.manager;

import de.omel.api.file.FileBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Set;
import java.util.UUID;

public class HomeManager {

    private final FileBuilder fw;

    public HomeManager(UUID uuid) {

        fw = new FileBuilder("plugins/SkyParty/", uuid.toString() + ".yml");

    }

    public void addHome(Location loc, String name) {
        fw.setValue(name + ".world", loc.getWorld().getName());
        fw.setValue(name + ".x", loc.getX());
        fw.setValue(name + ".y", loc.getY());
        fw.setValue(name + ".z", loc.getZ());
        fw.setValue(name + ".yaw", loc.getYaw());
        fw.setValue(name + ".pitch", loc.getPitch());

        fw.save();

    }

    public boolean exist(String name) {
        return fw.getString(name) != null;
    }
    public Set<String> getHomes() {
        return fw.getKeys(false);
    }

    public Location getLocation(String name) {
        return new Location(Bukkit.getWorld(fw.getString(name + ".world")), fw.getDouble(name + ".x"), fw.getDouble(name + ".y"),
                fw.getDouble(name + ".z"), (float) fw.getDouble(name + ".yaw"), (float) fw.getDouble(name + ".pitch"));
    }

    public void delHome(String name) {
        fw.setValue(name, null);
        fw.save();
    }

}
