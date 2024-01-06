package com.yukikaze.essentials.data;

import com.yukikaze.essentials.models.PlayerTable;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataStorage {

    Connection getConnection() throws SQLException;

    void initializeDatabase() throws SQLException;

    PlayerTable getPlayerTableFromDatabase(Player player) throws SQLException;

    PlayerTable getPlayerTableByUUID(String uuid) throws SQLException;

    void createPlayerTable(PlayerTable playerTable) throws SQLException;

    void updatePlayerTable(PlayerTable playerTable) throws SQLException;
}