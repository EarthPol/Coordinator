package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetLocation implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            boolean mapsLink = Main.instance.getConfig().getBoolean("mapsLink");
            Location loc = player.getLocation();
            double scale = Main.instance.getConfig().getInt("scale"), tiles = Main.instance.getConfig().getInt("tiles"), x = loc.getBlockX(), z = loc.getBlockZ(), y = loc.getBlockY();
            double lat, lng;

            lat = -1 * ((z / scale) * tiles);
            lng = (x / scale) * tiles;

            player.sendMessage("§e========= Coordinates =========");
            player.sendMessage("§eX: §b" + x);
            player.sendMessage("§eY: §b" + y);
            player.sendMessage("§eZ: §b" + z);
            player.sendMessage("§eIRL Coords: §1Lat: §b" + lat + "§e, §1Long: §b" + lng);
            if(mapsLink == true){
                TextComponent googleMaps = new TextComponent("§b[Click to View on Google Maps]!");
                googleMaps.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to your location in google maps").create()));
                googleMaps.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com/maps/@" + lat + "," + lng + ",18z"));
                player.sendMessage(googleMaps);
            }
            if(player.hasPermission("coordinator.admin")){
                player.sendMessage(ChatColor.YELLOW + "Scale: " + ChatColor.AQUA + scale + ChatColor.YELLOW + " Dagrees per Tile: " + ChatColor.AQUA + tiles);
            }
            player.sendMessage("§e======================================");


        } else {
            Bukkit.getLogger().info(Main.prefix + "You need to be a player to execute this command.");
        }

        return false;
    }
}
