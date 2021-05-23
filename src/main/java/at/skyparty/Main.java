package at.skyparty;

import at.skyparty.chat.SkyChatCommand;
import at.skyparty.chat.SlowChatCommand;
import at.skyparty.chat.TeamChatCommand;
import at.skyparty.commands.*;
import at.skyparty.listener.*;
import at.skyparty.tp.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class Main extends JavaPlugin {

    public static File file;
    public static FileConfiguration cfg;
    public static Boolean status;
    private static Main instance;
    public static Main plugin;
    private int task;
    private int bcNumber;

    public static Main get() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }


    public static Main getPlugin() {
        return plugin;
    }

    public static void setPlugin(Main plugin) {
        Main.plugin = plugin;
    }

    public static Main getInstance() {
        return instance;
    }

    public int getBcNumber() {
        return bcNumber;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public void setBcNumber(int bcNumber) {
        this.bcNumber = bcNumber;
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "Das Plugin ist nun Aktiviert");
        commandRegistration();
        listenerRegistration();

        plugin = this;
        instance = this;
        saveDefaultConfig();
        status = false;
        file = new File(instance.getDataFolder(), "config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        status = true;
    }

    @Override
    public void onDisable() {

    }

    private void commandRegistration() {
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("giveall").setExecutor(new GiveAllCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("slowchat").setExecutor(new SlowChatCommand());
        getCommand("teamchat").setExecutor(new TeamChatCommand());
        getCommand("sky").setExecutor(new SkyChatCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tpaccept").setExecutor(new TPAcceptCommand());
        getCommand("tpahere").setExecutor(new TPAHereCommand());
        getCommand("tpaall").setExecutor(new TPAAllCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("youtuber").setExecutor(new YouTubeCommand());
        getCommand("bewerbung").setExecutor(new BewerbungCommand());
    }

    private void listenerRegistration() {
        PluginManager pluginmanager = Bukkit.getPluginManager();
        pluginmanager.registerEvents(new JoinListener(), this);
        pluginmanager.registerEvents(new QuitListener(), this);
        pluginmanager.registerEvents(new PlayerChatEvent(), this);
        pluginmanager.registerEvents(new CommandBlockerListener(), this);
        pluginmanager.registerEvents(new MoveListener(), this);
        pluginmanager.registerEvents(new DeathListener(), this);
        pluginmanager.registerEvents(new MuteChatListener(), this);
        pluginmanager.registerEvents(new SlowChatListener(), this);
        pluginmanager.registerEvents(new BookEditListener(), this);
        pluginmanager.registerEvents(new SignChangeListener(), this);
    }

    public static String getPrefix() {
        return ChatColor.GRAY + "[" + ChatColor.GREEN + "Sky" + ChatColor.GRAY + "-" + ChatColor.GREEN + "Party" + ChatColor.GRAY + "] " + ChatColor.GRAY;
    }

    public static String noPerm() {
        return ChatColor.GRAY + "Dafür hast du " + ChatColor.RED + "keine" + ChatColor.GRAY + " Berechtigung.";
    }

    public static String onlyPlayer() {
        return ChatColor.GRAY + "Nur Spieler dürfen dies.";
    }

    public static String use() {
        return ChatColor.GRAY + "Verwende » ";
    }

    public Main() {
    }

    public static void setFile(File file) {
        Main.file = file;
    }

    public static FileConfiguration getCfg() {
        return cfg;
    }

    public static void setCfg(FileConfiguration cfg) {
        Main.cfg = cfg;
    }

    public static Boolean getStatus() {
        return status;
    }

    public static void setStatus(Boolean status) {
        Main.status = status;
    }

    public Main(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    private void counter() {
        if (bcNumber == 5) {
            bcNumber = 0;
        }
        bcNumber = 0;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                counter();
            }
        }, 8400, 8400);

        bcNumber++;
        switch (bcNumber) {
            case 1:
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§aFolgst du uns schon auf Instagram? §c@skyparty.eu");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                break;
            case 2:
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§aDu brauchst Hilfe? Mit §b/support [Thema]§a du bekommst Hilfe von einem Teammitglied.");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                break;
            case 3:
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§aWir suchen aktuell §eBuilder §aund §bDeveloper.");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                break;
            case 4:
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§aHeute schon gevotet? Mit §d/vote §aerhälst du eine coole Belohnung.");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                break;
            case 5:
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§aBist du schon auf unseren Discord? §9/discord§a.");
                Bukkit.broadcastMessage("§7 ");
                Bukkit.broadcastMessage("§7§m-------------------§8[§aSky§7-§aParty§8]§7§m-------------------");
                break;
        }
    }

}
