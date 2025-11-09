# CustomItemsPlugin

A powerful Minecraft plugin that adds custom items with 3D previews, similar to ItemsAdder.

## Features

- Custom Items System
  - Magic items (wands, spells, crystals)
  - Pet companions (dragons, fairies, wolves)
  - Tech gadgets
  - Vehicles

- 3D Preview System
  - Interactive item previews
  - Rotation controls
  - Floating animation
  - Detailed item information

- Resource Pack System
  - Automatic resource pack generation
  - Built-in HTTP server for resource pack hosting
  - Force resource pack option
  - Custom models and textures support

- GUI System
  - Category-based item browser
  - Easy-to-use interface
  - Preview integration
  - Quick item spawning

## Installation

1. Download the latest release from the [Releases page](https://github.com/shadowpuszek-rgb/CustomItemsPlugin/releases)
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/CustomItemsPlugin/config.yml`

## Commands

- `/customitems` - Open the main GUI menu
- `/customitems give <item> [player]` - Give a custom item
- `/customitems list` - List all available items
- `/customitems preview <item>` - Preview an item in 3D

## Configuration

The plugin uses several configuration files:

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

### items/*.yml
Item configuration files are located in the `plugins/CustomItemsPlugin/items` folder:
- `magic.yml` - Magical items and spells
- `pets.yml` - Pet companions
- `tech.yml` - Technology items
- `vehicles.yml` - Various vehicles

## Adding Custom Items

To add new items, create or edit YAML files in the `plugins/CustomItemsPlugin/items` folder:

```yaml
my_custom_item:
  name: "§bMy Custom Item"
  material: DIAMOND_SWORD
  custom_model_data: 1001
  lore:
    - "§7First line of description"
    - "§7Second line of description"
  abilities:
    - SPECIAL_ABILITY_1
    - SPECIAL_ABILITY_2
```

## Support

For issues and feature requests, please use the [GitHub Issues](https://github.com/shadowpuszek-rgb/CustomItemsPlugin/issues) page.

## Building from Source

1. Clone the repository
2. Install Maven
3. Run `mvn clean package`
4. Find the built jar in `target/` directory

## License

This project is licensed under the MIT License - see the LICENSE file for details.