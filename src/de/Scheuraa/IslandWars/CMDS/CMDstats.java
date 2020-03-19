package de.Scheuraa.IslandWars.CMDS;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;

public class CMDstats implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("stats")) {
			if (args.length == 1) {
				String player = args[0];
				if (sender instanceof Player) {
					int kills = MySQlPoints.getKills(Bukkit.getOfflinePlayer(player).getUniqueId());
					int deaths = MySQlPoints.getDeaths(Bukkit.getOfflinePlayer(player).getUniqueId());
					double kd;
					if (kills != 0) {
						if (deaths != 0) {
							kd = (double) kills / (double) deaths;
							
						}else {
							kd = kills;
						}
					} else {
						kd = 0;
					}
					
					double rund = ((int)kd*100)/100;
					int wins = MySQlPoints.getWins(Bukkit.getOfflinePlayer(player).getUniqueId());
					int coins = CoinsAPI.getCoins(Bukkit.getOfflinePlayer(player).getUniqueId().toString());
					sender.sendMessage("§7----=Stats von §e" + player + "§7=----");
					sender.sendMessage(
							"   §7Kills: §e" + MySQlPoints.getKills(Bukkit.getOfflinePlayer(player).getUniqueId()));
					sender.sendMessage(
							"   §7Deaths: §e" + MySQlPoints.getDeaths(Bukkit.getOfflinePlayer(player).getUniqueId()));
					String coins2 = los(coins);
					sender.sendMessage("   §7Coins: §e" + coins2);
					sender.sendMessage("   §7Wins: §e" + wins);
					sender.sendMessage("   §7KD: §e" + rund);
				}

			} else if (args.length == 0) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					int kills = MySQlPoints.getKills(((Player) sender).getUniqueId());
					int deaths = MySQlPoints.getDeaths(((Player) sender).getUniqueId());
					double kd;
					if (kills != 0) {
						if (deaths != 0) {
							kd = (double) kills / (double) deaths;
							
						}else {
							kd = kills;
						}
					} else {
						kd = 0;
					}
					double rund = ((int)kd*100)/100;
					int coins = CoinsAPI.getCoins(p.getUniqueId().toString());
					int wins = MySQlPoints.getWins(p.getUniqueId());
					sender.sendMessage("§7----=Deine Stats=----");
					sender.sendMessage("   §7Kills: §e"
							+ MySQlPoints.getKills(Bukkit.getPlayer(((Player) sender).getUniqueId()).getUniqueId()));
					sender.sendMessage("   §7Deaths: §e"
							+ MySQlPoints.getDeaths(Bukkit.getPlayer(((Player) sender).getUniqueId()).getUniqueId()));
					String coins2 = los(coins);
					sender.sendMessage("   §7Coins: §e" + coins2);
					sender.sendMessage("   §7Wins: §e" + wins);
					sender.sendMessage("   §7KD: §e" + rund);

					return false;
				}

			} else if (args.length >= 2) {
				sender.sendMessage(Var.pr + "§cNutze /stats oder /stats <name>");
				return false;
			}
			return false;
		}
		return false;
	}

	public String los(int zahl) {

		DecimalFormat nf = new DecimalFormat();
		String ausgabe = nf.format(zahl);
		return ausgabe;
	}
}
