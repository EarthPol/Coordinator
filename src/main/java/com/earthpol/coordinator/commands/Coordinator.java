package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Config;
import com.earthpol.coordinator.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Coordinator implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args
    ) {
        // /coordinator reload
        Runnable reload = () -> {
            Main.instance.reloadConfig();
            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    Config.getConfig().getString("message.reloaded", "")
            ));
        };

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player player) {
				if (player.hasPermission("coordinator.admin")) reload.run();
                else player.sendMessage(MiniMessage.miniMessage().deserialize(
                        Config.getConfig().getString("message.error.nopermission", "")
                ));
            } else reload.run();
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args
    ) {
        return List.of("reload");
    }
}
