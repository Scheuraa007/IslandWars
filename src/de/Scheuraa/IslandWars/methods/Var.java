package de.Scheuraa.IslandWars.methods;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EnchantingInventory;

public class Var {
	
	public static final String pr = "§7[§6IslandWars§7]",
			                   noperm = pr + "§cDazu hast du keine Rechte!";
	public static boolean canMove = true,
			              canAttack = false,
			              canBuild = false,
			              canThrowEnderpearl = false,
			              canDrop = false,
						  canInteract = false,
						  cangetDamage = false,
						  isinGame = false;
	
	public static HashMap<Player, Integer> playerKils = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> teamKills = new HashMap<Player, Integer>();
	
	public static ArrayList<Player> isInWater = new ArrayList<>();
	public static ArrayList<EnchantingInventory> inventories;
			         
	public static File cfgFile = new File("plugins//IslandWars//arena.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
			
	public static ArrayList<Player> playing = new ArrayList<>();
	public static ArrayList<Player> spectating = new ArrayList<>();

}
