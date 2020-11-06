package dev.fendel.cCoords;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class commandC implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location locationPlayer = player.getLocation();
            String sMessage = player.getDisplayName() + "s coords: " + ChatColor.YELLOW + "x" + ChatColor.WHITE + ": " + (int)locationPlayer.getX() + ", " + ChatColor.YELLOW + "y" + ChatColor.WHITE + ": " + (int)locationPlayer.getY() + ", " + ChatColor.YELLOW + "z" + ChatColor.WHITE + ": " + (int)locationPlayer.getZ() + " in " + ChatColor.YELLOW + Objects.requireNonNull(locationPlayer.getWorld()).getName() + ChatColor.WHITE;
            Bukkit.broadcastMessage(sMessage);
        }
        return true;
    }
}