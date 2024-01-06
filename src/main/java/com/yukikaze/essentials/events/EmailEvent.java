package com.yukikaze.essentials.events;

import com.yukikaze.essentials.Essentials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmailEvent implements Listener {

    @SuppressWarnings("all")
    private Map<Player, String> commandMap = new HashMap<>();
    @SuppressWarnings("all")
    private Map<Player, String> argMap = new HashMap<>();

    @EventHandler
    public void onPlayerSendingEmailCommand(PlayerCommandPreprocessEvent event) {

        String[] commandAndArg = event.getMessage().substring(1).split(" ");
        String command = commandAndArg[0];

        if(command.equalsIgnoreCase("email")) {

            Player player = event.getPlayer();

            if(commandAndArg.length==2) {

                String arg = commandAndArg[1];

                commandMap.put(player, command);
                argMap.put(player, arg);

            } else {
                player.sendMessage("&cInvalid Usage");
            }
        }
    }

    @EventHandler
    public void onPlayerSentEmailInTextEditor(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if(commandMap.containsKey(player)) {

            String input = event.getMessage();
            String arg = argMap.get(player);

            Player receiver = Bukkit.getPlayer(arg);
            Objects.requireNonNull(receiver);

            final TextComponent message = Component
            .text("You have received a new email")
            .color(TextColor.color(255, 215, 0))

            .append(Component.text("\nClick to open")
            .color(TextColor.color(255, 255, 0)))
            .clickEvent(ClickEvent.runCommand("/send " + receiver.getName() + " " + input));

            try {

                Essentials.main.adventure().player(receiver).sendMessage(message);

                player.sendMessage(ChatColor.GREEN+"Your message has been sent!");

            } catch (Exception e) {
                player.sendMessage(ChatColor.RED+"Something went wrong! \nDue to " + e);
            }


            event.setCancelled(true);

            commandMap.remove(player);
            argMap.remove(player);
        }
    }

}
