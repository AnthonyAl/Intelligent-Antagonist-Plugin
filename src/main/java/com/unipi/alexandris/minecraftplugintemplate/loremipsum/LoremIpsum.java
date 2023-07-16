package com.unipi.alexandris.minecraftplugintemplate.loremipsum;

import com.unipi.alexandris.minecraftplugintemplate.loremipsum.Handlers.CommandsHandler;
import com.unipi.alexandris.minecraftplugintemplate.loremipsum.Handlers.ConfigHandler;
import com.unipi.alexandris.minecraftplugintemplate.loremipsum.Handlers.EventsHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LoremIpsum extends JavaPlugin {

    public ConfigHandler config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        config = new ConfigHandler(this);
        Objects.requireNonNull(getCommand("lorem")).setExecutor(new CommandsHandler(this));
        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
