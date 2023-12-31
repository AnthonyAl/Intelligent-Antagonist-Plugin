package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers.CommandsHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpCmd implements SubCommand {

    private final CommandsHandler cmdHandler;

    private final String prefix;

    public HelpCmd(CommandsHandler cmdHandler, String prefix) {
        this.cmdHandler = cmdHandler;
        this.prefix = prefix;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(prefix + "Command list:");

        for (SubCommand cmd : cmdHandler.getCommands()) {
            if (cmd.inGameOnly() && !(sender instanceof Player)) {
                continue;
            }

            sender.sendMessage(ChatColor.GRAY + "  -" + ChatColor.AQUA + "/intelligentantagonist " + cmd.getUsage() + ChatColor.GRAY + " - " + cmd.getDescription());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows this page.";
    }

    @Override
    public List<String> getSubCommands() {
        return new ArrayList<>();
    }
}
