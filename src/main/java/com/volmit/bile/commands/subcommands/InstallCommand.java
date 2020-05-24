package com.volmit.bile.commands.subcommands;

import com.volmit.bile.BileTools;
import com.volmit.bile.utils.BileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstallCommand
extends SubCommand{

    private BileTools bileTools;

    public InstallCommand(BileTools bileTools) {
        super ("install");
        this.bileTools = bileTools;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1)
        {
            try
            {
                for(File i : new File(bileTools.getDataFolder(), "library").listFiles())
                {
                    if(i.getName().toLowerCase().equals(args[1].toLowerCase()))
                    {
                        if(args.length == 2)
                        {
                            long highest = -100000;
                            File latest = null;

                            for(File j : i.listFiles())
                            {
                                String v = j.getName().replace(".jar", "");
                                List<Integer> d = new ArrayList<Integer> ();

                                for(char k : v.toCharArray())
                                {
                                    if(Character.isDigit(k))
                                    {
                                        d.add(Integer.valueOf(k + ""));
                                    }
                                }

                                Collections.reverse(d);
                                long g = 0;

                                for(int k = 0; k < d.size(); k++)
                                {
                                    g += (Math.pow(d.get(k), (k + 2)));
                                }

                                if(g > highest)
                                {
                                    highest = g;
                                    latest = j;
                                }
                            }

                            if(latest != null)
                            {
                                File ff = new File(BileUtils.getPluginsFolder(), i.getName() + "-" + latest.getName());
                                BileUtils.copy(latest, ff);
                                BileUtils.load(ff);
                                sender.sendMessage(tag + "Installed " + ChatColor.WHITE + ff.getName() + ChatColor.GRAY + " from library.");
                            }
                        }

                        else
                        {
                            for(File j : i.listFiles())
                            {
                                String v = j.getName().replace(".jar", "");

                                if(v.equals(args[2]))
                                {
                                    File ff = new File(BileUtils.getPluginsFolder(), i.getName() + "-" + v);
                                    BileUtils.copy(j, ff);
                                    BileUtils.load(ff);
                                    sender.sendMessage(tag + "Installed " + ChatColor.WHITE + ff.getName() + ChatColor.GRAY + " from library.");
                                }
                            }
                        }
                    }
                }
            }

            catch(Throwable e)
            {
                sender.sendMessage(tag + "Couldn't install or find \"" + args[1] + "\".");
                e.printStackTrace();
            }
        }

        else
        {
            sender.sendMessage(tag + "/bile install <PLUGIN> [VERSION]");
        }
    }
}
