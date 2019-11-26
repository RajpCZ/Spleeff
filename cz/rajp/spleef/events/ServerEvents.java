package cz.rajp.spleef.events;

import cz.rajp.spleef.Main;
import cz.rajp.spleef.game.Game;
import cz.rajp.spleef.game.GameState;
import cz.rajp.spleef.utils.Chat;
import cz.rajp.spleef.utils.api.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;

public class ServerEvents implements Listener {

    public static ArrayList<Player> players = new ArrayList<Player>();


    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event)
    {
        if (GameState.isState(GameState.WAITING)) {
            if(event.getBlock().getType() == Material.ICE)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event)
    {
        if (GameState.isState(GameState.WAITING)) {
            if (((event.getBlock().getType() == Material.ICE) || (event.getBlock().getType() == Material.SNOW) || (event.getBlock().getType() == Material.SNOW_BLOCK))) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event)
    {
        if (GameState.isState(GameState.WAITING)) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event)
    {
        if (GameState.isState(GameState.WAITING)) {
            Block block = event.getBlock();
            if (block.getType() == Material.TORCH) {
                event.setBuildable(true);
            }
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            Player p = (Player) e.getWhoClicked();
            if (e.getInventory() != null && e.getClickedInventory() != null && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && !players.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceI(BlockIgniteEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            Player p = e.getPlayer();
            if (!players.contains(p)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onSpread(BlockSpreadEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            if (!players.contains(e.getPlayer())) {
                if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL) {
                    e.setCancelled(true);
                }
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.TRAP_DOOR
                        || e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.WOODEN_DOOR
                        || e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CHEST) {
                    e.setCancelled(true);
                }
            }
        }
    }


    @EventHandler(priority=EventPriority.HIGHEST)
    public void dmgbydmg(EntityDamageByEntityEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            if (e.getEntity().getType() == EntityType.ITEM_FRAME) {
                if (e.getDamager().getType() == EntityType.PLAYER) {
                    Player p = (Player) e.getDamager();
                    if (!players.contains(p)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler(priority=EventPriority.HIGHEST)
    public void dmgbydmgi(HangingBreakByEntityEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            if (e.getEntity().getType() == EntityType.ITEM_FRAME) {
                if (e.getRemover().getType() == EntityType.PLAYER) {
                    Player p = (Player)e.getRemover();
                    if (!players.contains(p)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            if (e.getRightClicked().getType() == EntityType.ITEM_FRAME) {
                Player p = e.getPlayer();
                if (!players.contains(p)) {
                    e.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onWeatherEvent(WeatherChangeEvent we) {
        we.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!players.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        e.setDeathMessage(Main.getInstance().prefix + "§3" + e.getEntity().getName() + " §7umrel!");

        Game.alive.remove(p);
        Game.spectators.add(p);

        p.setGameMode(GameMode.SPECTATOR);
        p.performCommand("game4yqxdsa");
        //TODO ITEMS

        if (GameState.isState(GameState.IN_GAME)) {
            if (Game.alive.size() == 1 || Bukkit.getOnlinePlayers().size() == 1) {
                Chat.broadcast("§b" + Game.alive.get(0).getDisplayName() + " §7win the game");
                TitleAPI.send(p, "§b" + Game.alive.get(0).getDisplayName(), " §7win the game");
                Game.endGame();
            }
            if (Game.alive.isEmpty()) {
                Chat.broadcast("§bNobody won the game!");
                Game.endGame();
            }
        }
    }
}
