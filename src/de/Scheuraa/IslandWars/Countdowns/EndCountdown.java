package de.Scheuraa.IslandWars.Countdowns;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Var;

public class EndCountdown extends Countdown{
	
	private int seconds = 30;
	private int taskID;

	@Override
	public void start() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				switch(seconds){
				
				case 30: case 15: case 10: case 5: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr+ "§7Der Server wird in §6" +seconds+ "§7 Sekunden neugestartet!");
					break;
				case 1:
					Bukkit.broadcastMessage(Var.pr+ "§7Der Server wird in §6" +seconds+ "§7 Sekunde neugestartet!");
					break;
				case 0:
					stop();
					Bukkit.broadcastMessage(Var.pr+ "§6Der Server wird jetzt neugestartet!");
					for(Player p: Bukkit.getOnlinePlayers()) {
						p.kickPlayer(Var.pr + " \n§cDie Runde ist beendet!");
						
					}
					Bukkit.getServer().shutdown();
					
					break;
				default:
					break;
				}
				seconds--;

				
			}
		}, 0, 20);
	}
		

	@Override
	public void stop() {
		Bukkit.getScheduler().cancelTask(taskID);
	}
	

}
