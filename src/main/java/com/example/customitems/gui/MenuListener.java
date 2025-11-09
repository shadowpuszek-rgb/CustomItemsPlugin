package com.example.customitems.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import com.example.customitems.preview.ItemPreview;
import com.example.customitems.items.CustomItem;
import com.example.customitems.CustomItemsPlugin;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        
        // Prevent item taking
        event.setCancelled(true);
        
        if (title.startsWith("Custom Items")) {
            handleMainMenu(event);
        } else if (title.startsWith("Category:")) {
            handleCategoryMenu(event);
        } else if (title.startsWith("Item Preview:")) {
            handlePreviewMenu(event);
        }
    }
    
    private void handleMainMenu(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
        String category = event.getCurrentItem().getItemMeta().getDisplayName();
        new CategoryMenu(category, (Player) event.getWhoClicked()).show();
    }
    
    private void handleCategoryMenu(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        
        Player player = (Player) event.getWhoClicked();
        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
        
        if (itemName.equals("§cBack to Menu")) {
            new ItemBrowser(player).show();
            return;
        }
        
        // Get the custom item and show preview
        CustomItem item = CustomItemsPlugin.getInstance().getItemManager().getItem(itemName);
        if (item != null) {
            new PreviewWindow(player, item).show();
        }
    }
    
    private void handlePreviewMenu(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        // We can't reliably get holder cast here; handle by title
        String title = event.getView().getTitle();
        if (!title.startsWith("Item Preview:")) return;
        // Delegate to PreviewWindow by creating a temporary instance isn't ideal, but PreviewWindow handles show and preview lifecycle via ItemPreview
        // Handle basic interactions
        int slot = event.getSlot();
        Player player = (Player) event.getWhoClicked();
        switch (slot) {
            case 11:
                ItemPreview.rotateLeft(player);
                break;
            case 15:
                ItemPreview.rotateRight(player);
                break;
            case 22:
                player.sendMessage("§eOpened item information (see chat)");
                break;
            case 26:
                new ItemBrowser(player).show();
                break;
        }
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().startsWith("Item Preview:")) {
            ItemPreview.stopPreview((Player) event.getPlayer());
        }
    }
}