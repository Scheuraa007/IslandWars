package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;

public class EVENTfood implements Listener{
	
	@EventHandler
	public void onFoodLose(FoodLevelChangeEvent e){
		if(GameStateHandler.getCurrentState() instanceof LobbyState){
			e.setCancelled(true);
		}
	}

}
