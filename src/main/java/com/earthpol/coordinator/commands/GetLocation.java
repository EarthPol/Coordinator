package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetLocation implements CommandExecutor {
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

		Location loc = player.getLocation();

        double scale = Config.getConfig().getInt("scale");
        double tiles = Config.getConfig().getInt("tiles");

        double x = loc.getBlockX();
        double z = loc.getBlockZ();
        double y = loc.getBlockY();

        double lat = -1 * ((z / scale) * tiles);
        double lng = (x / scale) * tiles;


        String message = Config.getConfig().getString("message.getlocation.default", "");
        if (player.hasPermission("coordinator.admin")) message += Config.getConfig().getString("message.getlocation.admin", "");

        message = message.replace("{x}", String.valueOf(x))
                .replace("{y}", String.valueOf(y))
                .replace("{z}", String.valueOf(z))
                .replace("{lat}", String.valueOf(lat))
                .replace("{long}", String.valueOf(lng));

        for (String msg : message.split("\n"))
            player.sendMessage(MiniMessage.miniMessage().deserialize(msg));


        return false;
    }
}
