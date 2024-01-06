package com.yukikaze.essentials.utils;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageRenderer extends MapRenderer {

//    private BufferedImage image;
//
    private boolean renderingFinished = false;

//
//    public ImageRenderer() {
//        renderingFinished = false;
//    }
//
//    public void load(String url) {
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new URL(url));
//            image = MapPalette.resizeImage(image);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        this.image = image;
//    }

    private final String url;

    public ImageRenderer(String url) {
        this.url = url;
    }

    @Override
    public void render(@NotNull MapView view, @NotNull MapCanvas canvas, @NotNull Player player) {

        if(renderingFinished) {
            return;
        }

        try {
            URL imageUrl = new URL(this.url);
            BufferedImage image = ImageIO.read(imageUrl);
            canvas.drawImage(0, 0, MapPalette.resizeImage(image));
            view.setTrackingPosition(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        renderingFinished = true;

    }
}
