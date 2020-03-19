package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.Scheuraa.IslandWars.methods.Var;

public class EVENTattack implements Listener{
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			if(e.getEntity() instanceof Player){
				if(!Var.canAttack){
					e.setCancelled(true);
				}
			}
		}
	}
	

}
