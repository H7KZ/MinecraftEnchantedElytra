package me.honzakomi.enchantedelytra.enchants;

import me.honzakomi.enchantedelytra.EnchantedElytra;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class FlyEnchantment extends Enchantment {

    public FlyEnchantment(String namespace) {
        super(new NamespacedKey(EnchantedElytra.plugin, namespace));
    }

    @Override
    public String getName() {
        return "eefly";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public NamespacedKey getKey() {
        return super.getKey();
    }


    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
