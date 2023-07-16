package com.unipi.alexandris.minecraftplugintemplate.loremipsum.Handlers;

import com.unipi.alexandris.minecraftplugintemplate.loremipsum.LoremIpsum;
import com.unipi.alexandris.minecraftplugintemplate.loremipsum.Core.Config;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public final class ConfigHandler {

    private final Config config = new Config();

    private final LoremIpsum plugin;

    public ConfigHandler(LoremIpsum plugin) {
        this.plugin = plugin;
        FileConfiguration fileConfiguration = plugin.getConfig();

        config.setLorem(fileConfiguration.getBoolean("lorem"));
    }

    public boolean isLorem() {
        return config.isLorem();
    }
}
