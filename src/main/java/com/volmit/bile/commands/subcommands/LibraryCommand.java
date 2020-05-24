package com.volmit.bile.commands.subcommands;

import com.volmit.bile.BileTools;
import com.volmit.bile.utils.BileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryCommand
extends SubCommand{

    private BileTools bileTools;

    public LibraryCommand(BileTools bileTools) {
        super ("library");
        this.bileTools = bileTools;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
            if(args.length == 1)
            {
                try
                {
                    for(File i : new File(bileTools.getDataFolder(), "library").listFiles())
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
                            boolean inst = false;
                            String v = null;

                            for(File k : BileUtils.getPluginsFolder().listFiles())
                            {
                                if(BileUtils.isPluginJar(k) && i.getName().equalsIgnoreCase(BileUtils.getPluginName(k)))
                                {
                                    v = BileUtils.getPluginVersion(k);
                                    inst = true;
                                    break;
                                }
                            }

                            if(inst)
                            {
                                sender.sendMessage(tag + i.getName() + " " + ChatColor.GREEN + "(" + v + " installed) " + ChatColor.WHITE + latest.getName().replace(".jar", "") + ChatColor.GRAY + " (latest)");
                            }

                            else
                            {
                                sender.sendMessage(tag + i.getName() + " " + ChatColor.WHITE + latest.getName().replace(".jar", "") + ChatColor.GRAY + " (latest)");
                            }
                        }
                    }
                }

                catch(Throwable e)
                {
                    sender.sendMessage(tag + "Couldn't list library.");
                    e.printStackTrace();
                }
            }

            else if(args.length > 1)
            {
                try
                {
                    boolean dx = false;

                    for(File i : new File(bileTools.getDataFolder(), "library").listFiles())
                    {
                        if(!i.getName().equalsIgnoreCase(args[1]))
                        {
                            continue;
                        }

                        dx = true;
                        long highest = -100000;
                        File latest = null;

                        for(File j : i.listFiles())
                        {
                            String v = j.getName().replace(".jar", "");
                            List<Integer> d = new ArrayList<Integer>();

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
                            for(File j : i.listFiles())
                            {
                                sender.sendMessage(tag + j.getName().replace(".jar", ""));
                            }

                            sender.sendMessage(tag + i.getName() + " " + ChatColor.WHITE + latest.getName().replace(".jar", "") + ChatColor.GRAY + " (latest)");
                        }
                    }

                    if(!dx)
                    {
                        sender.sendMessage(tag + "Couldn't find " + args[1] + " in library.");
                    }
                }

                catch(Throwable e)
                {
                    sender.sendMessage(tag + "Couldn't list library.");
                    e.printStackTrace();
                }
            }

            else
            {
                sender.sendMessage(tag + "/bile library [PLUGIN]");
            }
    }
}
