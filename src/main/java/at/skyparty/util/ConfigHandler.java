package at.skyparty.util;

import at.skyparty.Main;

public class ConfigHandler {

    public String get(String key) {
        return Main.plugin.getConfig().getString(key);
    }
    public void set(String key, String value) {
        Main.plugin.getConfig().set(key,value);
    }
    public void save() {
        Main.plugin.saveConfig();
        Main.plugin.saveDefaultConfig();
    }
}
