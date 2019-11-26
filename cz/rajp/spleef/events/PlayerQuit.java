package cz.rajp.spleef.events;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.game.Game;
import cz.rajp.spleef.game.GameState;
import cz.rajp.spleef.utils.Chat;
import cz.rajp.spleef.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (GameState.isState(GameState.WAITING)) {
            e.setQuitMessage(Main.getInstance().prefix + "§3" + e.getPlayer().getName() + " §7quit the game §8(§b" + Bukkit.getOnlinePlayers().size() + "§8)");

            if (Game.isAlive(p)) {
                Game.alive.remove(p);
            } else if (Game.isSpectator(p)) {
                Game.spectators.remove(p);
            }

            if (Countdown.hasStarted) {
                if (Bukkit.getOnlinePlayers().size() < 2) {
                    Countdown.stop();
                }
            }
        }

        if (GameState.isState(GameState.IN_GAME)) {
            if (Game.isAlive(p)) {
                if (Game.alive.size() == 1) {
                    Chat.broadcast("§b" + p.getName() + " §7win the game!");
                    Game.endGame();
                }
                if (Game.alive.size() > 1) {
                    Chat.broadcast("§bNobody won the game!");
                    Game.endGame();
                }
            }
        }
    }
}
