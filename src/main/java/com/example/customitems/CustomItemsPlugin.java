package com.example.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import com.example.customitems.gui.MenuListener;
import com.example.customitems.items.ItemManager;
import com.example.customitems.resourcepack.ResourcePackManager;
import com.example.customitems.listeners.PlayerListener;
import java.io.File;

public class CustomItemsPlugin extends JavaPlugin {
    private static CustomItemsPlugin instance;
    private ItemManager itemManager;
    private ResourcePackManager resourcePackManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // Create plugin directory if it doesn't exist
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        
        // Save default config and resources
        saveDefaultConfig();
        saveResource("models.yml", false);
        
        // Create items directory
        File itemsDir = new File(getDataFolder(), "items");
        if (!itemsDir.exists()) {
            itemsDir.mkdirs();
            saveResource("items/magic.yml", false);
            saveResource("items/pets.yml", false);
            saveResource("items/tech.yml", false);
            saveResource("items/vehicles.yml", false);
        }
        
        // Initialize managers
        this.itemManager = new ItemManager(this);
        this.resourcePackManager = new ResourcePackManager(this);
        
        // Register listeners
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        
        // Register commands
        getCommand("customitems").setExecutor(new CustomItemsCommand(this));
        
        // Load items
        itemManager.loadItems();
        
        // Generate and host resource pack
        resourcePackManager.generateResourcePack();
        resourcePackManager.startHosting();
        
        getLogger().info("CustomItemsPlugin has been enabled successfully!");
        getLogger().info("Resource pack server running on port: " + getConfig().getInt("settings.resourcepack.port", 8080));
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