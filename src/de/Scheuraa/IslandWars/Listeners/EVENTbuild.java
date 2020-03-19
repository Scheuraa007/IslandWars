package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.Var;
import de.Scheuraa.IslandWars.methods.chest.ChestItem;

public class EVENTbuild implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!Factory.isSetetup(Var.cfg)) {
			if (e.getPlayer().hasPermission("iw.admin")) {
				e.setCancelled(false);
				return;
			}
		}
		if (!Var.canBuild) {
			e.getBlock().getLocation().getBlock().setType(e.getBlock().getType());
			return;
		}
		if (e.getBlock().getType().equals(Material.CHEST)) {
			if (!Main.getPlugin().getChesthandler().fakeChests.contains(e.getBlock().getLocation())) {
				for (ChestItem chestItems : Main.getPlugin().getChesthandler().getChest(e.getBlock().getLocation())
						.getItems()) {
					ItemStack item = chestItems.getItem();
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
				}
				Main.getPlugin().getChesthandler().fakeChests.add(e.getBlock().getLocation());
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!Factory.isSetetup(Var.cfg)) {
			if (e.getPlayer().hasPermission("iw.admin")) {
				e.setCancelled(false);
				return;
			}
		}
		if (!Var.canBuild) {
			e.setCancelled(true);
			return;
		}

		if (e.getBlock().getType().equals(Material.CHEST)) {
			Main.getPlugin().getChesthandler().fakeChests.add(e.getBlock().getLocation());
		}
	}

	@EventHandler
	public void onEntityBreak(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (!Var.canBuild) {
				e.setCancelled(true);
			}
			Player p = (Player) e.getDamager();
			if (!Factory.isSetetup(Var.cfg) && p.hasPermission("iw.admin")) {
				e.setCancelled(false);
				return;
			}
		}
	}
}
