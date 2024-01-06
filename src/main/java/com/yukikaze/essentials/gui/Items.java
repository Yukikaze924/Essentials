package com.yukikaze.essentials.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class Items {

    public static ItemStack getMarketShop() {

        ItemStack item = new ItemStack(Material.EMERALD, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName(ChatColor.DARK_GREEN+"Market");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"Buy everything you need at Market ~");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getPawnShop() {

        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName(ChatColor.YELLOW+"Pawnshop");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"Sell your stuffs ~");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getTouristMap() {

        ItemStack item = new ItemStack(Material.FILLED_MAP, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName(ChatColor.AQUA+"Tourist Map");

        item.setItemMeta(meta);

        return item;
    }

}
