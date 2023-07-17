package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.JURAN.Juran;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.LOREM.Lorem;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.CommandsHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.IntelligentAntagonist;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum Mobs {
    JURAN,
    LOREM;

    public static void load(IntelligentAntagonist plugin) {
        InputStream stream;


        try {
            // Load the Mob Models to ModelEngine config.
            stream = plugin.getResource("Models/Juran.bbmodel");
            assert stream != null;
            FileUtils.copyInputStreamToFile(stream, new File(Objects.requireNonNull(plugin.getServer().getPluginManager().getPlugin("ModelEngine")).getDataFolder(),
                    "blueprints/Juran.bbmodel"));

            // Load the Mob Mythic configurations to MythicMob config.
            stream = plugin.getResource("MythicMobs/Mobs/IntelligentAntagonistMobs.yml");
            assert stream != null;
            FileUtils.copyInputStreamToFile(stream, new File(Objects.requireNonNull(plugin.getServer().getPluginManager().getPlugin("MythicMobs")).getDataFolder(),
                    "Mobs/IntelligentAntagonistMobs.yml"));

        } catch (IOException ignored) {
            plugin.getLogger().severe("An internal error has occurred while attempting automatic resource loading." +
                    " If the problem persists, try loading the data manually from the available zip.");
        }

        // Reload the plugins.
        plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mythicmobs reload");
        ModelEngineAPI.api.reloadConfig();
    }

    public Mob spawn(Location location) {
        switch (this.name()) {
            case "JURAN" -> {
                Juran juran = new Juran();
                juran.spawn(location);
                return juran;
            }
            case "LOREM" -> {
                Lorem lorem = new Lorem();
                lorem.spawn(location);
                return lorem;
            }
        }
        return new Lorem();
    }

    public List<Mob> kill(List<Mob> activeMobs) {
        List<Mob> removed = new ArrayList<>();
        for(Mob mob : activeMobs)
            if(mob.getMobType().equals(this)) {
                mob.kill();
                removed.add(mob);
            }
        return removed;
    }

    public static List<Mob> killAll(List<Mob> activeMobs) {
        List<Mob> removed = new ArrayList<>();
        for(Mob mob : activeMobs) {
                mob.kill();
                removed.add(mob);
            }
        return removed;
    }
}
