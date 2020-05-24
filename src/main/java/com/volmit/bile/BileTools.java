package com.volmit.bile;

import com.volmit.bile.commands.BileCommand;
import com.volmit.bile.runnable.FileUpdater;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class BileTools extends JavaPlugin
{
    private static BileTools bile;
    private File folder;
    private File backoff;

    @Override
    public void onEnable()
    {
        bile = this;
        folder = getDataFolder().getParentFile();
        backoff = new File(getDataFolder(), "backoff");
        backoff.mkdirs();
        getCommand("bile").setExecutor(new BileCommand (this));
        BukkitRunnable bukkitRunnable = new FileUpdater (this);
    }

    public static BileTools getInstance()
    {return bile;}
}
