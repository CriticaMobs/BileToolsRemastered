package com.volmit.bile.runnable;

import com.volmit.bile.BileTools;
import com.volmit.bile.storage.MapStorage;
import com.volmit.bile.utils.BileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUpdater extends BukkitRunnable {

    private int cd = 10;
    private BileTools bileTools;
    private String tag = ChatColor.GREEN + "[" + ChatColor.DARK_GRAY + "Bile" + ChatColor.GREEN + "]: " + ChatColor.GRAY;

    public FileUpdater(BileTools bileTools) {
        this.bileTools = bileTools;
        runTaskTimerAsynchronously (bileTools, 20L, 20L);
    }

    @Override
    public void run()
    {
        if(cd > 0)
        {
            cd--;
        }

        for(File i : bileTools.getDataFolder ().getParentFile ().listFiles())
        {
            if(i.getName().toLowerCase().endsWith(".jar") && i.isFile())
            {
                if(!MapStorage.mod.containsKey(i))
                {
                    Bukkit.getLogger ().log (Level.INFO, "Now Tracking: " + i.getName());
                    MapStorage.mod.put(i, i.length());
                    MapStorage.las.put(i, i.lastModified());

                    if(cd == 0)
                    {
                        try {
                            BileUtils.load(i);

                            for(Player k : Bukkit.getOnlinePlayers())
                            {
                                if(k.hasPermission("bile.use"))
                                {
                                    k.sendMessage(tag + "Hot Dropped " + ChatColor.WHITE + i.getName());
                                    k.playSound(k.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1f, 1.9f);
                                }
                            }
                        }catch (Exception ex)
                        {
                            Bukkit.broadcast (tag + "Failed to hot drop " + ChatColor.RED + i.getName(), "bile.use");
                        }
                    }
                }

                if(MapStorage.mod.get(i) != i.length() || MapStorage.las.get(i) != i.lastModified())
                {
                    MapStorage.mod.put(i, i.length());
                    MapStorage.las.put(i, i.lastModified());

                    for(Plugin j : Bukkit.getServer().getPluginManager().getPlugins())
                    {
                        if(BileUtils.getPluginFile(j).getName().equals(i.getName()))
                        {
                            getLogger().log(Level.INFO, "File change detected: " + i.getName());
                            getLogger().log(Level.INFO, "Identified Plugin: " + j.getName() + " <-> " + i.getName());
                            getLogger().log(Level.INFO, "Reloading: " + j.getName());
                            Bukkit.getScheduler ().runTaskLaterAsynchronously (bileTools, () ->
                            {
                                try {
                                    BileUtils.reload(j);
                                    Bukkit.broadcast (tag + "Reloaded " + ChatColor.WHITE + j.getName(), "bile.use");
                                }catch (Exception ex) {
                                    Bukkit.broadcast (tag + "Failed to Reload " + ChatColor.RED + j.getName(), "bile.use");
                                }
                            }, 5L);
                            break;
                        }
                    }
                }
            }
        }
    }

    public Logger getLogger()
    {return Bukkit.getLogger ();}
}
