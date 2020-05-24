package com.volmit.bile.backoff;

import org.bukkit.entity.Player;

public interface BackOFF {

    public boolean isBackOff(Player p);

    public void toggleBackoff(Player p);

}
