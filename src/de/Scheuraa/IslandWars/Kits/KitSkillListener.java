package de.Scheuraa.IslandWars.Kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;

public class KitSkillListener implements Listener{
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(KitManager.getPlayerKit(p) != null){
				if(KitManager.getPlayerKit(p) instanceof KitEnderman){
					if(GameStateHandler.getCurrentState() instanceof IngameState){
						if(e.getCause().equals(DamageCause.FALL)){
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
