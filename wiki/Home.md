# CustomItemsPlugin Wiki

## Features

### Custom Items
- Magic Items
  - Wands with spell casting
  - Crystal orbs for divination
  - Magic books with learnable spells
  - Phoenix feathers for resurrection

- Pet Companions
  - Baby dragons that follow and protect
  - Fairy companions that provide light
  - Spirit wolves for combat assistance
  - Mini golems that help carry items

- Furniture System
  - Modern chairs and sofas
  - Tables and decorative items
  - Lamps with toggle functionality
  - Storage furniture

### GUI System

#### Main Menu
- Category-based navigation
- Easy-to-use interface
- Quick access to all items

#### Item Preview
- 3D item preview
- Rotation controls
- Detailed item information
- Animation system

### Resource Pack System
- Automatic pack generation
- Built-in HTTP server
- Custom models and textures
- Automatic client installation

## Configuration

### config.yml
```yaml
settings:
  resourcepack:
    port: 8080
    force: true
    generation:
      enabled: true
      modelPath: "models"
      texturePath: "textures"
```

### Adding Custom Items
Create new items in the respective category files under `plugins/CustomItemsPlugin/items/`:

```yaml
my_custom_item:
  name: "§bMy Item"
  material: PAPER
  custom_model_data: 1001
  lore:
    - "§7Description line 1"
    - "§7Description line 2"
  abilities:
    - ABILITY_1
    - ABILITY_2
```

### Custom Models
1. Create model JSON in `assets/models/`
2. Add texture in `assets/textures/`
3. Reference in item configuration

## Commands

### General Commands
- `/customitems` - Open main GUI
- `/customitems give <item> [player]` - Give items
- `/customitems list` - List all items
- `/customitems preview <item>` - Preview item

### Furniture Commands
- `/customitems furniture rotate <degrees>` - Rotate placed furniture
- `/customitems furniture remove` - Remove furniture
- `/customitems furniture adjust <height>` - Adjust furniture height

## Permissions
- `customitems.use` - Use basic features
- `customitems.give` - Give items to players
- `customitems.admin` - Access all features
- `customitems.preview` - Use preview system
- `customitems.furniture` - Place and manage furniture

## API Usage

### Adding Custom Items
```java
CustomItem item = new CustomItem(
    "my_item",
    "§bMy Item",
    "DIAMOND_SWORD",
    1001,
    Arrays.asList("§7Line 1", "§7Line 2"),
    Arrays.asList("ABILITY_1", "ABILITY_2"),
    "my_category"
);

CustomItemsPlugin.getInstance().getItemManager().registerItem(item);
```

### Furniture API
```java
FurnitureManager fm = CustomItemsPlugin.getInstance().getFurnitureManager();

// Place furniture
fm.placeFurniture(location, itemStack, player);

// Rotate furniture
fm.rotateFurniture(armorStand, 45.0f);

// Remove furniture
fm.removeFurniture(armorStand);
```

## Resource Pack Development

### Model Format
```json
{
  "parent": "item/handheld",
  "textures": {
    "layer0": "customitems:item/my_texture"
  },
  "display": {
    "thirdperson_righthand": {
      "rotation": [0, -90, 55],
      "translation": [0, 4.0, 0.5],
      "scale": [0.85, 0.85, 0.85]
    }
  }
}
```

### Adding New Models
1. Create texture file in `assets/textures/item/`
2. Create model JSON in `assets/models/item/`
3. Add item configuration in appropriate category file
4. Update custom model data in configuration

## Troubleshooting

### Resource Pack Issues
1. Check server port availability
2. Verify resource pack generation
3. Check client resource pack settings
4. Validate model/texture paths

### Performance Tips
- Use minimal textures sizes
- Optimize model complexity
- Cache frequently used items
- Use efficient commands

## Support and Contributing
- Report issues on GitHub
- Submit pull requests
- Join our Discord community
- Check for updates regularly