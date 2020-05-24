package com.volmit.bile.commands.subcommands;

import com.volmit.bile.utils.BileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ReloadCommand
extends SubCommand{
    public ReloadCommand() {
        super ("reload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1)
        {
            for(int i = 1; i < args.length; i++)
            {
                try
                {
                    Plugin s = BileUtils.getPluginByName(args[i]);

                    if(s == null)
                    {
                        sender.sendMessage(tag + "Couldn't find \"" + args[i] + "\".");
                        continue;
                    }

                    try
                    {
                        String sn = s.getName();
                        BileUtils.reload(s);
                        File n = BileUtils.getPluginFile(args[i]);
                        sender.sendMessage(tag + "Reloaded " + ChatColor.WHITE + sn + ChatColor.GRAY + " (" + ChatColor.WHITE + n.getName() + ChatColor.GRAY + ")");
                    }

                    catch(Throwable e)
                    {
                        sender.sendMessage(tag + "Couldn't reload \"" + args[i] + "\".");
                        e.printStackTrace();
                    }
                }

                catch(Throwable e)
                {
                    sender.sendMessage(tag + "Couldn't reload or find \"" + args[i] + "\".");
                    e.printStackTrace();
                }
            }
        }

        else
        {
            sender.sendMessage(tag + "/bile reload <PLUGIN>");
        }
    }
}
