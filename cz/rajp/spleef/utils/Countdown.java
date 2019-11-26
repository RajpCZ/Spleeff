package cz.rajp.spleef.utils;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class Countdown {

    public static int cas = 60;
    static int task;

    public static boolean hasStarted;

    public static void start() {
        if (Bukkit.getOnlinePlayers().size() >= 2) {
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

                @Override
                public void run() {
                    Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(cas));
                    hasStarted = true;
                    if (cas == 60 || cas == 45 || cas == 30 || cas == 15 || cas <= 10 && cas != 0) {
                        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.LEVEL_UP, 20, 20));
                        Chat.broadcast("§7Game start in §3" + cas + " §7second(s)");
                    } else if (cas == 0) {
                        Chat.broadcast("§a§lGame started!");
                        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.LEVEL_UP, 20, 20));
                        Game.startGame();
                        Bukkit.getScheduler().cancelTask(task);
                    }
                    cas--;
                }
            }, 0L, 20L);
        } else {
            Bukkit.getScheduler().cancelTask(task);
            stop();
        }
    }

    public static void stop() {
        hasStarted = false;
        Chat.broadcast("§cCountdown has been stopped!");
    }
}
