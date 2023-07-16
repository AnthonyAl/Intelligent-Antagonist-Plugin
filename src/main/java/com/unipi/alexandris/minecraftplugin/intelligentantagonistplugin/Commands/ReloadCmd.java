package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.ConfigHandler;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.IntelligentAntagonist;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCmd implements SubCommand{

    private final IntelligentAntagonist plugin;

    private final String prefix;

    public ReloadCmd(IntelligentAntagonist plugin, String prefix) {
        this.plugin = plugin;
        this.prefix = prefix;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(prefix + "Reloading Intelligent Antagonist.. .  . ");
        plugin.reloadConfig();
        plugin.config = new ConfigHandler(plugin);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public String getUsage() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml for the plugin.";
    }

    @Override
    public List<String> getSubCommands() {
        return new ArrayList<>();
    }
}
