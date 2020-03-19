package de.Scheuraa.IslandWars.Countdowns;

import org.bukkit.Bukkit;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Var;

public class GraceCountdown extends Countdown{
	
	private int taskID;
	private int seconds = 30;

	@Override
	public void start() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				switch(seconds){
				case 30: case 15: case 10: case 5: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr+ "§7Die Spieler können sich in §6" +seconds+ "§7 Sekunden angreifen!");
					break;
				case 1:
					Bukkit.broadcastMessage(Var.pr+ "§7Die Spieler können sich in §6" +seconds+ "§7 Sekunde angreifen!");
					break;
				case 0:
					stop();
					Bukkit.broadcastMessage(Var.pr+ "§6Die Spieler können sich jetzt angreifen!");
					Var.canAttack = true;
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
