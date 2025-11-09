package com.example.customitems.items;

import com.example.customitems.CustomItemsPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    private final CustomItemsPlugin plugin;
    private final Map<String, CustomItem> items;
    private final Map<String, List<CustomItem>> categories;

    public ItemManager(CustomItemsPlugin plugin) {
        this.plugin = plugin;
        this.items = new HashMap<>();
        this.categories = new HashMap<>();
    }

    public void loadItems() {
        File itemsDir = new File(plugin.getDataFolder(), "items");
        if (!itemsDir.exists()) {
            itemsDir.mkdirs();
            plugin.saveResource("items/magic.yml", false);
            plugin.saveResource("items/pets.yml", false);
            plugin.saveResource("items/tech.yml", false);
            plugin.saveResource("items/vehicles.yml", false);
        }

        for (File file : itemsDir.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                loadItemFile(file);
            }
        }
    }

    private void loadItemFile(File file) {
        String category = file.getName().replace(".yml", "");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (String key : config.getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(key);
            if (section == null) continue;

            CustomItem item = new CustomItem(
                key,
                section.getString("name"),
                section.getString("material"),
                section.getInt("custom_model_data"),
                section.getStringList("lore"),
                section.getStringList("abilities"),
                category
            );

            items.put(key, item);
            categories.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
        }
    }

    public CustomItem getItem(String id) {
        return items.get(id);
    }

    public List<CustomItem> getItemsByCategory(String category) {
        return categories.getOrDefault(category, new ArrayList<>());
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories.keySet());
    }

    public List<CustomItem> getItems() {
        return new ArrayList<>(items.values());
    }
}