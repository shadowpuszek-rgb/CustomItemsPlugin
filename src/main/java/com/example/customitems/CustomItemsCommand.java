package com.example.customitems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.example.customitems.gui.ItemBrowser;
import com.example.customitems.gui.PreviewWindow;
import com.example.customitems.items.CustomItem;

public class CustomItemsCommand implements CommandExecutor {
    private final CustomItemsPlugin plugin;
    
    public CustomItemsCommand(CustomItemsPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                new ItemBrowser((Player) sender).show();
                return true;
            }
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }
        
        switch (args[0].toLowerCase()) {
            case "give":
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /customitems give <item> [player]");
                    return true;
                }
                handleGiveCommand(sender, args);
                break;
                
            case "list":
                plugin.getItemManager().getItems().forEach(item -> 
                    sender.sendMessage("§7- §f" + item.getName())
                );
                break;
                
            case "preview":
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cOnly players can preview items!");
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /customitems preview <item>");
                    return true;
                }
                CustomItem item = plugin.getItemManager().getItem(args[1]);
                if (item == null) {
                    sender.sendMessage("§cItem not found: " + args[1]);
                    return true;
                }
                // Show item preview
                new PreviewWindow((Player) sender, item).show();
                break;
                
            default:
                sender.sendMessage("§cUnknown subcommand: " + args[0]);
                sender.sendMessage("§cAvailable commands:");
                sender.sendMessage("§7- §f/customitems");
                sender.sendMessage("§7- §f/customitems give <item> [player]");
                sender.sendMessage("§7- §f/customitems list");
                sender.sendMessage("§7- §f/customitems preview <item>");
        }
        
        return true;
    }
    
    private void handleGiveCommand(CommandSender sender, String[] args) {
        Player target;
        if (args.length > 2) {
            target = plugin.getServer().getPlayer(args[2]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found: " + args[2]);
                return;
            }
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            sender.sendMessage("§cPlease specify a player!");
            return;
        }
        
        CustomItem item = plugin.getItemManager().getItem(args[1]);
        if (item == null) {
            sender.sendMessage("§cItem not found: " + args[1]);
            return;
        }
        
        target.getInventory().addItem(item.createItemStack());
        sender.sendMessage("§aGave " + item.getName() + " §ato " + target.getName());
        if (target != sender) {
            target.sendMessage("§aYou received " + item.getName());
        }
    }
}