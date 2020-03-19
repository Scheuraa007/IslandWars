package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTjoin implements Listener {
	
	private ItemStack kitChoice(){
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName("§aWähle dein Kit!");
		item.setItemMeta(imeta);
		return item;
	}
	private ItemStack teamChoice() {
		ItemStack item = new ItemStack(Material.RED_BED);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName("§aWähle dein Team!");
		item.setItemMeta(imeta);
		return item;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		MySQlPoints.createPlayer(p.getUniqueId());
		if(!Factory.isSetetup(Var.cfg)) {
				if(p.hasPermission("iw.admin")) {
					p.sendMessage(Var.pr + "§cDer Server wurde noch nicht vollständig eingerichtet es fehlen noch folgende Einstellungen: §e" + Factory.checkFehlend(Var.cfg));
					p.setGameMode(GameMode.CREATIVE);
					return;
				}
				p.setGameMode(GameMode.SURVIVAL);
				p.setHealth(20);
				p.setFoodLevel(20);
				p.setLevel(0);
				return;
			}
		if(GameStateHandler.getCurrentState() instanceof LobbyState){
			LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();
			
			Var.playing.add(p);
			Bukkit.broadcastMessage(Var.pr + "§7Der Spieler §a" + p.getDisplayName()+ " §7ist beigetreten. [" + Var.playing.size() + "/" + LobbyState.MAX_PLAYERS+"]");
			p.teleport(Factory.getConfigLocation("Spawn.Lobby", Var.cfg));
			
			p.getInventory().clear();
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			
			p.setGameMode(GameMode.SURVIVAL);
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setLevel(0);
			p.getInventory().setItem(0, kitChoice());
			
			if(TeamHandler.teams>1) {
				p.getInventory().setItem(1,teamChoice());
			}
			
			if(Var.playing.size() >= LobbyState.MIN_PLAYERS){
				if(ls.getCountdown().isRunning == false){
					ls.getCountdown().stopIdle();
					ls.getCountdown().start();
					
				}
			}
			if(Var.playing.size() < LobbyState.MIN_PLAYERS){
				if(ls.getCountdown().isIdling == false){
					ls.getCountdown().idle();
				}
			}
		}else if(GameStateHandler.getCurrentState() instanceof IngameState){
			Var.spectating.add(p);
			p.setGameMode(GameMode.SPECTATOR);
			p.teleport(Factory.getConfigLocation("Spawn.Spectator", Var.cfg));
			
		}
	}
	
}
