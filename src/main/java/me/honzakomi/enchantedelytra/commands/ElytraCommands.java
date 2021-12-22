package me.honzakomi.enchantedelytra.commands;

import me.honzakomi.enchantedelytra.EnchantedElytra;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElytraCommands implements CommandExecutor {

    FileConfiguration config = EnchantedElytra.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player to execute this command!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("ee")) {

            Player p = (Player) sender;

            if (args.length == 0) {
                //DISPLAY HELP
                p.sendMessage("Hello and Welcome to Enchanted Elytra Plugin!");

                return true;

            } else if (args[0].equalsIgnoreCase(config.getString("Command.Disable"))) {
                //ENABLE NORMAL ELYTRA FUNCTIONS

                if (p.hasPermission(config.getString("Permission.Disable")) && !(p.getGameMode() == GameMode.CREATIVE)) {

                    p.setAllowFlight(false);

                    return true;
                }

            } else if (args[0].equalsIgnoreCase(config.getString("Command.Enable"))) {
                //ENABLE FLY FUNCTION ONLY IF HE HAS ELYTRA WITH SPECIFIC ENCHANTMENT

                if (p.getEquipment().getChestplate() == null || p.getEquipment().getChestplate().getEnchantments() == null) {
                    p.sendMessage(ChatColor.RED + "You need to wear an elytra with " + config.getString("EnchantmentName") + "enchantment to be able to fly!");
                    return true;
                }

                if (!(p.hasPermission(config.getString("Permission.Enable")))) {
                    p.sendMessage(ChatColor.RED + "You need to have a permission to do that!");
                    return true;
                }

                if (p.getEquipment().getChestplate().getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey())) && p.hasPermission(config.getString("Permission.Enable"))) {

                    p.setAllowFlight(true);

                    return true;
                }

            } else if (args[0].equalsIgnoreCase(config.getString("Command.Enchant")) && p.hasPermission(config.getString("Permission.Enchant"))) {

                if (p.getInventory().getItemInMainHand().getType() == Material.ELYTRA && !(p.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey())))) {

                    ItemStack item = p.getInventory().getItemInMainHand();

                    item.addUnsafeEnchantment(EnchantedElytra.flyEnchantment, 1);

                    ItemMeta meta = item.getItemMeta();

                    List<String> lore = new ArrayList<>();

                    if (meta.getLore() != null) {
                        lore = meta.getLore();
                    }

                    lore.add(ChatColor.GRAY + config.getString("EnchantmentName"));

                    meta.setLore(lore);

                    item.setItemMeta(meta);

                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "You have to hold an elytra in your hand for it to work");
                    return true;
                }

            }

        }

        return true;
    }

}
