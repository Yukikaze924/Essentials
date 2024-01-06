package com.yukikaze.essentials.gui;

import com.yukikaze.essentials.models.PlayerTable;
import com.yukikaze.essentials.data.Mysql;
import com.yukikaze.essentials.utils.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Components {

    public static ItemStack getBlank() {

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(Collections.emptyList());

        item.setItemMeta(meta);

        return item;
    }

    @SuppressWarnings("all")
    public static ItemStack getPlayerHead(Player player) throws SQLException {

        PlayerTable playerTable = new Mysql().getPlayerTableFromDatabase(player);

        // Player head
        ItemStack avatar = SkullCreator.itemFromName(player.getName());
        // meta
        ItemMeta avatar_meta = avatar.getItemMeta();
        avatar_meta.setDisplayName(ChatColor.AQUA + playerTable.getPlayerName());
        //lore
        ArrayList<String> avatar_lore = new ArrayList<>();
        avatar_lore.add(ChatColor.GOLD+playerTable.getPlayerUUID());
        //compile meta&lore together into item
        avatar_meta.setLore(avatar_lore);
        avatar.setItemMeta(avatar_meta);

        return avatar;
    }

    @SuppressWarnings("all")
    public static ItemStack getQuitBarrier() {

        // Quit button
        ItemStack quit = new ItemStack(Material.BARRIER, 1);
        // meta
        ItemMeta quit_meta = quit.getItemMeta();
        quit_meta.setDisplayName(ChatColor.RED+"Quit");
        // lore
        ArrayList<String> quit_lore = new ArrayList<>();
        quit_lore.add(ChatColor.DARK_RED+"Close the page");
        // Compiled
        quit_meta.setLore(quit_lore);
        quit.setItemMeta(quit_meta);

        return quit;
    }

    public static ItemStack getNextPage() {

        // Repeater btn
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName("§2Click to move on");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getNoPage() {

        ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);

        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta);
        meta.setDisplayName("§8Nothing over there");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getPageCount(int page) {

        ItemStack item = new ItemStack(Material.MAP, 1);

        ItemMeta meta = item.getItemMeta();

        Objects.requireNonNull(meta);
        meta.setDisplayName("§6Page: " + page);

        item.setItemMeta(meta);

        return item;
    }


}