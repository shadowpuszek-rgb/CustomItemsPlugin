package com.example.customitems.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.example.customitems.CustomItemsPlugin;
import java.util.List;

public class ItemBrowser {
    private final Player viewer;
    private final Inventory inventory;
    
    public ItemBrowser(Player player) {
        this.viewer = player;
        this.inventory = Bukkit.createInventory(null, 27, "Custom Items");
        setupCategories();
    }
    
    private void setupCategories() {
        List<String> categories = CustomItemsPlugin.getInstance().getItemManager().getCategories();
        
        // Tech category
        if (categories.contains("tech")) {
            ItemStack tech = new ItemStack(Material.REDSTONE);
            tech.getItemMeta().setDisplayName("§bTechnology Items");
            inventory.setItem(10, tech);
        }
        
        // Vehicles category
        if (categories.contains("vehicles")) {
            ItemStack vehicles = new ItemStack(Material.MINECART);
            vehicles.getItemMeta().setDisplayName("§6Vehicles");
            inventory.setItem(12, vehicles);
        }
        
        // Magic category
        if (categories.contains("magic")) {
            ItemStack magic = new ItemStack(Material.BLAZE_ROD);
            magic.getItemMeta().setDisplayName("§dMagical Items");
            inventory.setItem(14, magic);
        }
        
        // Pets category
        if (categories.contains("pets")) {
            ItemStack pets = new ItemStack(Material.BONE);
            pets.getItemMeta().setDisplayName("§aPets");
            inventory.setItem(16, pets);
        }
    }
    
    public void show() {
        viewer.openInventory(inventory);
    }
}