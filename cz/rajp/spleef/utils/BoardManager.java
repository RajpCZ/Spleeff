package cz.rajp.spleef.utils;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class BoardManager implements Listener {

    public static void loadLobbyBoard(Player p) {

            new BukkitRunnable() {
                public void run() {

                    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
                    Objective objective = board.getObjective("Spleef");

                    objective = board.registerNewObjective("Spleef", "dummy");
                    objective.setDisplayName("§b§lSpleef §8| §3" + Countdown.cas);
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                    Score score1 = objective.getScore("§r");
                    Score score2 = objective.getScore("§3Server: " /*TODO*/);
                    Score score3 = objective.getScore("§3Map: §7Voting..");
                    Score score4 = objective.getScore("§r§f ");
                    Score score5 = objective.getScore("§3Players: §7" + Game.alive.size());
                    Score score6 = objective.getScore("§r§f§c ");
                    Score score7 = objective.getScore("§bmc.melwerz.eu");

                    score1.setScore(12);
                    score2.setScore(11);
                    score3.setScore(10);
                    score4.setScore(9);
                    score5.setScore(8);
                    score6.setScore(7);
                    score7.setScore(6);
                    p.setScoreboard(board);

                }
            }.runTaskTimer(Main.getInstance(), 0, 10);
    }


}
