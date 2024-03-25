package com.earthpol.coordinator.commands;

import com.earthpol.coordinator.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Coordinator implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
          if(args[0].equalsIgnoreCase("reload")){
              // /COORDINATOR RELOAD
              if(sender instanceof Player){
                  Player player = (Player) sender;
                  if (player.hasPermission("coordinator.admin")){
                      Main.instance.reloadConfig();
                      player.sendMessage(Main.prefix + "Configuration reloaded.");
                  } else {
                      player.sendMessage(Main.prefix + "You do not have permission to do that.");
                  }
              } else {
                  Main.instance.reloadConfig();
                  Main.instance.log.info(Main.prefix + "Configuration reloaded.");
              }
          } else if (args[0].equalsIgnoreCase("set")){
              // /COORDINATOR SET
              if(args.length > 1){
                  if (args[1].equalsIgnoreCase("scale")) {
                      // /COORDINATOR SET SCALE
                      if(args.length > 2) {
                          // /COORDINATOR SET SCALE <NUMBER>
                          if(sender instanceof Player){
                              Player player = (Player) sender;
                              if (player.hasPermission("coordinator.admin")){
                                  int prevScale = Main.instance.getConfig().getInt("scale");
                                  int scale = Integer.parseInt(args[2]);
                                  Main.instance.getConfig().set("scale", scale);
                                  Main.instance.saveConfig();
                                  Main.instance.reloadConfig();
                                  player.sendMessage(Main.prefix + "Scale changed from §b" + prevScale + "§a to §b" + scale);
                              } else {
                                  player.sendMessage(Main.prefix + "You do not have permission to do that.");
                              }
                          } else {
                              int prevScale = Main.instance.getConfig().getInt("scale");
                              int scale = Integer.getInteger(args[2]);
                              Main.instance.getConfig().set("scale", scale);
                              Main.instance.saveConfig();
                              Main.instance.reloadConfig();
                              Main.log.info(Main.prefix + "Scale changed from " + prevScale + " to " + scale);
                          }
                      } else {
                          if(sender instanceof Player){
                              Player player = (Player) sender;
                              player.sendMessage(Main.prefix + "Correct Usage: §b/coordinator set scale <number>");
                              player.sendMessage(Main.prefix + "Example Usage: §b/coordinator set scale 3072");
                          } else {
                              Main.log.info(Main.prefix + "Correct Usage: /coordinator set scale <number>");
                              Main.log.info(Main.prefix + "Example Usage: /coordinator set scale 3072");
                          }
                      }
                  } else if (args[1].equalsIgnoreCase("tiles")) {
                      // /COORDINATOR SET TILES
                      if(args.length > 2) {
                          // /COORDINATOR SET TILES <NUMBER>
                          if(sender instanceof Player){
                              Player player = (Player) sender;

                              if (player.hasPermission("coordinator.admin")){
                                  int prevTile = Main.instance.getConfig().getInt("tiles");
                                  int tiles = Integer.parseInt(args[2]);
                                  Main.instance.getConfig().set("tiles", tiles);
                                  Main.instance.saveConfig();
                                  Main.instance.reloadConfig();
                                  player.sendMessage(Main.prefix + "Tiles changed from §b" + prevTile + "§a to §b" + tiles);
                              } else {
                                  player.sendMessage(Main.prefix + "You do not have permission to do that.");
                              }
                          } else {
                              int prevTile = Main.instance.getConfig().getInt("tiles");
                              int tiles = Integer.getInteger(args[2]);
                              Main.instance.getConfig().set("tiles", tiles);
                              Main.instance.saveConfig();
                              Main.instance.reloadConfig();
                              Main.log.info(Main.prefix + "Tiles changed from " + prevTile + " to " + tiles);
                          }
                      } else {
                          if(sender instanceof Player){
                              Player player = (Player) sender;
                              player.sendMessage(Main.prefix + "Correct Usage: §b/coordinator set tiles <number>");
                              player.sendMessage(Main.prefix + "Example Usage: §b/coordinator set tiles 15");
                          } else {
                              Main.log.info(Main.prefix + "Correct Usage: /coordinator set tiles <number>");
                              Main.log.info(Main.prefix + "Example Usage: /coordinator set tiles 15");
                          }
                      }
                  } else {
                      if(sender instanceof Player){
                          Player player = (Player) sender;
                          player.sendMessage("§e========= §bCOORDINATOR §e=========");
                          player.sendMessage("§b/coordinator set §e| Sets the values in the config.yml");
                          player.sendMessage("§b/coordinator set scale <number> §e| Sets scale");
                          player.sendMessage("§b/coordinator set tiles <number> §e| Sets tiles");
                          player.sendMessage("§e================================");

                      } else {
                          Main.log.info("========= COORDINATOR =========");
                          Main.log.info("/coordinator set | Sets the values in the config.yml");
                          Main.log.info("/coordinator set scale <number> | Sets scale");
                          Main.log.info("/coordinator set tiles <number> | Sets tiles");
                          Main.log.info("================================");
                      }
                  }
              } else {
                  if(sender instanceof Player){
                      Player player = (Player) sender;
                      player.sendMessage("§e========= §bCOORDINATOR §e=========");
                      player.sendMessage("§b/coordinator set §e| Sets the values in the config.yml");
                      player.sendMessage("§b/coordinator set scale <number> §e| Sets scale");
                      player.sendMessage("§b/coordinator set tiles <number> §e| Sets tiles");
                      player.sendMessage("§e================================");

                  } else {
                      Main.log.info("========= COORDINATOR =========");
                      Main.log.info("/coordinator set | Sets the values in the config.yml");
                      Main.log.info("/coordinator set scale <number> | Sets scale");
                      Main.log.info("/coordinator set tiles <number> | Sets tiles");
                      Main.log.info("================================");
                  }

              }
          }
        } else {
            if(sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage("§e========= §bCOORDINATOR §e=========");
                player.sendMessage("§ePlugin by §b0xBit §e| IP: play.earthpol.com");
                player.sendMessage("§b/coordinator reload §e| Reloads the configuration file.");
                player.sendMessage("§b/coordinator set §e| Sets the values in the config.yml");
                player.sendMessage("§e================================");

            } else {
                Main.log.info("========= COORDINATOR =========");
                Main.log.info("/coordinator reload | Reloads the configuration file.");
                Main.log.info("/coordinator set | Sets the values in the config.yml");
                Main.log.info("================================");
            }
        }
        return false;
    }
}
