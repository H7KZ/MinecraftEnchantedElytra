package me.honzakomi.enchantedelytra;

import me.honzakomi.enchantedelytra.commands.ElytraCommands;
import me.honzakomi.enchantedelytra.enchants.FlyEnchantment;
import me.honzakomi.enchantedelytra.events.ElytraAnvil;
import me.honzakomi.enchantedelytra.events.ElytraRemove;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class EnchantedElytra extends JavaPlugin {

    public static EnchantedElytra plugin;
    public static FlyEnchantment flyEnchantment;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        plugin = this;

        config.options().copyDefaults();
        saveDefaultConfig();

        System.out.println("EE Config loaded!");

        getCommand("ee").setExecutor(new ElytraCommands());

        System.out.println("EE Commands loaded!");

        getServer().getPluginManager().registerEvents(new ElytraRemove(), this);
        getServer().getPluginManager().registerEvents(new ElytraAnvil(), this);

        System.out.println("EE Events loaded!");

        flyEnchantment = new FlyEnchantment("eefly");
        registerEnchantment(flyEnchantment);

        System.out.println("EE Enchantments loaded & registered!");

        System.out.println("EE Plugin has started!");
    }

    @Override
    public void onDisable() {

        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(flyEnchantment.getKey())) {
                byKey.remove(flyEnchantment.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(flyEnchantment.getName())) {
                byName.remove(flyEnchantment.getName());
            }
        } catch (Exception ignored) { }

        System.out.println("EE Enchantments unregistered!");

        System.out.println("EE Plugin has been shutdown!");
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

}
