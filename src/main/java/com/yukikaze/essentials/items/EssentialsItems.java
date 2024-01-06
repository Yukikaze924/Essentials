package com.yukikaze.essentials.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class EssentialsItems {

    public static ItemStack scimitar;

    public static ItemStack firstAid;

    public static void init() {

        createScimitar();

    }

    public static void createScimitar() {
        // Item
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        // Meta
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName(ChatColor.WHITE+"Scimitar");
        meta.setUnbreakable(true);
        // Lore
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"Scimitar is a short sword with a curved blade\n that broadens toward the point, used originally in Eastern countries");
        // Setup
        meta.setLore(lore);
        item.setItemMeta(meta);

        scimitar = item;
    }

    public static void createFirstAid() {
        // Item
        ItemStack item = new ItemStack(Material.APPLE, 1);
        // Meta
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName(ChatColor.RED+"First Aid Kit");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        // Lore
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Refill your HP");
        // Setup
        meta.setLore(lore);
        item.setItemMeta(meta);

        firstAid = item;
    }

}
