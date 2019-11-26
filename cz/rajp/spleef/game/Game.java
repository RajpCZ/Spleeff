package cz.rajp.spleef.game;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    public static int cas = 60;
    static int task;

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Player> spectators = new ArrayList<Player>();

    public static ArrayList<Player> alive = new ArrayList<Player>();

    public static void startGame() {
        GameState.setState(GameState.IN_GAME);

        Bukkit.getOnlinePlayers().forEach(p -> p.performCommand("game4yqxdsa"));
    }

    public static void endGame() {
        GameState.setState(GameState.RESET);



        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(cas));
                if (cas <= 10 && cas != 0) {
                    Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.LEVEL_UP, 20, 20));
                    Chat.broadcast("§7Server restart in §3" + cas + " §7second(s)");
                } else if (cas == 0) {
                    Chat.broadcast("§c§lServer restarting!");
                    Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.LEVEL_UP, 20, 20));
                    Bukkit.getScheduler().cancelTask(task);
                }
                cas--;
            }
        }, 0L, 20L);
    }

    public static boolean isAlive(Player p) {

        return alive.contains(p);
    }

    public static boolean isPlayer(Player p) {

        return players.contains(p);
    }
    public static boolean isSpectator(Player p) {

        return spectators.contains(p);
    }
}
