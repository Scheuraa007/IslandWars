package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.Scheuraa.IslandWars.methods.Var;

import org.bukkit.event.Listener;


public class EVENTdamage implements Listener{
	
	@EventHandler
	public void onDmg(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			if(!Var.cangetDamage){
				e.setCancelled(true);

				
				return;
			}
		}
	}

}
