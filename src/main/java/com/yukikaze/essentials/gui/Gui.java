package com.yukikaze.essentials.gui;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.models.PlayerTable;
import com.yukikaze.essentials.data.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class Gui {

    private static final DataStorage data = Essentials.dataStorage;

    public static PlayerTable getPlayerTable(Player player) throws SQLException {

        return data.getPlayerTableFromDatabase(player);
    }

    @SuppressWarnings("all")
    public static Inventory openMainMenuForPlayer(Player player) throws SQLException {

        PlayerTable playerTable = getPlayerTable(player);

        // Create gui inv
        Inventory gui = Bukkit.createInventory(player, 36, "Menu");

        // Feature items
        gui.setItem(10, Items.getMarketShop());
        gui.setItem(12, Items.getPawnShop());
        gui.setItem(14, Items.getTouristMap());
        gui.setItem(16, new ItemStack(Material.WOLF_SPAWN_EGG));

        // Base items
        gui.setItem(27, Components.getPlayerHead(player));
        gui.setItem(30, Components.getNoPage());
        gui.setItem(31, Components.getPageCount(1));
        gui.setItem(32, Components.getNextPage());
        gui.setItem(35, Components.getQuitBarrier());

        for (int i = 0; i < gui.getSize(); i++) {
            if (gui.getItem(i) == null) {
                gui.setItem(i, Components.getBlank());
            }
        }

        return gui;
    }

//    public static Inventory openMarketForPlayer(Player player) {
//
//        Inventory gui = Bukkit.createInventory(player, 36, "Market");
//
//        gui.setItem();
//
//    }

    public static Inventory openStatsMenuForPlayer(Player player) throws SQLException {

        Inventory gui = Bukkit.createInventory(player, 36, "Stats");

        gui.setItem(27, Components.getPlayerHead(player));
        gui.setItem(30, Components.getNextPage());
        gui.setItem(31, Components.getPageCount(2));
        gui.setItem(32, Components.getNoPage());
        gui.setItem(35, Components.getQuitBarrier());

        for (int i = 0; i < gui.getSize(); i++) {
            if (gui.getItem(i) == null) {
                gui.setItem(i, Components.getBlank());
            }
        }

        return gui;
    }

}
