package dev.fendel.cCoords;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class cCoords extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("c")).setExecutor(new commandC());
        Objects.requireNonNull(this.getCommand("s")).setExecutor(new commandS());
        new PlayerListener(this);
    }

    @Override
    public void onDisable() {
    }
}
