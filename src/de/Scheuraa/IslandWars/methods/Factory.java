package de.Scheuraa.IslandWars.methods;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Teams.TeamHandler;

public class Factory {

	public static void createConfigLocation(Location loc, String path, File file, YamlConfiguration cfg) {
		cfg.set(path + ".World", loc.getWorld().getName());
		cfg.set(path + ".X", loc.getX());
		cfg.set(path + ".Y", loc.getY());
		cfg.set(path + ".Z", loc.getZ());
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Location getConfigLocation(String path, YamlConfiguration cfg) {
		World w = Bukkit.getWorld(cfg.getString(path + ".World"));
		double x = cfg.getDouble(path + ".X");
		double y = cfg.getDouble(path + ".Y");
		double z = cfg.getDouble(path + ".Z");

		return new Location(w, x, y, z);
	}

	public static Location getConfigLocation2(String path, YamlConfiguration cfg) {
		World w = Bukkit.getWorld(cfg.getString(path + ".World"));
		double x = cfg.getDouble(path + ".X");
		double y = cfg.getDouble(path + ".Y");
		double z = cfg.getDouble(path + ".Z");

		return new Location(w, x, y, z);
	}

	public static boolean isSetetup(YamlConfiguration cfg) {
		if (!cfg.contains("Spawn.Lobby")) {
			return false;
		}
		if (!cfg.contains("Spawn.Spectator")) {
			return false;
		}
		for (int i = 1; i <= TeamHandler.teams; i++) {
			if (!cfg.contains("Spawn.Arena." + i)) {
				return false;
			}
		}
		return true;
	}

	public static String checkFehlend(YamlConfiguration cfg) {
		String fehlend = "";
		if (!cfg.contains("Spawn.Lobby")) {
			fehlend = fehlend + "\n-Lobbyspawn ";
		}
		if (!cfg.contains("Spawn.Spectator")) {
			fehlend = fehlend + "\n-Spectatorspawn ";
		}
		for (int i = 1; i <= TeamHandler.teams; i++) {
			if (!cfg.contains("Spawn.Arena." + i)) {
				fehlend = fehlend + "\n-Arenaspawn " + i;
			}
		}
		return fehlend;
	}

}
