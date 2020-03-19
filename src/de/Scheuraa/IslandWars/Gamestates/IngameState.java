package de.Scheuraa.IslandWars.Gamestates;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.Scheuraa.IslandWars.Countdowns.EndCountdown;
import de.Scheuraa.IslandWars.Countdowns.SpawnCountdown;
import de.Scheuraa.IslandWars.Kits.KitManager;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.Var;

public class IngameState extends GameState {

	private SpawnCountdown spawnCountdown;
	private EndCountdown endCountdown;
	@Override
	public void init() {
		spawnCountdown = new SpawnCountdown();
		Bukkit.createWorld(new WorldCreator("world"));

		for (int i = 0; i < Var.playing.size(); i++) {
			Player p = Var.playing.get(i);
			if(TeamHandler.getPlayersTeams(p)==0) {
				TeamHandler.setRandomteam(p);
			}
			
			p.teleport(Factory.getConfigLocation("Spawn.Arena." + TeamHandler.getPlayersTeams(p), Var.cfg));
			System.out.println(TeamHandler.getPlayersTeams(p));
			p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
			p.getInventory().clear();

			if (KitManager.getPlayerKit(p) != null) {
				for (ItemStack item : KitManager.getPlayerKit(p).getStartItems()) {
					p.getInventory().addItem(item);
				}
				if(KitManager.getPlayerKit(p).getHelm() !=null) {
					p.getInventory().setHelmet(KitManager.getPlayerKit(p).getHelm());
				}
				if(KitManager.getPlayerKit(p).getBrust() !=null) {
					p.getInventory().setChestplate(KitManager.getPlayerKit(p).getBrust());
				}
				if(KitManager.getPlayerKit(p).getHose() !=null) {
					p.getInventory().setLeggings(KitManager.getPlayerKit(p).getHose());
				}
				if(KitManager.getPlayerKit(p).getSchuhe() !=null) {
					p.getInventory().setBoots(KitManager.getPlayerKit(p).getSchuhe());
				}
			}
		}
		spawnCountdown.start();

	}

	@Override
	public void end() {
		Var.canAttack = false;
		Var.canBuild = false;
		Var.canDrop = false;
		Var.canAttack = false;
		Var.canBuild = false;
		Var.canDrop = false;
		Var.cangetDamage = false;
		Var.canInteract = false;
		spawnCountdown.getGraceCountdown().stop();
		Var.playing = new ArrayList<Player>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
			p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
			for(int i=0;i<p.getInventory().getSize();i++) {
				p.getInventory().remove(p.getInventory().getItem(i));
			}
			p.getInventory().setBoots(new ItemStack(Material.AIR));
			p.getInventory().setChestplate(new ItemStack(Material.AIR));
			p.getInventory().setLeggings(new ItemStack(Material.AIR));
			p.getInventory().setHelmet(new ItemStack(Material.AIR));
			p.teleport(Factory.getConfigLocation("Spawn.Lobby", Var.cfg));
			p.setGameMode(GameMode.SURVIVAL);

		}
		
	}

}
