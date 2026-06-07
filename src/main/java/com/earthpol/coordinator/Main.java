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

    @Override
    public void onEnable() {
        instance = this;
        log.info("========= COORDINATOR =========");
        log.info("= Version: " + this.getDescription().getVersion());
        log.info("= Authors: " + this.getDescription().getAuthors());
        log.info("= Website: " + this.getDescription().getWebsite());
        log.info("= Support: https://discord.gg/epmc");
        log.info("=========================");
        log.info("= Registering Configuration");

        Config.initialize();

        log.info("= Registered Configuration");
        log.info("=========================");
        log.info("= Registering Commands");
        Objects.requireNonNull(getCommand("coordinate")).setExecutor(new Coordinate());
        Objects.requireNonNull(getCommand("getlocation")).setExecutor(new GetLocation());
        Objects.requireNonNull(getCommand("coordinator")).setExecutor(new Coordinator());
        log.info("= Registered Commands");
        log.info("=========================");
        log.info("= Startup completed.");
        log.info("=========================");
    }

    @Override
    public void onDisable() {}

}
