package com.yukikaze.essentials.handlers;

import com.yukikaze.essentials.Essentials;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LoadConfig {

    public static YamlConfiguration getConfig() {

        YamlConfiguration config;

        File configFile = new File(Essentials.main.getDataFolder(), "config.yml");

        if(!configFile.exists()) {
            Essentials.main.saveDefaultConfig();
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        return config;
    }
}
