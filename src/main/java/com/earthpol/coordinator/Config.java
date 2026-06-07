package com.earthpol.coordinator;

import org.bukkit.configuration.file.FileConfiguration;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class Config {

	public static @NonNull FileConfiguration getConfig() {
		return Main.instance.getConfig();
	}

	public static void initialize() {
		getConfig().addDefault(
				"tiles",
				15
		);
		getConfig().setComments(
				"tiles",
				List.of(
						"Degrees per tile"
				)
		);

		getConfig().addDefault(
				"scale",
				5120
		);
		getConfig().setComments(
				"scale",
				List.of(
						"Blocks per tile"
				)
		);


		getConfig().addDefault(
				"message.coordinate.default",

				"<dark_gray><st>--=--</st> <white>In-game Coordinates</white> <dark_gray><st>--=--</st>\n"
				+"<gray>X: <yellow>{x} <gray>Z: <yellow>{z}\n"
				+"<i><gray><click:copy_to_clipboard:'{x} 255 {z}'>Click to copy coordinates</click>"
		);
		getConfig().setComments(
				"message.coordinate.default",
				List.of(
						"Message to sent in response to /coordinate.",
						"",
						"Placeholders:",
						"{x} - converted x coordinate",
						"{z} - converted z coordinate"
				)
		);

		getConfig().addDefault(
				"message.coordinate.admin",

				"<dark_gray> •</dark_gray> <i><gray><click:suggest_command:'/tp {x} 255 {z}'>Click to teleport</click>"
		);
		getConfig().setComments(
				"message.coordinate.admin",
				List.of(
						"Additional lines to append to coordinate command response if the sender is an admin."
				)
		);


		getConfig().addDefault(
				"message.getlocation.default",

				"<dark_gray><st>--=--</st> <white>Real World Coordinates</white> <dark_gray><st>--=--</st>\n"
						+"<gray>X: <yellow>{x} <gray>Y: <yellow>{y} <gray>Z: <yellow>{z}\n"
						+"<gray>Lat: <yellow>{lat} <gray>Long: <yellow>{long}\n"
						+"<i><gray><click:copy_to_clipboard:'{lat} {long}'>Click to copy coordinates</click>"
						+"<dark_gray> •</dark_gray> <i><gray><click:open_url:'https://www.google.com/maps/@{lat},{long},18z'>Click to open Google Maps</click>"
		);
		getConfig().setComments(
				"message.getlocation.default",
				List.of(
						"Message to sent in response to /getlocation.",
						"",
						"Placeholders:",
						"{lat} - converted latitude",
						"{long} - converted longitude",
						"{x} - player x coordinate",
						"{y} - player y coordinate",
						"{z} - player z coordinate"
				)
		);


		getConfig().addDefault(
				"message.reloaded",
				"<yellow>Coordinator configuration reloaded"
		);


		getConfig().addDefault(
				"message.error.mustbeplayer",
				"<red>You must be a player to run this"
		);
		getConfig().addDefault(
				"message.error.nopermission",
				"<red>You don't have permission to do this"
		);
		getConfig().addDefault(
				"message.error.latlongnumber",
				"<red>Latitude and longitude should be a number"
		);
		getConfig().addDefault(
				"message.error.incorrectusage",
				"<red>Incorrect usage! /coordinate <latitude> <longitude>"
		);


		getConfig().options().setHeader(
						List.of(
								"Coordinator Configuration",
								"https://earthmc.org/docs/plugins/coordinator/",
								"",
								"Configure tiles and scale based on the following table:",
								"Ratio		Scale		Tiles",
								"1:108		15360		15",
								"1:217		7680		15",
								"1:326		5120		15",
								"1:543		3072		15"
						)
				).copyDefaults(true)
				.parseComments(true);

		Main.instance.saveConfig();
		Main.instance.reloadConfig();
	}

}
