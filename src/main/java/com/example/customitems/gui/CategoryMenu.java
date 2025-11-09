package com.example.customitems.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.example.customitems.CustomItemsPlugin;
import com.example.customitems.items.CustomItem;
import com.example.customitems.items.ItemBuilder;
import java.util.List;

public class CategoryMenu {
    private final Player viewer;
    private final Inventory inventory;
    private final String category;
    private final List<CustomItem> items;
    private int currentPage = 0;
    
    public CategoryMenu(String category, Player player) {
        this.category = category;
        this.viewer = player;
        this.inventory = Bukkit.createInventory(null, 54, "Category: " + category);
        this.items = CustomItemsPlugin.getInstance().getItemManager().getItemsByCategory(category);
        setupItems();
    }
    
    private void setupItems() {
        // Clear inventory
        inventory.clear();
        
        // Calculate pages
        int itemsPerPage = 45; // 9x5 grid for items
        int startIndex = currentPage * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, items.size());
        
        // Add items
        for (int i = startIndex; i < endIndex; i++) {
            CustomItem item = items.get(i);
            inventory.addItem(item.createItemStack());
        }
        
        // Add navigation if needed
        if (items.size() > itemsPerPage) {
            setupNavigation();
        }
        
        // Add back button
        ItemStack backButton = new ItemBuilder("BARRIER")
            .setName("§cBack to Menu")
            .build();
        inventory.setItem(49, backButton);
    }
    
    private void setupNavigation() {
        if (currentPage > 0) {
            ItemStack prevButton = new ItemBuilder("ARROW")
                .setName("§ePrevious Page")
                .build();
            inventory.setItem(45, prevButton);
        }
        
        if ((currentPage + 1) * 45 < items.size()) {
            ItemStack nextButton = new ItemBuilder("ARROW")
                .setName("§eNext Page")
                .build();
            inventory.setItem(53, nextButton);
        }
    }
    
    public void nextPage() {
        if ((currentPage + 1) * 45 < items.size()) {
            currentPage++;
            setupItems();
        }
    }
    
    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            setupItems();
        }
    }
    
    public void show() {
        viewer.openInventory(inventory);
    }
    
    public String getCategory() {
        return category;
    }
}