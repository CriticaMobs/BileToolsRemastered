package com.volmit.bile.commands.subcommands;

import com.volmit.bile.utils.BileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class UNInstallCommand
extends SubCommand{
    public UNInstallCommand() {
        super ("uninstall");
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
                        String n = BileUtils.getPluginName(s);
                        BileUtils.delete(s);

                        if(!s.exists())
                        {
                            sender.sendMessage(tag + "Uninstalled " + ChatColor.WHITE + n + ChatColor.GRAY + " from " + ChatColor.WHITE + s.getName());
                        }

                        else
                        {
                            sender.sendMessage(tag + "Uninstalled " + ChatColor.WHITE + n + ChatColor.GRAY + " from " + ChatColor.WHITE + s.getName());
                            sender.sendMessage(tag + "But it looks like we can't delete it. You may need to delete " + ChatColor.RED + s.getName() + ChatColor.GRAY + " before installing it again.");
                        }
                    }

                    catch(Throwable e)
                    {
                        sender.sendMessage(tag + "Couldn't uninstall \"" + args[i] + "\".");
                        e.printStackTrace();
                    }
                }

                catch(Throwable e)
                {
                    sender.sendMessage(tag + "Couldn't uninstall or find \"" + args[i] + "\".");
                    e.printStackTrace();
                }
            }
        }

        else
        {
            sender.sendMessage(tag + "/bile uninstall <PLUGIN>");
        }
    }
}
