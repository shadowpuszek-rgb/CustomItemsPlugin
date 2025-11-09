package com.example.customitems.preview;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import com.example.customitems.CustomItemsPlugin;
import com.example.customitems.items.CustomItem;
import java.util.HashMap;
import java.util.Map;

public class ItemPreview {
    private static final Map<Player, ArmorStand> previews = new HashMap<>();
    private static final Map<Player, BukkitRunnable> animations = new HashMap<>();
    
    public static void startPreview(Player player, CustomItem item) {
        stopPreview(player); // Clean up any existing preview
        
        Location loc = player.getLocation().add(2, 1.5, 0);
        ArmorStand stand = player.getWorld().spawn(loc, ArmorStand.class);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setHelmet(item.createItemStack());
        
        previews.put(player, stand);
        startAnimation(player, stand);
    }
    
    public static void stopPreview(Player player) {
        if (previews.containsKey(player)) {
            previews.get(player).remove();
            previews.remove(player);
        }
        if (animations.containsKey(player)) {
            animations.get(player).cancel();
            animations.remove(player);
        }
    }
    
    public static void rotateLeft(Player player) {
        ArmorStand stand = previews.get(player);
        if (stand != null) {
            EulerAngle rotation = stand.getHeadPose();
            stand.setHeadPose(rotation.add(0, -0.5, 0));
        }
    }
    
    public static void rotateRight(Player player) {
        ArmorStand stand = previews.get(player);
        if (stand != null) {
            EulerAngle rotation = stand.getHeadPose();
            stand.setHeadPose(rotation.add(0, 0.5, 0));
        }
    }
    
    private static void startAnimation(Player player, ArmorStand stand) {
        BukkitRunnable animation = new BukkitRunnable() {
            double y = 0;
            @Override
            public void run() {
                if (!player.isOnline() || !previews.containsKey(player)) {
                    cancel();
                    return;
                }
                y += 0.1;
                Location loc = stand.getLocation();
                loc.setY(loc.getY() + Math.sin(y) * 0.1);
                stand.teleport(loc);
            }
        };
        
        animation.runTaskTimer(CustomItemsPlugin.getInstance(), 0L, 1L);
        animations.put(player, animation);
    }
}