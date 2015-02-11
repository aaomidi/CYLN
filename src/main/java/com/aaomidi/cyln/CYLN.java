package com.aaomidi.cyln;

import com.aaomidi.cyln.engine.ConfigReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CYLN extends JavaPlugin implements Listener {
    private ConfigReader configReader;

    @Override
    public void onLoad() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveDefaultConfig();
        }
    }

    @Override
    public void onEnable() {
        configReader = new ConfigReader(this);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        configReader.onDisable();
    }

    @EventHandler
    public void onPlayerAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        String playerName = event.getName();
        if (playerName.length() <= ConfigReader.getMaxLength()) {
            return;
        }
        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ConfigReader.getKickMessage());
    }
}
