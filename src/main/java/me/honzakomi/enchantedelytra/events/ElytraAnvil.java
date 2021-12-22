package me.honzakomi.enchantedelytra.events;

import me.honzakomi.enchantedelytra.EnchantedElytra;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static org.bukkit.event.inventory.InventoryType.ANVIL;

public class ElytraAnvil implements Listener {

    @EventHandler
    public void onElytraAnvilPickUp(InventoryClickEvent e) {

        try {
            if (!(Objects.requireNonNull(e.getClickedInventory()).getType() == ANVIL)) { return; }
        } catch (NullPointerException r) {
            //NOTHING - IT WORKS FINE JUST PLAYER CLICKED OUTSIDE OF SLOTS
        }

        try {
            if (e.getClickedInventory().getItem(0) == null || e.getClickedInventory().getItem(1) == null || e.getClickedInventory().getItem(0).getEnchantments() == null || e.getClickedInventory().getItem(1).getEnchantments() == null) {
                if (e.getClickedInventory().getItem(2) == null) { return; }
            }
        } catch (NullPointerException r) {
            //NOTHING - IT WORKS FINE JUST PLAYER CLICKED OUTSIDE OF SLOTS
        }

        try {
            if (!(Objects.requireNonNull(e.getClickedInventory().getItem(0)).getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey()))) && !(Objects.requireNonNull(e.getClickedInventory().getItem(1)).getEnchantments().containsKey(Enchantment.getByKey(EnchantedElytra.flyEnchantment.getKey())))) { return; }
        } catch (NullPointerException r) {
            //NOTHING - IT WORKS FINE JUST PLAYER CLICKED OUTSIDE OF SLOTS
        }

        try {
            if (e.getSlot() == 2 && e.getClickedInventory().getItem(2) != null) {
                Objects.requireNonNull(e.getCurrentItem()).addUnsafeEnchantment(EnchantedElytra.flyEnchantment, 1);
            }
        } catch (NullPointerException r) {
            //NOTHING - IT WORKS FINE JUST PLAYER CLICKED OUTSIDE OF SLOTS
        }
    }

}
