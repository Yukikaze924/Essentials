package com.yukikaze.essentials.data;

import com.yukikaze.essentials.models.PlayerTable;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.SQLException;

public class Yaml implements DataStorage {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void initializeDatabase() {

    }

    @Override
    public PlayerTable getPlayerTableFromDatabase(Player player) {
        return null;
    }

    @Override
    public PlayerTable getPlayerTableByUUID(String uuid) {
        return null;
    }

    @Override
    public void createPlayerTable(PlayerTable playerTable) {

    }

    @Override
    public void updatePlayerTable(PlayerTable playerTable) {

    }
}
