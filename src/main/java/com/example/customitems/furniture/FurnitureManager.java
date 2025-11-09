package com.example.customitems.furniture;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import com.example.customitems.CustomItemsPlugin;

public class FurnitureManager {
    private final CustomItemsPlugin plugin;
    
    public FurnitureManager(CustomItemsPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void placeFurniture(Location location, ItemStack item, Player player) {
        // Create armor stand
        ArmorStand stand = location.getWorld().spawn(location, ArmorStand.class);
        
        // Set armor stand properties
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setCanPickupItems(false);
        stand.setCustomNameVisible(false);
        stand.setInvulnerable(true);
        
        // Set the item as head
        stand.setHelmet(item);
        
        // Set rotation based on player's direction
        float yaw = player.getLocation().getYaw();
        stand.setRotation(yaw, 0);
        
        // Store metadata
        stand.setCustomName("furniture_" + item.getItemMeta().getCustomModelData());
        
        // Add to tracking
        plugin.getConfig().set("furniture." + stand.getUniqueId(), 
            location.getWorld().getName() + "," + 
            location.getX() + "," + 
            location.getY() + "," + 
            location.getZ() + "," + 
            yaw);
        plugin.saveConfig();
    }
    
    public void removeFurniture(ArmorStand stand) {
        if (stand.getCustomName() != null && stand.getCustomName().startsWith("furniture_")) {
            plugin.getConfig().set("furniture." + stand.getUniqueId(), null);
            plugin.saveConfig();
            stand.remove();
        }
    }
    
    public boolean isFurniture(ArmorStand stand) {
        return stand.getCustomName() != null && stand.getCustomName().startsWith("furniture_");
    }
    
    public void rotateFurniture(ArmorStand stand, float angle) {
        if (isFurniture(stand)) {
            float currentYaw = stand.getLocation().getYaw();
            stand.setRotation(currentYaw + angle, 0);
        }
    }
    
    public void adjustHeight(ArmorStand stand, double amount) {
        if (isFurniture(stand)) {
            Location loc = stand.getLocation();
            loc.add(0, amount, 0);
            stand.teleport(loc);
        }
    }
}