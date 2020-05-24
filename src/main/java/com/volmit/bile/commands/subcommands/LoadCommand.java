package com.volmit.bile.commands.subcommands;

import com.volmit.bile.utils.BileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class LoadCommand
extends SubCommand{

    public LoadCommand() {
        super ("load");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1)
        {
            for(int i = 1; i < args.length; i++)
            {
                try
                {
                    File s = BileUtils.getPluginFile(args[i]);

                    if(s == null)
                    {
                        sender.sendMessage(tag + "Couldn't find \"" + args[i] + "\".");
                        continue;
                    }

                    try
                    {
                        BileUtils.load(s);
                        String n = BileUtils.getPluginByName(args[i]).getName();
                        sender.sendMessage(tag + "Loaded " + ChatColor.WHITE + n + ChatColor.GRAY + " from " + ChatColor.WHITE + s.getName());
                    }

                    catch(Throwable e)
                    {
                        sender.sendMessage(tag + "Couldn't load \"" + args[i] + "\".");
                        e.printStackTrace();
                    }
                }

                catch(Throwable e)
                {
                    sender.sendMessage(tag + "Couldn't load or find \"" + args[i] + "\".");
                    e.printStackTrace();
                }
            }
        }

        else
        {
            sender.sendMessage(tag + "/bile load <PLUGIN>");
        }
    }
}
