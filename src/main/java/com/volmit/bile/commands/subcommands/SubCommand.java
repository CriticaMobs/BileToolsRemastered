package com.volmit.bile.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public abstract class SubCommand
{
    public static Map<String, SubCommand> subCommandMap = new HashMap<> ();

    public String tag = ChatColor.GREEN + "[" + ChatColor.DARK_GRAY + "Bile" + ChatColor.GREEN + "]: " + ChatColor.GRAY;

    public SubCommand(String command) {
        subCommandMap.put (command, this);
    }

    public abstract void execute(CommandSender sender, String[] args);
}
