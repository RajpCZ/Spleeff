package cz.rajp.spleef.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Chat {

    public static void broadcast(String msg) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            String prefix = "§8[§bSpleef§8] §r";
            p.sendMessage(prefix + msg);
        }
    }
}
