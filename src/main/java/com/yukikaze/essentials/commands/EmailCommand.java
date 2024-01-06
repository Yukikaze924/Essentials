package com.yukikaze.essentials.commands;

import com.yukikaze.essentials.Essentials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class EmailCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {

            if(command.getName().equalsIgnoreCase("email")) {
                if(args.length==1) {
                    try {

                        player.sendMessage(ChatColor.GOLD+"You are in Text Editor now\n");

                    } catch (IllegalArgumentException e) {
                        player.sendMessage("&cThis isn't a valid player");
                    }
                } else {
                    player.sendMessage(ChatColor.RED+"Usage: /email <player>");
                }
            }

            if(command.getName().equalsIgnoreCase("send")) {
                if(args.length < 2) {

                    player.sendMessage("&cUsage: /send <player> <content>");

                    return false;

                } else {
                    try {

                        Player playerSender = (Player) sender;
                        Player target = Bukkit.getPlayer(args[0]);

                        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                        Objects.requireNonNull(playerSender);
                        Objects.requireNonNull(target);

                        Essentials.main.adventure().player(target).sendMessage(Component.text(message)
                                .append(Component.text("\nFrom "+playerSender.getName())
                                        .color(NamedTextColor.GRAY)));

                    } catch (IllegalArgumentException e) {
                        player.sendMessage("&cThis isn't a valid player");
                    }
                }
            }



        }
        return true;
    }
}
