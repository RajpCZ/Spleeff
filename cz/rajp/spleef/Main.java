package cz.rajp.spleef;

import cz.rajp.spleef.events.PlayerJoin;
import cz.rajp.spleef.events.PlayerQuit;
import cz.rajp.spleef.game.GameState;
import cz.rajp.spleef.commands.SetLobby;
import cz.rajp.spleef.commands.SetSpawn;
import cz.rajp.spleef.events.BlockBreak;
import cz.rajp.spleef.events.ServerEvents;
import cz.rajp.spleef.utils.BoardManager;
import cz.rajp.spleef.utils.ChatFormat;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static String prefix = "§8[§bSpleef§8] §r";

    public void onEnable() {
        instance = this;
        GameState.setState(GameState.WAITING);
        registerEvents();
        registerCommands();

        rollback("arena");
    }

    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new ServerEvents(), this);
        pm.registerEvents(new BoardManager(), this);
        pm.registerEvents(new ChatFormat(), this);
    }

    public void registerCommands() {
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("game4yqxdsa").setExecutor(new SetSpawn());
        getCommand("setlobby").setExecutor(new SetLobby());
        getCommand("lobby4yqxdsa").setExecutor(new SetLobby());
    }

    public static void unloadMap(String mapname){
        if (Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(mapname), false)){
            instance.getLogger().info("Successfully unloaded " + mapname);
        } else {
            instance.getLogger().severe("COULD NOT UNLOAD " + mapname);
        }
    }

    public static void loadMap(String mapname){
        Bukkit.getServer().createWorld(new WorldCreator(mapname));
    }

    public static void rollback(String mapname){
        unloadMap(mapname);
        loadMap(mapname);
    }

    public static Main getInstance() {
        return instance;
    }
}
