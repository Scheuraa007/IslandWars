package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTmove implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (!Var.canMove) {
			Location from = e.getFrom();
			Location to = e.getTo();
			if(from.getX()==to.getX()&&from.getY()==to.getY()&&from.getZ()==to.getZ()) {
				return;
			}
			e.getPlayer().teleport(e.getFrom());

		}
		Player p = e.getPlayer();
		if (!Factory.isSetetup(Var.cfg)) {
			if (p.hasPermission("iw.admin")) {
				e.setCancelled(false);
				return;
			}
		}
		if (GameStateHandler.getCurrentState() instanceof IngameState) {
			Material m = p.getLocation().getBlock().getType();
			if (m == Material.WATER) {
				Var.isInWater.add(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5555, 3));
			} else if (m != Material.WATER) {
				if (Var.isInWater.contains(p)) {
					p.removePotionEffect(PotionEffectType.WITHER);
					Var.isInWater.remove(p);
				}
			}
		}

	}

}
