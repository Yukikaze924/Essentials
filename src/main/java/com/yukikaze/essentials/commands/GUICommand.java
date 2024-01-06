package com.yukikaze.essentials.commands;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.gui.Gui;
import com.yukikaze.essentials.models.PlayerTable;
import com.yukikaze.essentials.data.Mysql;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;

public class GUICommand implements CommandExecutor {

    private final DataStorage dataStorage = Essentials.dataStorage;

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            PlayerTable playerTable;
            try {
                playerTable = dataStorage.getPlayerTableFromDatabase(player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Inventory mainMenu;
            try {
                mainMenu = Gui.openMainMenuForPlayer(player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            player.openInventory(mainMenu);
        }

        return true;
    }
}