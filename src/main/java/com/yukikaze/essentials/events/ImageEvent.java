package com.yukikaze.essentials.events;

import com.yukikaze.essentials.utils.ImageRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.Objects;

public class ImageEvent implements Listener {

    @EventHandler
    public void onCraftingImage(PrepareItemCraftEvent event) {

        ItemStack[] matrix = event.getInventory().getMatrix();
        for (ItemStack item : matrix) {
            if(item!=null && item.getType() == Material.PAPER) {

                String paperName = Objects.requireNonNull(item.getItemMeta()).getDisplayName();

                MapView view = Bukkit.createMap(Objects.requireNonNull(Bukkit.getWorld("lobby")));
                view.getRenderers().clear();

                ImageRenderer renderer = new ImageRenderer(paperName);
                view.addRenderer(renderer);

                ItemStack map = new ItemStack(Material.FILLED_MAP);
                MapMeta meta = (MapMeta) map.getItemMeta();
                Objects.requireNonNull(meta).setDisplayName(paperName);
                meta.setMapView(view);
                map.setItemMeta(meta);

                event.getInventory().setResult(map);
                break;

            }
        }
    }

}
