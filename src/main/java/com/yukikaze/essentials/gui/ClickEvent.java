package com.yukikaze.essentials.gui;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.models.PlayerTable;
import com.yukikaze.essentials.data.Mysql;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;

public class ClickEvent implements Listener {

    private final DataStorage dataStorage = Essentials.dataStorage;

    @EventHandler
    @SuppressWarnings("all")
    public void onGUIClicked(InventoryClickEvent event) throws SQLException {

        Player player = (Player) event.getWhoClicked();

        PlayerTable playerTable;
        try {
            playerTable = dataStorage.getPlayerTableFromDatabase(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(event.getView().getTitle().equalsIgnoreCase("Menu")) {
            switch (event.getCurrentItem().getType()) {

                case PLAYER_HEAD:
                    player.closeInventory();
                    player.performCommand("id");
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case GREEN_STAINED_GLASS_PANE:
                    player.closeInventory();
                    player.openInventory(Gui.openStatsMenuForPlayer(player));
                    break;

                case EMERALD:
                    player.chat("/" + Essentials.config.getString("general.shop_command.market"));
                    break;
                case GOLD_NUGGET:
                    player.chat("/" + Essentials.config.getString("general.shop_command.pawnshop"));
                    break;
            }
            event.setCancelled(true);
        }
        //
        if(event.getView().getTitle().equalsIgnoreCase("Stats")) {
            switch (event.getCurrentItem().getType()) {

                case PLAYER_HEAD:
                    player.closeInventory();
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case GREEN_STAINED_GLASS_PANE:
                    player.closeInventory();
                    player.openInventory(Gui.openMainMenuForPlayer(player));
                    break;
            }
            event.setCancelled(true);
        }
    }
}