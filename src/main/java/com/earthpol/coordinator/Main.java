package com.earthpol.coordinator;

import com.earthpol.coordinator.commands.Coordinate;
import com.earthpol.coordinator.commands.Coordinator;
import com.earthpol.coordinator.commands.GetLocation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = "§e[COORDINATOR]:§a ";

    @Override
    public void onEnable() {
        instance = this;
        log.info("§e========= §bCOORDINATOR §e=========");
        log.info("§e= §bVersion: §3" + this.getDescription().getVersion());
        log.info("§e= §bAuthors: §3" + this.getDescription().getAuthors());
        log.info("§e= §bWebsite: §3" + this.getDescription().getWebsite());
        log.info("§e= §bSupport: §3https://discord.gg/DvtZzztAfF");
        log.info("§e=========================");
        log.info("§e= §bRegistering Configuration");

        instance.getConfig().options().copyDefaults();
        instance.saveDefaultConfig();

        log.info("§e= §aRegistered Configuration");
        log.info("§e=========================");
        log.info("§e= §bRegistering Commands");
        Objects.requireNonNull(getCommand("coordinate")).setExecutor(new Coordinate());
        Objects.requireNonNull(getCommand("getlocation")).setExecutor(new GetLocation());
        Objects.requireNonNull(getCommand("coordinator")).setExecutor(new Coordinator());
        log.info("§e= §aRegistered Commands");
        log.info("§e=========================");
        log.info("§e= §aStartup completed.");
        log.info("§e=========================");
    }




    @Override
    public void onDisable() {
        instance.saveConfig();
        log.info("§e========= §bCOORDINATOR §e=========");
        log.info("§e= §bDisabling plugin...");
        log.info("§e= §bThank you for using Coordinator");
        log.info("§e= §bSupport: §3https://discord.gg/DvtZzztAfF");
        log.info("§e=========================");
    }
}
