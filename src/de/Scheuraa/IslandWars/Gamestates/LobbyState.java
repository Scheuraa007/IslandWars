package de.Scheuraa.IslandWars.Gamestates;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Scheuraa.IslandWars.Countdowns.LobbyCountdown;
import de.Scheuraa.IslandWars.methods.Var;

public class LobbyState extends GameState{
	public static File cfgFile = new File("plugins/IslandWars/config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
	public static final int MIN_PLAYERS = cfg.getInt("minPlayer"),
			                MAX_PLAYERS = cfg.getInt("PlayersinTeam")*cfg.getInt("Teams");
	
	private LobbyCountdown countdown;

	@Override
	public void init() {
		countdown = new LobbyCountdown();
		
	}

	@Override
	public void end() {
		Bukkit.broadcastMessage(Var.pr + "§aAlle Spieler werden nun auf ihre Inseln teleportiert!");
	}

	public LobbyCountdown getCountdown() {
		return countdown;
	}

}
