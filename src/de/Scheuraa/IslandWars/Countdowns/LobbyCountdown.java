package de.Scheuraa.IslandWars.Countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Gamestates.GameState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Var;

public class LobbyCountdown extends Countdown{
	
	private int seconds = 60;
	private int taskID, idleID;
	public boolean isIdling = false, isRunning = false;

	@Override
	public void start() {
		isRunning = true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			
			@Override
			public void run(){
				if(seconds >= 0) {
					for(Player p : Var.playing) {
						p.setLevel(seconds);
					}
				}
				switch(seconds){
				case 60: case 30: case 15: case 10: case 5: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr + "§7Das Spiel startet in §6" +seconds+"§7 Sekunden!");
				    for(Player a : Var.playing){
				    	a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);			
				    }
				    break;
				case 1:
					Bukkit.broadcastMessage(Var.pr + "§7Das Spiel startet in §6" +seconds+"§7 Sekunde!");
				    for(Player a : Var.playing){
				    	a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);			
				    }
				    break;
				case 0:
					GameStateHandler.setGameState(GameState.INGAME_STATE);
					break;
				default:
					break;
				}			
				seconds--;
			}
			
		}, 0, 20*1);
	}
	
	public void idle(){
		isIdling = true;
		idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			
			@Override
			public void run(){
				int missing =  LobbyState.MIN_PLAYERS - Var.playing.size();
				Bukkit.broadcastMessage(Var.pr +"§7Zum Spielstart fehlen noch §c" +missing + "§7 Spieler.");
			}
			
		}, 0, 20*15);
	}
	
	
	public void stopIdle(){
		if(isIdling){
			isIdling = false;
			Bukkit.getScheduler().cancelTask(idleID);
		}
	}
	
	public void stopCountdown(){
		if(isRunning){
			isRunning = false;
			Bukkit.getScheduler().cancelTask(taskID);
			seconds =60;
			for(Player a : Var.playing){
				a.setLevel(seconds);			
			}
			
		}
	}
	
	public int getSeconds(){
		return seconds;
	}
	
	public void setSeconds(int seconds){
		this.seconds = seconds;
	}
	
	@Override
	public void stop() {
		isIdling = false;
		isRunning = false;
		seconds = 60;
		Bukkit.getScheduler().cancelTask(taskID);
		Bukkit.getScheduler().cancelTask(idleID);
	}

}
