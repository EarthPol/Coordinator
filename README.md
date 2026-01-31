# Coordinator

Coordinator is a lightweight Paper/Spigot plugin that converts between **Minecraft X/Z** coordinates and **real-world decimal latitude/longitude** for Earth-style maps (Mercator-projection style), using two config values: `scale` and `tiles`.

It provides:
- **`/getlocation`** → Minecraft ➜ IRL (Lat/Long), optionally with a clickable Google Maps link
- **`/coordinate <lat> <lng>`** → IRL (Lat/Long) ➜ Minecraft X/Z, optionally with a clickable teleport prompt
- **`/coordinator ...`** → admin utilities (reload config, set scale/tiles)

This README matches how the plugin currently behaves and aligns with the EarthMC documentation for the same formulas and command behavior.

---

## How it works

Coordinator uses a simple math conversion based on your map’s *scale* and *degrees-per-tile* (your `tiles` value).

### Minecraft ➜ IRL (used by `/getlocation`)

Given player location `(x, z)`:

'''
lat = -1 * ((z / scale) * tiles)
lng = (x / scale) * tiles
'''

### IRL ➜ Minecraft (used by `/coordinate <lat> <lng>`)

Given decimal degrees `(lat, lng)`:

'''
x = round(lng * scale / tiles)
z = -1 * round(lat * scale / tiles)
'''

---

## Installation

1. Build or download the plugin jar.
2. Drop the jar into your server’s `plugins/` folder.
3. Start the server once to generate the default config.
4. Configure `scale`, `tiles`, and `mapsLink` in `plugins/Coordinator/config.yml`.
5. Restart the server or run `/coordinator reload`.

---

## Configuration (`config.yml`)

Default example:

'''
scale: 3072
tiles: 15
mapsLink: true
'''

### Options

- **`scale`**  
  Controls the conversion ratio between Minecraft blocks and map tiles.

- **`tiles`**  
  Degrees-per-tile factor used in coordinate math.

- **`mapsLink`**  
  When enabled, `/getlocation` shows a clickable Google Maps link.

---

## Commands

### `/getlocation`
**Player-only**

Displays:
- Current X / Y / Z
- Converted IRL latitude and longitude
- Clickable Google Maps link (if enabled)

If the player has `coordinator.admin`, scale and tile values are also shown.

---

### `/coordinate <latitude> <longitude>`
**Player-only**

- Converts decimal latitude/longitude to Minecraft X/Z
- Accepts decimal degrees
- Outputs calculated X and Z
- If the player has `coordinator.teleport` or `coordinator.admin`, a clickable teleport prompt appears

Teleport command used internally:

'''
/tp <player> <x> 255 <z>
'''

Admins additionally see scale and tile values.

---

### `/coordinator`

Administrative root command.

#### `/coordinator reload`
Reloads `config.yml`.

- Requires `coordinator.admin` (players)
- Console always allowed

#### `/coordinator set scale <number>`
Sets the scale value, saves, and reloads config.

#### `/coordinator set tiles <number>`
Sets the tiles value, saves, and reloads config.

---

## Permissions

- **`coordinator.admin`**
  - Reload config
  - Set scale and tiles
  - View debug scale/tile info

- **`coordinator.teleport`**
  - Enables teleport option in `/coordinate`

---

## Behavior notes

- `/getlocation` and `/coordinate` are **player-only**
- Google Maps links use:
  '''
  https://www.google.com/maps/@<lat>,<lng>,18z
  '''
- All commands currently return `false`, which may trigger Bukkit usage messages depending on `plugin.yml`
- Console `set` commands use JVM property lookup (`Integer.getInteger`) rather than parsing; player commands use proper parsing

---

## Example usage

### Convert your in-game location to IRL coordinates
'''
/getlocation
'''

### Convert Google Maps coordinates to Minecraft
'''
/coordinate 47.6062 -122.3321
'''

---

## Support

- Discord: https://discord.gg/epmc

# Licensing 
Coordinator is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported (CC BY-NC-ND 3.0) License (http://creativecommons.org/licenses/by-nc-nd/3.0/)

We don't object to you making your own forks and builds but we do object to people being selfish, which is why we specify No Derivative Works. If you want to modify the code to add some nice feature the least you can do is ask and submit a pull request to allow everyone to benefit from it.
