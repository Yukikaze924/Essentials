package com.yukikaze.essentials.commands;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.models.PlayerTable;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

public class EssentialsCommands implements CommandExecutor {

    public final DataStorage dataStorage = Essentials.dataStorage;

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        // If sender is the console
        if(!(sender instanceof Player)) { return true; }

        // Assign the player object obtained from the event to a variable - player
        Player player = (Player) sender;

        // Command /treat
        if(cmd.getName().equalsIgnoreCase("treat")) {

            double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue();

            player.setHealth(maxHealth);
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.LIGHT_PURPLE+"You have been cure");
        }

        // Command /me
        if(cmd.getName().equalsIgnoreCase("id")) {

            try {

                PlayerTable playerTable = dataStorage.getPlayerTableFromDatabase(player);

                player.sendMessage(ChatColor.LIGHT_PURPLE+" ********************************************");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" *");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" * " + ChatColor.BOLD+ChatColor.GOLD+"       Blaine County");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" *");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" * " + ChatColor.YELLOW+playerTable.getPlayerUUID());
                player.sendMessage(ChatColor.LIGHT_PURPLE+" *");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" * " + ChatColor.GRAY+"Name: " + ChatColor.AQUA+playerTable.getPlayerName());
                player.sendMessage(ChatColor.LIGHT_PURPLE+" * " + ChatColor.GRAY+"Identity: " + ChatColor.YELLOW+playerTable.getPlayerIdentity());
                player.sendMessage(ChatColor.LIGHT_PURPLE+" *");
                player.sendMessage(ChatColor.LIGHT_PURPLE+" ********************************************");

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }
        }

        return true;
    }
}
