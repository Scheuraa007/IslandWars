package de.Scheuraa.IslandWars.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.Scheuraa.IslandWars.CMDS.CMDaddcoins;
import de.Scheuraa.IslandWars.CMDS.CMDsetspawn;
import de.Scheuraa.IslandWars.CMDS.CMDstart;
import de.Scheuraa.IslandWars.CMDS.CMDstats;
import de.Scheuraa.IslandWars.Gamestates.GameState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Kits.KitManager;
import de.Scheuraa.IslandWars.Kits.KitSkillListener;
import de.Scheuraa.IslandWars.Listeners.EVENTChat;
import de.Scheuraa.IslandWars.Listeners.EVENTattack;
import de.Scheuraa.IslandWars.Listeners.EVENTbuild;
import de.Scheuraa.IslandWars.Listeners.EVENTchest;
import de.Scheuraa.IslandWars.Listeners.EVENTdamage;
import de.Scheuraa.IslandWars.Listeners.EVENTdeath;
import de.Scheuraa.IslandWars.Listeners.EVENTdrop;
import de.Scheuraa.IslandWars.Listeners.EVENTenderpearl;
import de.Scheuraa.IslandWars.Listeners.EVENTfood;
import de.Scheuraa.IslandWars.Listeners.EVENTjoin;
import de.Scheuraa.IslandWars.Listeners.EVENTlogin;
import de.Scheuraa.IslandWars.Listeners.EVENTmove;
import de.Scheuraa.IslandWars.Listeners.EVENTquit;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.Utils.ItemsHandler;
import de.Scheuraa.IslandWars.methods.API_MySQL;
import de.Scheuraa.IslandWars.methods.MySQLFile;
import de.Scheuraa.IslandWars.methods.Var;
import de.Scheuraa.IslandWars.methods.chest.ChestHandler;
import net.luckperms.api.LuckPerms;

public class Main extends JavaPlugin{
	private static Main plugin;
	private ChestHandler chesthandler;
	private KitManager kitManager;
	private static LuckPerms api;

	@Override
	public void onEnable() {
		this.plugin = this;
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
		    api = provider.getProvider();
		}
		new GameStateHandler();
		kitManager = new KitManager();
		new ItemsHandler();
		GameStateHandler.setGameState(GameState.LOBBY_STATE);
		init();
		MySQLFile file = new MySQLFile();
		file.setStandart();
		file.readData();
		file.setStandartConfig();
		API_MySQL.connect();
		API_MySQL.update(
				"CREATE TABLE IF NOT EXISTS IslandwarsStats(UUID varchar(100) ,Kills int ,Deaths int,Wins int);");
		Bukkit.getServer().setSpawnRadius(1);
		new TeamHandler();
		System.out.println(Var.pr +  "Das Plugin wurde aktiviert!");
	}
	
	private void init() {
		this.getCommand("start").setExecutor(new CMDstart());
		this.getCommand("setspawn").setExecutor(new CMDsetspawn());
		this.getCommand("stats").setExecutor(new CMDstats());
		this.getCommand("addcoins").setExecutor(new CMDaddcoins());

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EVENTjoin(), this);
		pm.registerEvents(new EVENTquit(), this);
		pm.registerEvents(new EVENTchest(), this);
		pm.registerEvents(new EVENTattack(), this);
		pm.registerEvents(new EVENTmove(), this);
		pm.registerEvents(new EVENTbuild(), this);
		pm.registerEvents(new EVENTdeath(), this);
		pm.registerEvents(new KitManager(), this);
		pm.registerEvents(new KitSkillListener(), this);
		pm.registerEvents(new EVENTenderpearl(), this);
		pm.registerEvents(new EVENTdamage(), this);
		pm.registerEvents(new EVENTfood(), this);
		pm.registerEvents(new EVENTdrop(), this);
		pm.registerEvents(new EVENTlogin(), this);
		pm.registerEvents(new EVENTChat(), this);
	}

	public static LuckPerms getApi() {
		return api;
	}	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
	
	public ChestHandler getChesthandler() {
		return chesthandler;
	}
	
	public void setChesthandler(ChestHandler chesthandler) {
		this.chesthandler = chesthandler;
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	

}
