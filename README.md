# Coordinator

Coordinator is a lightweight **Paper / Spigot** plugin that converts between **Minecraft X/Z coordinates** and **real-world latitude/longitude** for Earth-style maps.

It is designed for Earth servers (EarthPol / EarthMC-style projections) and uses a configurable scale system to map in-game positions to real-world locations.

---

## Features

- Convert **Minecraft → IRL coordinates**
- Convert **IRL coordinates → Minecraft**
- Optional **clickable Google Maps link**
- Optional **click-to-teleport**
- Fully configurable scale values
- Live config reload
- Minimal overhead, command-only plugin

---

## How It Works

Coordinator uses two configuration values:

- **scale** – blocks per tile
- **tiles** – degrees per tile

These values define how Minecraft coordinates map to latitude and longitude.

---

### Minecraft → IRL ( `/getlocation` )

Given player location `(x, z)`:

```
lat = -1 * ((z / scale) * tiles)
lng = (x / scale) * tiles
```

---

### IRL → Minecraft ( `/coordinate <lat> <lng>` )

Given decimal latitude and longitude:

```
x = round(lng * scale / tiles)
z = -1 * round(lat * scale / tiles)
```

---

## Installation

1. Download or build the plugin JAR
2. Place it in your server’s `plugins/` folder
3. Start the server once to generate the config
4. Edit `plugins/Coordinator/config.yml`
5. Restart or run `/coordinator reload`

---

## Configuration

**`config.yml`**

```
scale: 3072
tiles: 15
mapsLink: true
```

### Options

| Key | Description |
|---|---|
| `scale` | Controls conversion ratio between blocks and tiles |
| `tiles` | Degrees per tile |
| `mapsLink` | Enables clickable Google Maps link |

---

## Commands

### `/getlocation`
**Player-only**

Displays:
- X / Y / Z
- IRL Latitude & Longitude
- Google Maps link (if enabled)

Admins also see scale and tile values.

---

### `/coordinate <latitude> <longitude>`
**Player-only**

- Converts IRL coordinates to Minecraft X/Z
- Accepts decimal degrees
- Shows teleport button if permitted

Teleport command used:

```
/tp <player> <x> 255 <z>
```

---

### `/coordinator`

Administrative root command.

#### `/coordinator reload`
Reloads `config.yml`

#### `/coordinator set scale <number>`
Updates scale value

#### `/coordinator set tiles <number>`
Updates tile value

---

## Permissions

| Permission | Description |
|---|---|
| `coordinator.admin` | Full access, config editing, debug output |
| `coordinator.teleport` | Allows teleport prompt in `/coordinate` |

---

## Behavior Notes

- `/getlocation` and `/coordinate` are **player-only**
- Google Maps links use:
  ```
  https://www.google.com/maps/@<lat>,<lng>,18z
  ```
- Console can reload and set config values
- Commands intentionally lightweight and synchronous

---

## Example Usage

Convert your current position to IRL coordinates:

```
/getlocation
```

Convert Google Maps coordinates to Minecraft:

```
/coordinate 47.6062 -122.3321
```

---

## Support

- Discord: https://discord.gg/epmc
- Author: **0xBit**
- Used on: **EarthPol**


# Licensing 
Coordinator is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported (CC BY-NC-ND 3.0) License (http://creativecommons.org/licenses/by-nc-nd/3.0/)

We don't object to you making your own forks and builds but we do object to people being selfish, which is why we specify No Derivative Works. If you want to modify the code to add some nice feature the least you can do is ask and submit a pull request to allow everyone to benefit from it.
