package de.Scheuraa.IslandWars.CMDS;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import de.Scheuraa.IslandWars.Kits.KitManager;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;

public class CMDaddcoins implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		if(command.getName().equals("addcoins")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("iw.admin")) {
					if(args.length == 2) {
						Player target = (Player) Bukkit.getOfflinePlayer(args[0]);
						int amount = Integer.parseInt(args[1]);
						String zahl = KitManager.los(amount);

						if(MySQlPoints.isUserExists(target.getUniqueId())) {
							if((CoinsAPI.getCoins(p.getUniqueId().toString()) + amount) >= 1000000000) {
								p.sendMessage(Var.pr + "§cDie Anzahl der Münzen war zu groß");
								return false;
							}
							MySQlPoints.update(target.getUniqueId(), amount, false, 0, 0,0);						
							p.sendMessage(Var.pr + "§aDu hast §a" + target.getDisplayName() + " §e" + zahl + "§e Münzen §ahinzugefügt");
						}else {						
							if(amount >= 1000000000) {
							p.sendMessage(Var.pr + "§cDie Anzahl der Münzen war zu groß");
							return false;
						    }
							MySQlPoints.createPlayer(target.getUniqueId());
							MySQlPoints.update(target.getUniqueId(), amount, false, 0, 0,0);
							p.sendMessage(Var.pr + "§aDu hast §a" + target.getDisplayName() + " §e" + zahl + "§e Münzen §ahinzugefügt");
						}						
					}
				}
				}
		}
		
		
		return false;
		
	}

	
}
