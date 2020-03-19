package de.Scheuraa.IslandWars.Gamestates;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Countdowns.EndCountdown;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.Var;

public class EndState extends GameState{
	
	private EndCountdown endCountdown;

	@Override
	public void init() {
		endCountdown = new EndCountdown();
		endCountdown.start();
		Var.canAttack = false;
		Var.canBuild = false;
		Var.canDrop = false;
		Var.canAttack = false;
		Var.canBuild = false;
		Var.canDrop = false;
		Var.cangetDamage = false;
		Var.canInteract = false;
		for(Player p : Bukkit.getOnlinePlayers()) {
			TeamHandler.removePlayersTeam(p);
			p.getInventory().clear();
			p.teleport(Factory.getConfigLocation("Spawn.Lobby", Var.cfg));
			p.setGameMode(GameMode.ADVENTURE);
		}
		
		
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	

}
