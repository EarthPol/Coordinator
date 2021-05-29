package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Coordinate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int scale = Main.instance.getConfig().getInt("scale"), tiles = Main.instance.getConfig().getInt("tiles"), x, z;

            double lat, lng;
            if (args.length > 1) {
                try {
                    lat = Double.parseDouble(args[0]);
                    lng = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Latitude and Longtitude should be a number.");
                    return false;
                }
                x = (int) Math.round(lng * scale / tiles);
                z = (int) (-1 * Math.round(lat * scale / tiles));

                player.sendMessage(ChatColor.YELLOW + "========= In-game Coordinates =========");
                player.sendMessage(ChatColor.YELLOW + "X: " + ChatColor.AQUA + x + ChatColor.YELLOW + " Z: " + ChatColor.AQUA + z);
                if (player.hasPermission("coordinator.teleport") || player.hasPermission("coordinator.admin")) {
                    TextComponent teleportTo = new TextComponent("§b[Teleport to Location]!");
                    teleportTo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleports you to X: " + x + ", Y: 255, Z: " + z).create()));
                    teleportTo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minecraft:tp " + player.getName() + " " + x + " 255 " + z));
                    player.sendMessage(teleportTo);
                }
                if (player.hasPermission("coordinator.admin")) {
                    player.sendMessage(ChatColor.YELLOW + "Scale: " + ChatColor.AQUA + scale + ChatColor.YELLOW + " Dagrees per Tile: " + ChatColor.AQUA + tiles);
                }
                player.sendMessage(ChatColor.YELLOW + "======================================");
            } else {
                player.sendMessage("§eIncorrect Usage: Please specific §blatitude §eand §blongitude §ein §bdecimal degrees");
                player.sendMessage("§eCorrect Usage: [§c/coordinate <latitude> <longitude>§e]");
            }
        } else {
            Bukkit.getLogger().info(Main.prefix + "You need to be a player to execute this command.");
        }

        return false;
    }
}
