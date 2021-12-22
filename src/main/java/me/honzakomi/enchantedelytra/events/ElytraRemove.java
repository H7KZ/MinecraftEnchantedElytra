package me.honzakomi.enchantedelytra.events;

import me.honzakomi.enchantedelytra.EnchantedElytra;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.event.inventory.InventoryType.PLAYER;

public class ElytraRemove implements Listener {

    @EventHandler
    public static void onRemovalOfElytra(InventoryClickEvent e) {

        Inventory inv = e.getClickedInventory();

        if (!(e.getWhoClicked() instanceof Player) && !(inv.getType() == PLAYER) && !(e.getRawSlot() == 6) && !(e.getCurrentItem().getType() == Material.ELYTRA)) { return; }

        ItemStack item = e.getCurrentItem();

        Player p = (Player) e.getWhoClicked();

        try {
            if (item.getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey())) && !(p.getGameMode() == GameMode.CREATIVE)) {
                p.setAllowFlight(false);
                return;
            }
        } catch (NullPointerException r) {
            //NOTHING - IT WORKS FINE JUST PLAYER CLICKED OUTSIDE OF SLOTS
        }
    }

    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

        Inventory inv = p.getInventory();

        if (inv.getItem(6).getType() == Material.ELYTRA) {
            ItemStack item = inv.getItem(6);
            if (item.getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey())) && !(p.getGameMode() == GameMode.CREATIVE)) {
                p.setAllowFlight(false);
                return;
            }
        }

    }

}
