package com.yukikaze.essentials.corpses;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.yukikaze.essentials.Essentials;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Pose;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class BodyEvents implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();

        for (Body body : Essentials.main.getBodyManager().getBodies()) {
            if (body.getWhoDied() == player.getUniqueId()) {

                Essentials.main.getBodyManager().deleteNPC(body);

            }
        }

        Essentials.main.getBodyManager().getBodies().add(spawnCorpse(player));

        event.getDrops().clear();
    }

    public Body spawnCorpse(Player player) {
        // Init model
        Body body = new Body();
        body.setWhoDied(player.getUniqueId());
        body.setItems(Arrays.stream(player.getInventory().getContents()).filter(Objects::nonNull).toArray(ItemStack[]::new));
        body.setWhenDied(System.currentTimeMillis());

        // Get craftPlayer, serverPlayer, server and level
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();
        MinecraftServer server = serverPlayer.getServer();
        ServerLevel level = serverPlayer.getLevel();
        // Create a new gameProfile for body
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.stripColor(player.getDisplayName()));
        GameProfile playerProfile = craftPlayer.getHandle().getGameProfile();
        // Get property
        Property property = (Property) playerProfile.getProperties().get("textures").toArray()[0];
        // Get skin texture from auth lib
        String signature = property.getSignature();
        String texture = property.getValue();
        // Set up the texture
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
        // Create the body
        ServerPlayer npc = new ServerPlayer(Objects.requireNonNull(server), level, gameProfile);

        Location location = player.getLocation().getBlock().getLocation().clone();
        while(location.getBlock().getType() == Material.AIR) {
            location = location.subtract(0, 1, 0);
        }

        npc.setPos(player.getLocation().getX(), location.getY() + 1, player.getLocation().getZ());
        npc.setPose(Pose.SLEEPING);

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(npc.getBukkitEntity().getLocation(), EntityType.ARMOR_STAND);
        armorStand.setSmall(true);
        armorStand.setInvulnerable(true);
        armorStand.setInvisible(true);
        ArmorStand armorStand2 = (ArmorStand) player.getWorld().spawnEntity(npc.getBukkitEntity().getLocation().subtract(1, 0, 0), EntityType.ARMOR_STAND);
        armorStand2.setSmall(true);
        armorStand2.setInvulnerable(true);
        armorStand2.setInvisible(true);
        ArmorStand armorStand3 = (ArmorStand) player.getWorld().spawnEntity(npc.getBukkitEntity().getLocation().subtract(2, 0, 0), EntityType.ARMOR_STAND);
        armorStand3.setSmall(true);
        armorStand3.setInvulnerable(true);
        armorStand3.setInvisible(true);

        Bukkit.getOnlinePlayers().forEach(p ->{
            ServerGamePacketListenerImpl ps = ((CraftPlayer) p).getHandle().connection;
            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));
            ps.send(new ClientboundSetEntityDataPacket(npc.getId(), npc.getEntityData(), true));
            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));
        });

        body.setNpc(npc);
        body.getArmorStands().add(armorStand);
        body.getArmorStands().add(armorStand2);
        body.getArmorStands().add(armorStand3);

        return body;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent e) {

        if (e.getRightClicked() instanceof ArmorStand as) {
            for (Iterator<Body> iterator = Essentials.main.getBodyManager().getBodies().iterator(); iterator.hasNext(); ) {
                Body body = iterator.next();
                if (body.getArmorStands().contains(as)) {

                    // right-clicked
                    spitItems(e.getPlayer(), body);

                    //play sound
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 1.0f);

                    Essentials.main.getBodyManager().deleteNPC(body);

                    iterator.remove();
                }
            }
        }
    }
    private void spitItems(Player whoClicked, Body body) {

        double y = 0.5;
        for (ItemStack itemStack : body.getItems()) {
            if (itemStack != null) {
                whoClicked.getWorld().dropItem(body.getNpc().getBukkitEntity().getLocation().clone().add(-1, y, 0), itemStack);
                y = y + 0.5;
            }
        }

    }

}