package com.example.customitems.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.example.customitems.items.CustomItem;
import com.example.customitems.preview.ItemPreview;

public class PreviewWindow {
    private final Inventory inventory;
    private final CustomItem item;
    private final Player viewer;
    
    public PreviewWindow(Player player, CustomItem item) {
        this.viewer = player;
        this.item = item;
        this.inventory = Bukkit.createInventory(null, 27, "Item Preview: " + item.getName());
        setupPreview();
    }
    
    private void setupPreview() {
        // Preview item in center
        inventory.setItem(13, item.createItemStack());
        
        // Add rotation controls
        ItemStack rotateLeft = new ItemStack(Material.ARROW);
        ItemMeta leftMeta = rotateLeft.getItemMeta();
        leftMeta.setDisplayName("§eRotate Left");
        rotateLeft.setItemMeta(leftMeta);
        inventory.setItem(11, rotateLeft);
        
        ItemStack rotateRight = new ItemStack(Material.ARROW);
        ItemMeta rightMeta = rotateRight.getItemMeta();
        rightMeta.setDisplayName("§eRotate Right");
        rotateRight.setItemMeta(rightMeta);
        inventory.setItem(15, rotateRight);
        
        // Add info button
        ItemStack info = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName("§eItem Information");
        info.setItemMeta(infoMeta);
        inventory.setItem(22, info);
        
        // Add back button
        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cBack to Menu");
        back.setItemMeta(backMeta);
        inventory.setItem(26, back);
    }
    
    public void show() {
        viewer.openInventory(inventory);
        ItemPreview.startPreview(viewer, item);
    }
    
    public void handleClick(int slot) {
        switch(slot) {
            case 11: // Rotate left
                ItemPreview.rotateLeft(viewer);
                break;
            case 15: // Rotate right
                ItemPreview.rotateRight(viewer);
                break;
            case 22: // Show info
                showItemInfo();
                break;
            case 26: // Back
                ItemPreview.stopPreview(viewer);
                new ItemBrowser(viewer).show();
                break;
        }
    }
    
    private void showItemInfo() {
        viewer.sendMessage("§e=== Item Information ===");
        viewer.sendMessage("§7Name: §f" + item.getName());
        viewer.sendMessage("§7Category: §f" + item.getCategory());
        if (item.getLore() != null) {
            viewer.sendMessage("§7Description:");
            for (String line : item.getLore()) {
                viewer.sendMessage("§7- " + line);
            }
        }
        if (item.getAbilities() != null) {
            viewer.sendMessage("§7Abilities:");
            for (String ability : item.getAbilities()) {
                viewer.sendMessage("§7- §f" + ability);
            }
        }
    }
}