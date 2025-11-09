package com.example.customitems.items;

import org.bukkit.inventory.ItemStack;
import java.util.List;

public class CustomItem {
    private final String id;
    private final String name;
    private final String material;
    private final int customModelData;
    private final List<String> lore;
    private final List<String> abilities;
    private final String category;

    public CustomItem(String id, String name, String material, int customModelData, List<String> lore, List<String> abilities, String category) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.customModelData = customModelData;
        this.lore = lore;
        this.abilities = abilities;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public String getCategory() {
        return category;
    }

    public ItemStack createItemStack() {
        ItemBuilder builder = new ItemBuilder(material)
            .setName(name)
            .setCustomModelData(customModelData);
        
        if (lore != null) {
            builder.setLore(lore);
        }
        
        return builder.build();
    }
}