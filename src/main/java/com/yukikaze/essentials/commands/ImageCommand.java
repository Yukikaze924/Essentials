package com.yukikaze.essentials.commands;

import com.yukikaze.essentials.utils.ImageRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public class ImageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            if(args.length == 1) {

                Player player = (Player) sender;

                ItemStack item = new ItemStack(Material.FILLED_MAP, 1);

                MapMeta meta = (MapMeta) item.getItemMeta();

                MapView view = Bukkit.createMap(player.getWorld());

                ImageRenderer renderer = new ImageRenderer(args[0]);

                view.getRenderers().clear();

                view.addRenderer(renderer);

                meta.setMapView(view);
                item.setItemMeta(meta);

                player.getInventory().addItem(item);
            }

        }

        return true;
    }

}
