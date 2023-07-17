package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mob;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mobs;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.CommandsHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.ConfigHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.EventsHandler;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class IntelligentAntagonist extends JavaPlugin {

    public ConfigHandler config;

    public List<Mob> activeMobs = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        config = new ConfigHandler(this);
        Objects.requireNonNull(getCommand("intelligentantagonist")).setExecutor(new CommandsHandler(this));
        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);

        if(config.isAuto_load()) Mobs.load(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
