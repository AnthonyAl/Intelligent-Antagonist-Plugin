package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mob;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mobs;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.IntelligentAntagonist;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobsCmd implements SubCommand {


    private final IntelligentAntagonist plugin;

    private final String prefix;

    public MobsCmd(IntelligentAntagonist plugin, String prefix) {
        this.plugin = plugin;
        this.prefix = prefix;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(prefix + ChatColor.RED + "Command usage: /intelligentantagonist " + ChatColor.GRAY + getUsage() + " info | spawn | kill | killall" + ChatColor.RED + ".");
            return true;
        }
        else if (Objects.equals(args[0], "spawn")) {
            Mobs mobType;
            if(args.length != 2) {
                sender.sendMessage(prefix + ChatColor.RED + "Command usage: /intelligentantagonist " + ChatColor.GRAY + getUsage() + " spawn <mob name>" + ChatColor.RED + ".");
                return true;
            }
            try {
                mobType = Mobs.valueOf(args[1].toUpperCase());
            }
            catch(IllegalArgumentException ignored) {
                sender.sendMessage(prefix + ChatColor.RED + "Could not identify Intelligent Mob of type " + ChatColor.YELLOW +
                        args[1] + ChatColor.RED + ".");
                return true;
            }
            plugin.activeMobs.add(mobType.spawn(((Entity) sender).getLocation()));
            return true;
        }
        else if(Objects.equals(args[0], "kill")) {
            Mobs mobType;
            if(args.length != 2) {
                sender.sendMessage(prefix + ChatColor.RED + "Command usage: /intelligentantagonist " + ChatColor.GRAY + getUsage() + " kill <mob name>" + ChatColor.RED + ".");
                return true;
            }
            try {
                mobType = Mobs.valueOf(args[1].toUpperCase());
            }
            catch(IllegalArgumentException ignored) {
                sender.sendMessage(prefix + ChatColor.RED + "Could not identify Intelligent Mob of type " + ChatColor.YELLOW +
                        args[1] + ChatColor.RED + ".");
                return true;
            }
            plugin.activeMobs.removeAll(mobType.kill(plugin.activeMobs));
            return true;
        }
        else if(Objects.equals(args[0], "killall")) {
            if(args.length > 1) {
                sender.sendMessage(prefix + ChatColor.RED + "Command usage: /intelligentantagonist " + ChatColor.GRAY + getUsage() + " killall" + ChatColor.RED + ".");
                return true;
            }
            plugin.activeMobs.removeAll(Mobs.killAll(plugin.activeMobs));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }


    @Override
    public String getUsage() {
        return "mobs";
    }

    @Override
    public String getDescription() {
        return "command group for the mobs of Intelligent Antagonist Plugin. Available commands: \n" +
                "- info <mob name>: reveals the info related to a specific mob. \n" +
                "- spawn <mob name>: summons the specified mob to the player's location. \n" +
                "- kill <mob name>: kills all mobs with the specified name. \n" +
                "- killall: kills all Intelligent Antagonist mobs.";
    }

    @Override
    public boolean inGameOnly() {
        return true;
    }

    @Override
    public List<String> getSubCommands() {
        ArrayList<String> subCommands = new ArrayList<>();
        subCommands.add("info");
        subCommands.add("spawn");
        subCommands.add("kill");
        subCommands.add("killall");

        return subCommands;
    }
}
