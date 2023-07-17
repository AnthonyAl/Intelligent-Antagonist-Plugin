package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Handlers;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands.HelpCmd;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands.MobsCmd;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands.ReloadCmd;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mobs;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.IntelligentAntagonist;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Commands.SubCommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CommandsHandler implements TabExecutor {

    private final HashMap<String, SubCommand> commands = new HashMap<>();

    private IntelligentAntagonist plugin;

    public static final String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "IntelligentAntagonist" + ChatColor.GRAY + "] ";

    public CommandsHandler(IntelligentAntagonist plugin) {
        this.plugin = plugin;
        commands.put("help", new HelpCmd(this, prefix));
        commands.put("reload", new ReloadCmd(plugin, prefix));
        commands.put("mobs", new MobsCmd(plugin, prefix));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(prefix + ChatColor.AQUA + "Intelligent Antagonist" + ChatColor.GRAY + " is a plugin developed by Antitonius. " +
                    "A whole unique non-playable character AI in a single plugin! This plugin takes " +
                    "advantage of Intelligent Agent algorithms to implement antagonistic NPCs with " +
                    "smart combat abilities and relative emotional states! To check the help page, type "
                    + ChatColor.YELLOW + "/intelligentantagonist help" + ChatColor.GRAY + ".");
            return true;
        }

        SubCommand command = commands.get(args[0].toLowerCase());

        if (command == null) {
            sender.sendMessage(prefix + ChatColor.RED + "Unknown command. To check out the help page, type " + ChatColor.GRAY + "/intelligentantagonist help" + ChatColor.RED + ".");
            return true;
        }

        if (command.inGameOnly() && !(sender instanceof Player)) {
            sender.sendMessage(prefix + ChatColor.RED + "Console cannot run this command.");
            return true;
        }

        String[] subCmdArgs = new String[args.length - 1];
        System.arraycopy(args, 1, subCmdArgs, 0, subCmdArgs.length);

        if (!command.onCommand(sender, subCmdArgs)) {
            sender.sendMessage(prefix + ChatColor.RED + "Command usage: /intelligentantagonist " + ChatColor.GRAY + command.getUsage() + ChatColor.RED + ".");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        //create new array
        final List<String> completions = new ArrayList<>();

        //create arrays of allowed commands
        List<String> commands = new ArrayList<>();
        if(args.length == 1) {
            commands = new ArrayList<>(this.commands.keySet().stream().toList());
        }
        else if(args.length == 2) {
            commands = new ArrayList<>();
            SubCommand sb = this.commands.get(args[0]);
            if(!sb.getSubCommands().isEmpty())
                commands.addAll(sb.getSubCommands());
        }

        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        else if(args.length == 2) {
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }
        else if(args.length == 3 && !Objects.equals(args[2], "killall")) {
            commands = new ArrayList<>();
            for(Mobs mobType : Mobs.values())
                commands.add(mobType.name());
            StringUtil.copyPartialMatches(args[2], commands, completions);
        }

        //sort the list
        Collections.sort(completions);
        return completions;
    }

    public Collection<SubCommand> getCommands() {
        return commands.values();
    }
}
