package com.yukikaze.essentials.events;

import com.yukikaze.essentials.Essentials;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.models.PlayerTable;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.Date;

public class EssentialsEvents implements Listener {

    private final DataStorage dataStorage = Essentials.dataStorage;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        try {

            PlayerTable playerTable = dataStorage.getPlayerTableFromDatabase(player);

            player.sendMessage(ChatColor.LIGHT_PURPLE+"[Essentials]: ************************************************");
            player.sendMessage(ChatColor.LIGHT_PURPLE+"[Essentials]: *" + ChatColor.GRAY+"You are in " + ChatColor.GOLD+ChatColor.BOLD+"Blaine County");
            player.sendMessage(ChatColor.LIGHT_PURPLE+"[Essentials]: *" + ChatColor.GRAY+"Your Identity: " + ChatColor.YELLOW+playerTable.getPlayerIdentity());
            player.sendMessage(ChatColor.LIGHT_PURPLE+"[Essentials]: *" + ChatColor.GRAY+"Type" + ChatColor.DARK_GRAY+" /me " + ChatColor.GRAY+"in chat to learn more");
            player.sendMessage(ChatColor.LIGHT_PURPLE+"[Essentials]: ************************************************");

        } catch (SQLException e) {
            System.out.println("[Essentials]: Unable to update player stats");
        }
    }

//    private static int seconds = 20;
//
//    @EventHandler
//    @SuppressWarnings("all")
//    public void onPlayerBitten(EntityDamageByEntityEvent event) {
//
//        if(event.getEntity() instanceof Player && event.getDamager().getType() == EntityType.ZOMBIE) {
//
//            Player player = (Player) event.getEntity();
//
//            player.sendMessage(ChatColor.GRAY + "You were bitten by a " + ChatColor.RED + "ZOMBIE");
//
//            // Infection
//            Random random = new Random();
//            if (random.nextDouble() < 0.02) {
//
//                Bukkit.getScheduler().runTaskTimer(Essentials.main, () -> {
//                    if (seconds > 0) {
//
//                        if(seconds == 9) {
//                            player.sendMessage(ChatColor.GRAY+"You start to have "+ChatColor.YELLOW+"trouble breathing");
//                            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 1));
//                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
//                        }
//
//                        seconds--;
//
//                    } else {
//                        // Turned to a zombie
//                        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
//                        zombie.setCustomName(player.getName());
//                        zombie.setCustomNameVisible(true);
//
//                        player.setHealth(0);
//
//                        Bukkit.getScheduler().cancelTasks(Essentials.main);
//                    }
//                }, 20L, 20L); // 以每秒20 tick的速度运行任务
//            }
//        }
//    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        try {
            PlayerTable playerTable = dataStorage.getPlayerTableFromDatabase(player);
            playerTable.setLastInGame(new Date());
            dataStorage.updatePlayerTable(playerTable);
        } catch (SQLException e) {
            System.out.println("[Essentials]: Unable to update player stats");
        }

    }
}