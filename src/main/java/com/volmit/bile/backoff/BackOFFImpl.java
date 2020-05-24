package com.volmit.bile.backoff;

import org.bukkit.entity.Player;

import java.io.File;

public class BackOFFImpl implements BackOFF {

    private File backOff;

    public BackOFFImpl(File backOff)
    {
        this.backOff = backOff;
    }

    @Override
    public boolean isBackOff(Player p)
    {
        return new File (backOff, p.getUniqueId().toString()).exists();
    }

    @Override
    public void toggleBackoff(Player p) {
        if(new File(backOff, p.getUniqueId().toString()).exists())
        { new File(backOff, p.getUniqueId().toString()).delete(); }

        else
        { new File(backOff, p.getUniqueId().toString()).mkdirs(); }
    }
}
