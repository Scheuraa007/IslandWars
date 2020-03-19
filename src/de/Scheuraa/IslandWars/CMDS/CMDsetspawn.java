package de.Scheuraa.IslandWars.CMDS;

import java.io.File;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.Sign;

import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.Var;
public class CMDsetspawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("setspawn")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("iw.admin")){
					File file = Var.cfgFile;
					YamlConfiguration cfg = Var.cfg;
					if(args.length ==1){
						if(args[0].equalsIgnoreCase("lobby")){
							Factory.createConfigLocation(p.getLocation(), "Spawn.Lobby", file, cfg);
							p.sendMessage(Var.pr + "§aDer Spawn für die Lobby wurde gesetzt!");
						}else if(args[0].equalsIgnoreCase("spectator")){
							Factory.createConfigLocation(p.getLocation(), "Spawn.Spectator", file, cfg);
							p.sendMessage(Var.pr + "§aDer Spawn für die Spectator wurde gesetzt!");
						}else if(args[0].equalsIgnoreCase("arena")){
							p.sendMessage(Var.pr + "§cBitte benutze /setspawn arena <spawnplatz>");
						}else{
							p.sendMessage(Var.pr + "§cBitte benutze /setspawn <Lobby/Spectator/Arena/Top>");
						}
					}else if(args.length==2){
						if(args[0].equalsIgnoreCase("arena")){
							try{
								int platz = Integer.parseInt(args[1]);
								if(platz>0 &&platz<=TeamHandler.teams){
									Factory.createConfigLocation(p.getLocation(), "Spawn.Arena." + platz, file, cfg);
									p.sendMessage(Var.pr + "§aDer Spawnplatz für Spieler §6" +platz+ "§a wurde gesetzt!");
									
								}else{
									p.sendMessage(Var.pr+ "§cDer angegebene Platz ist zu groß oder zu klein!");
								}
								
							}catch (NumberFormatException e){
								p.sendMessage(Var.pr + "§cBitte gib eine gültige Zahl an!");
							}
							
						}/*else if(args[0].equalsIgnoreCase("top")){
							try{
								int platz = Integer.parseInt(args[1]);
								if(platz>0 &&platz<=10){
									Factory.createConfigLocation(p.getLocation().subtract(0,1,0), "Spawn.topskull." + platz, file, cfg);
									Factory.createConfigLocation(p.getLocation().subtract(0,2,0), "Spawn.topsign." + platz, file, cfg);
									p.sendMessage(Var.pr + "§aDer TopPlatzt §6" +platz+ "§a wurde gesetzt!");
								}
							}catch (NumberFormatException e){
								p.sendMessage(Var.pr + "§cBitte gib eine gültige Zahl an!");
							}								
						}*/else{
							p.sendMessage(Var.pr + "§cBitte benutze /setspawn <Lobby/Spectator/Arena/Top>");
						}
					}else{
							p.sendMessage(Var.pr + "§cBitte benutze /setspawn <Lobby/Spectator/Arena/Top>");
					}
					}else{
					p.sendMessage(Var.noperm);
				}
			}
		}
		
		return false;
	}

}
