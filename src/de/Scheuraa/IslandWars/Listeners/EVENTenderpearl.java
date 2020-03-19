package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import de.Scheuraa.IslandWars.methods.Var;
public class EVENTenderpearl implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			Player p = e.getPlayer();
			if(p.getItemInHand() != null){
				if(p.getItemInHand().getType().equals(Material.ENDER_PEARL)){
					e.setCancelled(!Var.canThrowEnderpearl);
				}
			}
		}
	}
}
