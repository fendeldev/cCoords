package dev.fendel.cCoords;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class commandS implements CommandExecutor {
    public Location playerLocation;
    public HashMap<UUID, Boolean> isSpectator = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID playerUUID = player.getUniqueId();

            if (isSpectator.get(playerUUID) == null || !isSpectator.get(playerUUID)) {
                isSpectator.put(playerUUID, true);
                playerLocation = player.getLocation();
                player.setGameMode(GameMode.SPECTATOR);
            }
            else {
                isSpectator.put(playerUUID, false);
                player.teleport(playerLocation);
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
        return true;
    }
}