package cz.rajp.spleef.events;

import cz.rajp.spleef.game.GameState;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockDamageEvent e) {
        if (GameState.isState(GameState.WAITING)) {
            e.setCancelled(true);
            e.getBlock().getDrops().clear();
        }

        if (GameState.isState(GameState.IN_GAME)) {
            e.setInstaBreak(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.STEP_STONE, 20, 20);
            e.getBlock().getWorld().getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
        }
    }
}
