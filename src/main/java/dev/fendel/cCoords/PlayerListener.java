package dev.fendel.cCoords;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class PlayerListener implements Listener {

    public PlayerListener(cCoords plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();
        assert player != null;
        Location playerLocation = player.getLocation();

        // Death Message
        String sDeath = event.getEntity().getPlayer().getDisplayName() + " died at: " + ChatColor.RED + "x" + ChatColor.WHITE + ": " + (int)playerLocation.getX() + ", " + ChatColor.RED + "y" + ChatColor.WHITE + ": " + (int)playerLocation.getY() + ", " + ChatColor.RED + "z" + ChatColor.WHITE + ": " + (int)playerLocation.getZ() + " in " + ChatColor.RED + Objects.requireNonNull(playerLocation.getWorld()).getName() + ChatColor.WHITE;
        Bukkit.broadcastMessage(sDeath);

        // Death Chest
        Block block = playerLocation.getBlock().getRelative(BlockFace.DOWN);
        List<ItemStack> eventDrops = event.getDrops();

        block.setType(Material.CHEST);
        Chest chest = (Chest)block.getState();

        for (ItemStack i : eventDrops) {
            if (i != null) {
                chest.getInventory().addItem(i);
            }
            if (chest.getInventory().firstEmpty() == -1) {
                block.getRelative(BlockFace.WEST).setType(Material.CHEST);
                chest = (Chest)block.getRelative(BlockFace.WEST).getState();
            }
        }

        for (ItemStack i : eventDrops) {
            i.setType(Material.AIR);
        }
    }
}