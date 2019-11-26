package cz.rajp.spleef.commands;

import cz.rajp.spleef.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetLobby implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("");
            return true;
        }
        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("setlobby")) {
            if (p.hasPermission("spleef.admin") || p.hasPermission("*") || p.isOp()) {
                p.sendMessage(Main.getInstance().prefix + "§aLobby bylo nastaveno!");
            } else {
                p.sendMessage("§cNa toto nemas opravneni!");
                return true;
            }

            File ordner = new File("plugins//Spleef");
            File file = new File("plugins//Spleef//lobby.yml");

            if (!ordner.exists()) {
                ordner.mkdir();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    return true;
                }
            }

            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Location loc = p.getLocation();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            double yaw = loc.getYaw();
            double pitch = loc.getPitch();
            String worldname = loc.getWorld().getName();

            cfg.set("World", worldname);
            cfg.set("x", Double.valueOf(x));
            cfg.set("y", Double.valueOf(y));
            cfg.set("z", Double.valueOf(z));
            cfg.set("yaw", Double.valueOf(yaw));
            cfg.set("pitch", Double.valueOf(pitch));


            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("lobby4yqxdsa")) {
            File file = new File("plugins//Spleef//lobby.yml");
            if (!file.exists()) {
                p.sendMessage("§cError!");
                return true;
            }
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Location loc = p.getLocation();
            String worldname = cfg.getString("World");
            double x = cfg.getDouble("x");
            double y = cfg.getDouble("y");
            double z = cfg.getDouble("z");
            double yaw = cfg.getDouble("yaw");
            double pitch = cfg.getDouble("pitch");

            World w = Bukkit.getWorld(worldname);

            loc.setX(x);
            loc.setY(y);
            loc.setZ(z);
            loc.setYaw((float)yaw);
            loc.setPitch((float)pitch);
            loc.setWorld(w);

            p.teleport(loc);
            p.sendMessage("");
            return true;
        }
        return false;
    }
}
