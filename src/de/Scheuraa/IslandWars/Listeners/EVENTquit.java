package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import de.Scheuraa.IslandWars.Gamestates.EndState;
import de.Scheuraa.IslandWars.Gamestates.GameState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTquit implements Listener{
	
	@EventHandler
	public void onquit(PlayerQuitEvent e){
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		if(GameStateHandler.getCurrentState() instanceof LobbyState){
			LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();
			Var.playing.remove(p);
			if(TeamHandler.getPlayersTeams(p)!=0) {
				TeamHandler.removePlayersTeam(p);
			}
			Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §c" + p.getDisplayName()+ " §7hat das Spiel verlassen. [" + Var.playing.size() + "/" + LobbyState.MAX_PLAYERS+"]");
			if(Var.playing.size() < LobbyState.MIN_PLAYERS){
				if(ls.getCountdown().isRunning){
					ls.getCountdown().stopCountdown();
					ls.getCountdown().idle();
				}
			}
		}else if(GameStateHandler.getCurrentState() instanceof IngameState){
			IngameState ls = (IngameState) GameStateHandler.getCurrentState();
			if(Var.isInWater.contains(p)) {
				Var.isInWater.remove(p);
				p.removePotionEffect(PotionEffectType.WITHER);
			}
			Var.playing.remove(p);
			Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §c" + p.getDisplayName()+ " §7hat das Spiel verlassen.");
			GameState.checkWinning();
			
			
			
		}
		if(GameStateHandler.getCurrentState() instanceof EndState) {
			Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §c" + p.getDisplayName()+ " §7hat das Spiel verlassen.");
		}
	}

}
