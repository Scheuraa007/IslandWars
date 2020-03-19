package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTChat implements Listener {

	@EventHandler
	public void onChat(PlayerChatEvent e) {
		String message = e.getMessage();
		Player p = e.getPlayer();
		e.setCancelled(true);
		if (Var.spectating.contains(p)) {
			for (Player pe : Var.spectating) {
				pe.sendMessage("§8" + p.getDisplayName() + "§7: §f" + message);
			}
		}

		if (TeamHandler.teams > 1) {
			if (message.startsWith("@all ")) {
				for (Player pe : Bukkit.getOnlinePlayers()) {
					message = message.replaceFirst("@all ", "");
					pe.sendMessage("§8[§7@all§8]§a" + p.getDisplayName() + "§7: §f" + message);
				}
				return;
			}
			if (message.startsWith("@all")) {
				for (Player pe : Bukkit.getOnlinePlayers()) {
					message = message.replaceFirst("@all", "");
					pe.sendMessage("§8[§7@all§8]§a" + p.getDisplayName() + "§7: §f" + message);
				}
				return;
			}
			if (message.startsWith("@a ")) {
				for (Player pe : Bukkit.getOnlinePlayers()) {
					message = message.replaceFirst("@a ", "");
					pe.sendMessage("§8[§7@all§8]§a" + p.getDisplayName() + "§7: §f" + message);
				}
				return;
			}
			if (message.startsWith("@a")) {
				for (Player pe : Bukkit.getOnlinePlayers()) {
					message = message.replaceFirst("@a", "");
					pe.sendMessage("§8[§7@all§8]§a" + p.getDisplayName() + "§7: §f" + message);
				}
				return;
			}
			for (Player pe : Var.playing) {
				if (TeamHandler.getMates(p).contains(Bukkit.getOfflinePlayer(pe.getUniqueId()))) {
					pe.sendMessage("§a" + p.getDisplayName() + "§7: §f" + message);
				}
				if(pe==p) {
					pe.sendMessage("§a" + p.getDisplayName() + "§7: §f" + message);
				}
			}
			return;
		}
		if (Var.playing.contains(p)) {
			for (Player pe : Bukkit.getOnlinePlayers()) {
				pe.sendMessage("§a" + p.getDisplayName() + "§7: §f" + message);
			}
		}

	}

}
