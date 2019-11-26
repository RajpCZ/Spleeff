package cz.rajp.spleef.events;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.game.Game;
import cz.rajp.spleef.game.GameState;
import cz.rajp.spleef.utils.BoardManager;
import cz.rajp.spleef.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent ej) {
        Player p = ej.getPlayer();
        if (GameState.isState(GameState.WAITING)) {
            ej.setJoinMessage(Main.getInstance().prefix + "§3" + p.getName() + " §7join the game §8(§b" + Bukkit.getOnlinePlayers().size() + "§8)");
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.updateInventory();
            p.setExp(0);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.setAllowFlight(false);
            p.setLevel(0);

            ItemStack kit_selector = new ItemStack(Material.EYE_OF_ENDER);
            ItemMeta meta = kit_selector.getItemMeta();
            meta.setDisplayName("§b§lKit Selector");
            kit_selector.setItemMeta(meta);
            p.getInventory().setItem(0, kit_selector);

            Game.alive.add(p);
            p.performCommand("lobby4yqxdsa");
            BoardManager.loadLobbyBoard(p);

            if (Bukkit.getOnlinePlayers().size() >= 2) {
                Countdown.start();
            }

        } if (GameState.isState(GameState.IN_GAME)) {
            p.setGameMode(GameMode.SPECTATOR);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.updateInventory();
            p.setExp(0);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.setAllowFlight(false);
            p.setLevel(0);

            Game.spectators.add(p);
            //BoardManager.loadGameBoard(p);

        } if (GameState.isState(GameState.RESET)) {
            p.kickPlayer("§cArena se restartuje!");
        }
    }
}
