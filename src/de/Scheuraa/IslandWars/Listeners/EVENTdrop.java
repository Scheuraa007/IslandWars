package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTdrop implements Listener{
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		if(!Var.canDrop){
			e.setCancelled(true);
		}
	}

}
