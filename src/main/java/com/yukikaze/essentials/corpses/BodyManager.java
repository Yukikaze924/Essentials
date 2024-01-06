package com.yukikaze.essentials.corpses;

import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;

public class BodyManager {

    private final List<Body> bodies;

    public BodyManager() {
        this.bodies = new ArrayList<>();
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void deleteNPC(Body body) {

        //remove the npc for every player on the server
        Bukkit.getOnlinePlayers().forEach(player -> {
            ServerGamePacketListenerImpl ps = ((CraftPlayer) player).getHandle().connection;
            //remove from tab list
            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, body.getNpc()));
            //remove from sight
            ps.send(new ClientboundRemoveEntitiesPacket(body.getNpc().getId()));
        });

        //remove the armor stands
        for (ArmorStand armorStand : body.getArmorStands()) {
            armorStand.remove();
        }

    }
}
