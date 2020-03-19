package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.Scheuraa.IslandWars.Gamestates.GameState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTdeath implements Listener{
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(GameStateHandler.getCurrentState() instanceof IngameState) {
		Player victim = e.getEntity();
		e.setDeathMessage(null);
		if(victim.getKiller() != null) {
			if(victim.getKiller() instanceof Player){	
				
				Player killer = victim.getKiller();	
				MySQlPoints.update(killer.getUniqueId(), 100, false, 1, 0,0);
				killer.sendMessage(Var.pr + "§aDu hast für das Töten von §c" +victim.getDisplayName() + " §a100 Coins bekommen!" );
				if(Var.playerKils.containsKey(killer)) {
					Var.playerKils.replace(killer, Var.playerKils.get(killer)+1);
				}else {
					Var.playerKils.put(killer, 1);
				}
				for(OfflinePlayer op: TeamHandler.getMates(killer)) {
					Player p = Bukkit.getPlayer(op.getUniqueId());
					if(Var.teamKills.containsKey(p)) {
						Var.teamKills.replace(p, Var.teamKills.get(p)+1);
					}else {
						Var.teamKills.put(p, 1);
					}
				}
				if(Var.teamKills.containsKey(killer)) {
					Var.teamKills.replace(killer, Var.teamKills.get(killer)+1);
				}else {
					Var.teamKills.put(killer, 1);
				}
				
				MySQlPoints.update(victim.getUniqueId(), 0, false, 0, 1,0);
				Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §c" + victim.getDisplayName() + "§7 wurde von §a" + killer.getDisplayName() + " §7getötet");
				e.setKeepInventory(false);
				e.setDeathMessage(null);
				GameState.Respawn(victim, 1);	
				victim.setGameMode(GameMode.SPECTATOR);
				if(Var.playing.size() ==2) {
					Var.isinGame = false;
					
				}
				Var.playing.remove(victim);	
				Var.spectating.add(victim);
				
				if(Var.isInWater.contains(victim)) {
					Var.isInWater.remove(victim);
				}
				
				victim.sendMessage(Var.pr + "§7Du bist gestorben und bist nun ein Zuschauer!");
				if(GameState.checkWinning()) {
					victim.teleport(Factory.getConfigLocation("Spawn.Lobby",Var.cfg));
					return;
				}
				victim.teleport(Factory.getConfigLocation("Spawn.Spectator", Var.cfg));
				return;
			}
		}
		if(Var.playing.size() ==2) {
			Var.isinGame = false;

		}
		Var.playing.remove(victim);
		Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §c" + victim.getDisplayName() + " §7ist gestorben!");
		MySQlPoints.update(victim.getUniqueId(), 0, false, 0, 1,0);
		Var.spectating.add(victim);	
		e.setKeepInventory(true);
		e.setDeathMessage(null);;
		GameState.Respawn(victim, 1);
		if(Var.isInWater.contains(victim)) {
			Var.isInWater.remove(victim);
		}
		if(GameState.checkWinning()) {
			victim.teleport(Factory.getConfigLocation("Spawn.Lobby",Var.cfg));
			return;
		}
		victim.teleport(Factory.getConfigLocation("Spawn.Spectator", Var.cfg));
		return;

	}
	
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if(Var.isinGame) {
			e.setRespawnLocation(Factory.getConfigLocation("Spawn.Spectator", Var.cfg));
			return;
		}
		e.setRespawnLocation(Factory.getConfigLocation("Spawn.Lobby", Var.cfg));
	}
	

}
