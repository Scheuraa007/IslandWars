package de.Scheuraa.IslandWars.Countdowns;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Var;

public class SpawnCountdown extends Countdown{

	private int taskID;
	private int seconds = 10;
	private GraceCountdown graceCountdown;
	
	public SpawnCountdown(){
		graceCountdown = new GraceCountdown();
	}
	
	@Override
	public void start() {
		Var.canAttack = false;
		Var.canMove = false;
		Var.canBuild = false;
		Var.isinGame= true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				
				switch(seconds){
				case 10: case 5: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr + "§7Die Friedensphase beginnt in §6" + seconds+ "§7 Sekunden.");
					break;
				case 1: 
					Bukkit.broadcastMessage(Var.pr + "§7Die Friedensphase beginnt in §6" + seconds+ "§7 Sekunde.");
					break;
				case 0:
					stop();
					Bukkit.broadcastMessage(Var.pr+ "§6Die Friedensphase hat begonnen!");
					Var.canMove = true;
					Var.canBuild = true;
					Var.canThrowEnderpearl = true;
					Var.canInteract = true;
					Var.cangetDamage = true;
					Var.canDrop = true;
					graceCountdown.start();
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
	
	public GraceCountdown getGraceCountdown() {
		return graceCountdown;
	}

}
