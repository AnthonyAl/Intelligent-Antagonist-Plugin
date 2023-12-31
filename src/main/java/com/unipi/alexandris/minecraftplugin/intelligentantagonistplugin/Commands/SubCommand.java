package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands;


import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public interface SubCommand {

    boolean onCommand(CommandSender sender, String[] args);

    List<String> onTabComplete(CommandSender sender, String[] args);

    String getUsage();

    String getDescription();

    default boolean inGameOnly() {
        return false;
    }

    List<String> getSubCommands();
}
