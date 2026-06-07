package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Config;
import com.earthpol.coordinator.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    Config.getConfig().getString("message.error.mustbeplayer", "")
            ));
            return true;
        }

		if (args.length < 2) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    Config.getConfig().getString("message.error.incorrectusage", "")
            ));
            return true;
        }


        int scale = Config.getConfig().getInt("scale");
        int tiles = Config.getConfig().getInt("tiles");

        int x, z;
        double lat, lng;


        try {
            String latInput = args[0].replaceAll("[^\\d.-]", "");
            String lngInput = args[1].replaceAll("[^\\d.-]", "");

            lat = Double.parseDouble(latInput);
            lng = Double.parseDouble(lngInput);
        } catch (NumberFormatException e) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(
                    Config.getConfig().getString("message.error.latlongnumber", "")
            ));
            return false;
        }

        x = (int) Math.round(lng * scale / tiles);
        z = (int) (-1 * Math.round(lat * scale / tiles));

        String message = Main.instance.getConfig().getString("message.coordinate.default", "");
        if (player.hasPermission("coordinator.admin")) message += Config.getConfig().getString("message.coordinate.admin", "");

        message = message.replace("{x}", String.valueOf(x))
                .replace("{z}", String.valueOf(z));

        for (String msg : message.split("\n"))
            player.sendMessage(MiniMessage.miniMessage().deserialize(msg));


        return false;
    }
}
