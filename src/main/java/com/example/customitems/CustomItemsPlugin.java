package com.example.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import com.example.customitems.gui.MenuListener;
import com.example.customitems.items.ItemManager;
import com.example.customitems.resourcepack.ResourcePackManager;

public class CustomItemsPlugin extends JavaPlugin {
    private static CustomItemsPlugin instance;
    private ItemManager itemManager;
    private ResourcePackManager resourcePackManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // Save default config
        saveDefaultConfig();
        
        // Initialize managers
        this.itemManager = new ItemManager(this);
        this.resourcePackManager = new ResourcePackManager(this);
        
        // Register listeners
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        
        // Register commands
        getCommand("customitems").setExecutor(new CustomItemsCommand(this));
        
        // Load items
        itemManager.loadItems();
        
        // Generate and host resource pack
        resourcePackManager.generateResourcePack();
        resourcePackManager.startHosting();
        
        getLogger().info("CustomItemsPlugin has been enabled!");
    }
    
    @Override
    public void onDisable() {
        if (resourcePackManager != null) {
            resourcePackManager.stopHosting();
        }
        getLogger().info("CustomItemsPlugin has been disabled!");
    }
    
    public static CustomItemsPlugin getInstance() {
        return instance;
    }
    
    public ItemManager getItemManager() {
        return itemManager;
    }
    
    public ResourcePackManager getResourcePackManager() {
        return resourcePackManager;
    }
}