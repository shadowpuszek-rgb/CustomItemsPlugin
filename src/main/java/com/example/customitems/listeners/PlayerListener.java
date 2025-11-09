package com.example.customitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.example.customitems.CustomItemsPlugin;

public class PlayerListener implements Listener {
    private final CustomItemsPlugin plugin;
    
    public PlayerListener(CustomItemsPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Send resource pack to player
        plugin.getResourcePackManager().sendResourcePack(event.getPlayer());
    }
}