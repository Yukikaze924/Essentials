package com.yukikaze.essentials;

import com.yukikaze.essentials.commands.EssentialsCommands;
import com.yukikaze.essentials.commands.ImageCommand;
import com.yukikaze.essentials.commands.EmailCommand;
import com.yukikaze.essentials.commands.GUICommand;
import com.yukikaze.essentials.corpses.BodyEvents;
import com.yukikaze.essentials.corpses.BodyManager;
import com.yukikaze.essentials.data.Data;
import com.yukikaze.essentials.data.DataStorage;
import com.yukikaze.essentials.gui.ClickEvent;
import com.yukikaze.essentials.events.EmailEvent;
import com.yukikaze.essentials.events.EssentialsEvents;
import com.yukikaze.essentials.handlers.LoadConfig;
import com.yukikaze.essentials.handlers.VaultHandler;
import com.yukikaze.essentials.items.EssentialsItems;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public final class Essentials extends JavaPlugin {

    public static Essentials main;

    public static YamlConfiguration config;

    public static DataStorage dataStorage;

    private final BodyManager bodyManager = new BodyManager();
    public BodyManager getBodyManager() {
        return bodyManager;
    }

    private BukkitAudiences adventure;
    public @NotNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @SuppressWarnings("all")
    @Override
    public void onEnable() {

        // Store data, object... into variables
        main = this;
        config = LoadConfig.getConfig();
        dataStorage = Data.getDataStorage();

        // Initialization
        EssentialsItems.init();
        this.adventure = BukkitAudiences.create(this);

        // Listener Registrations
        getServer().getPluginManager().registerEvents(new EssentialsEvents(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new EmailEvent(), this);
        getServer().getPluginManager().registerEvents(new BodyEvents(), this);
//        getServer().getPluginManager().registerEvents(new ImageEvent(), this);

        // Command Registrations
        getCommand("treat").setExecutor(new EssentialsCommands());
        getCommand("id").setExecutor(new EssentialsCommands());
        getCommand("me").setExecutor(new GUICommand());
        getCommand("email").setExecutor(new EmailCommand());
        getCommand("send").setExecutor(new EmailCommand());
        getCommand("print").setExecutor(new ImageCommand());

        // server logger
        getLogger().info(ChatColor.GREEN+"Plugin is now Enable");

        // Mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e);
        }
        // SQLite
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver found");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e);
        }
        try {
            dataStorage.initializeDatabase();
            getLogger().info(ChatColor.GREEN+"Database connection successfully established - " + config.getString("general.data_storage").toUpperCase() + " initialized!");
        } catch (SQLException e) {
            getLogger().warning(ChatColor.RED + "Failed to initialize database");
            System.out.println(e);
        }

        // Vault interaction
        boolean economySetup = new VaultHandler().setupEconomy();
        if(economySetup) {
            getLogger().info(ChatColor.GREEN+"Successfully hooked with Vault");
        } else {
            getLogger().warning(ChatColor.RED+"No Economy plugin found!");
            getLogger().warning(ChatColor.RED+"Failed to hooked with Vault");
        }
    }

    @Override
    public void onDisable() {

        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

        try {
            dataStorage.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        getLogger().info(ChatColor.YELLOW+"Plugin is disable!");
    }
}
