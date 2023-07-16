package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Config;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.IntelligentAntagonist;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public final class ConfigHandler {

    private final Config config = new Config();

    private final IntelligentAntagonist plugin;

    public ConfigHandler(IntelligentAntagonist plugin) {
        this.plugin = plugin;
        FileConfiguration fileConfiguration = plugin.getConfig();

        config.setLorem(fileConfiguration.getBoolean("lorem"));
    }

    public boolean isLorem() {
        return config.isLorem();
    }
}
