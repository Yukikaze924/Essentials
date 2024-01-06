package com.yukikaze.essentials.data;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.models.PlayerTable;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Date;

public class Sqlite implements DataStorage {


    @Override
    public Connection getConnection() throws SQLException {

        String jdbc = "jdbc:sqlite:" + Essentials.main.getDataFolder().getAbsolutePath() + "/essentials.db";

        return DriverManager.getConnection(jdbc);
    }

    @Override
    public void initializeDatabase() throws SQLException {

        Statement statement = getConnection().createStatement();

        statement.execute("""
                CREATE TABLE IF NOT EXISTS players (
                uuid TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                identity VARCHAR NOT NULL,
                birthday DATE,
                last_in_game DATE
                )
                """);

        statement.close();
    }

    @Override
    public PlayerTable getPlayerTableFromDatabase(Player player) throws SQLException {

        PlayerTable playerTable = getPlayerTableByUUID(player.getUniqueId().toString());

        if(playerTable == null) {
            playerTable = new PlayerTable(player.getUniqueId().toString(), player.getDisplayName(), "Survivor", new Date(), new Date());
            createPlayerTable(playerTable);
        }

        return playerTable;
    }

    @Override
    public PlayerTable getPlayerTableByUUID(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM players WHERE uuid = ?");

        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        PlayerTable playerTable;

        if(resultSet.next()) {
            playerTable = new PlayerTable(resultSet.getString("uuid"), resultSet.getString("name"), resultSet.getString("identity"), resultSet.getDate("birthday"), resultSet.getDate("last_in_game") );

            statement.close();

            return playerTable;
        }

        statement.close();

        return null;
    }

    @Override
    public void createPlayerTable(PlayerTable playerTable) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO players(uuid, name, identity, birthday, last_in_game) VALUES (?, ?, ?, ?, ?)");

        statement.setString(1, playerTable.getPlayerUUID());
        statement.setString(2, playerTable.getPlayerName());
        statement.setString(3, playerTable.getPlayerIdentity());
        statement.setDate(4, new java.sql.Date(playerTable.getLastInGame().getTime()));
        statement.setDate(5, new java.sql.Date(playerTable.getBirthday().getTime()));

        statement.executeUpdate();

        statement.close();
    }

    @Override
    public void updatePlayerTable(PlayerTable playerTable) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("UPDATE players SET uuid =?, name=?, identity=?, birthday=?, last_in_game=? WHERE uuid = ?");

        statement.setString(1, playerTable.getPlayerUUID());
        statement.setString(2, playerTable.getPlayerName());
        statement.setString(3, playerTable.getPlayerIdentity());
        statement.setDate(4, new java.sql.Date(playerTable.getBirthday().getTime()));
        statement.setDate(5, new java.sql.Date(playerTable.getLastInGame().getTime()));

        // uuid condition set, be careful on this. It must be the last num in queue
        statement.setString(6, playerTable.getPlayerUUID());

        statement.executeUpdate();

        statement.close();
    }
}
