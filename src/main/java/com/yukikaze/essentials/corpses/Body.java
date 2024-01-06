package com.yukikaze.essentials.corpses;

import net.minecraft.server.level.ServerPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Body {

    private UUID whoDied;
    private ServerPlayer npc;
    private ItemStack[] items;
    private List<ArmorStand> armorStands;
    private long time;

    public Body() {
        this.armorStands = new ArrayList<>();
    }

    public Body(UUID whoDied, ServerPlayer npc, ItemStack[] items, List<ArmorStand> armorStands, long time) {
        this.whoDied = whoDied;
        this.npc = npc;
        this.items = items;
        this.armorStands = armorStands;
        this.time = time;
    }

    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    public void setArmorStands(List<ArmorStand> armorStands) {
        this.armorStands = armorStands;
    }

    public ServerPlayer getNpc() {
        return npc;
    }

    public void setNpc(ServerPlayer npc) {
        this.npc = npc;
    }

    public UUID getWhoDied() {
        return whoDied;
    }

    public void setWhoDied(UUID whoDied) {
        this.whoDied = whoDied;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void setItems(ItemStack[] items) {
        this.items = items;
    }

    public long getWhenDied() {
        return time;
    }

    public void setWhenDied(long time) {
        this.time = time;
    }

}
