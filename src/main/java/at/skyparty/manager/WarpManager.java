package at.skyparty.manager;

import de.omel.api.file.FileBuilder;
import de.omel.api.fun.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class WarpManager {

    private static FileBuilder fw;
    private static String name;

    public WarpManager() {
        fw = new FileBuilder("plugins/warps/", "warps.yml");
    }

    public WarpManager(String name) {
        WarpManager.name = name;
        fw = new FileBuilder("plugins/warps/", "warps.yml");
    }

    public String getName() {
        return WarpManager.name;
    }

    public List<Warp> getWarps() {
        List<Warp> temp = new ArrayList<>();
        for (String name : fw.getKeys(false)) {
            temp.add(new Warp(name));
        }
        return temp;
    }

    public FileBuilder getFileBuilder() {
        return fw;
    }

    public boolean exist() {
        return fw.getString(name) != null;
    }

    public WarpManager delete() {
        fw.setValue(name, null);
        fw.save();
        return this;
    }

    public WarpManager setLocation(Location loc) {
        fw.setValue(name + ".world", loc.getWorld().getName());
        fw.setValue(name + ".x", loc.getX());
        fw.setValue(name + ".y", loc.getY());
        fw.setValue(name + ".z", loc.getZ());
        fw.setValue(name + ".yaw", loc.getYaw());
        fw.setValue(name + ".pitch", loc.getPitch());
        fw.save();
        return this;
    }

    public Location getLocation() {
        World world = Bukkit.getWorld(fw.getString(name + ".world"));
        double x = fw.getDouble(name + ".x"),
                y = fw.getDouble(name + ".y"),
                z = fw.getDouble(name + ".y"),
                yaw = fw.getDouble(name + ".yaw"),
                pitch = fw.getDouble(name + ".pitch");
        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }
}
