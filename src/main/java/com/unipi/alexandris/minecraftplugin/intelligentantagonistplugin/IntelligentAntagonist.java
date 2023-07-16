package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.CommandsHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.ConfigHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.EventsHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class IntelligentAntagonist extends JavaPlugin {

    public ConfigHandler config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        config = new ConfigHandler(this);
        Objects.requireNonNull(getCommand("intelligentantagonist")).setExecutor(new CommandsHandler(this));
        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
