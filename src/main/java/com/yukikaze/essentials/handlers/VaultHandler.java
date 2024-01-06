package com.yukikaze.essentials.handlers;

import com.yukikaze.essentials.Essentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHandler {

    public static Economy economy;

    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = Essentials.main.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
