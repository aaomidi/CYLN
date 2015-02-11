package com.aaomidi.cyln.engine;

import com.aaomidi.cyln.CYLN;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigReader {
    private final CYLN instance;
    private static FileConfiguration config;
    private static Integer maxLength = null;
    public static String kickMessage = null;

    public ConfigReader(CYLN instance) {
        this.instance = instance;
        config = instance.getConfig();
    }

    public static int getMaxLength() {
        if (maxLength != null) {
            return maxLength;
        }
        maxLength = config.getInt("Max-Length");
        return maxLength;
    }

    public static String getKickMessage() {
        if (kickMessage != null) {
            return kickMessage;
        }
        kickMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Kick-Message"));
        return kickMessage;
    }

    public void onDisable() {
        config = null;
        maxLength = null;
        kickMessage = null;
    }
}
