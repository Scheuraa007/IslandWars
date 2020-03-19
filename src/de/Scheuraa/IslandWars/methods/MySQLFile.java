package de.Scheuraa.IslandWars.methods;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLFile {

	public void setStandart(){
		FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", "3306");
		cfg.addDefault("database", "plugintest");
		cfg.addDefault("username", "localhost");
		cfg.addDefault("password", "1234");
		
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getFile(){
		return new File("plugins/IslandWars", "mysql.yml");
	}
	
	private FileConfiguration getFileConfiguration(){
		return YamlConfiguration.loadConfiguration(getFile());
		
	}
	public void readData(){
		FileConfiguration cfg = getFileConfiguration();
		
		API_MySQL.HOST = cfg.getString("host");
		API_MySQL.PORT= cfg.getString("port");
		API_MySQL.DATABASE = cfg.getString("database");
		API_MySQL.USER= cfg.getString("username");
		API_MySQL.PASSWORD = cfg.getString("password");
		
	}
	
	private File getConfig() {
		return new File("plugins/IslandWars", "config.yml");
	}
	
	public void setStandartConfig(){
		FileConfiguration cfg = getFileConfiguration2();
		cfg.options().copyDefaults(true);
		int Min = 1,
			Max = 8;
		cfg.addDefault("minPlayer", Min);
		cfg.addDefault("PlayersinTeam", 2);
		cfg.addDefault("Teams", 4);
		try {
			cfg.save(getConfig());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileConfiguration getFileConfiguration2() {
		return YamlConfiguration.loadConfiguration(getConfig());
	}
	
}
