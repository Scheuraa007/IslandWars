package de.Scheuraa.IslandWars.CMDS;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.methods.Var;
public class CMDstart implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("start")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("iw.start")){
					if(args.length == 0){
						
						if(GameStateHandler.getCurrentState() instanceof LobbyState){
							LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();
							if(Var.playing.size() >= LobbyState.MIN_PLAYERS){
								
							if(ls.getCountdown().getSeconds() >5){
								ls.getCountdown().setSeconds(5);
								p.sendMessage(Var.pr + "§aDer Spielstart wurde beschleunigt!");
							}else{
								p.sendMessage(Var.pr + "§cDas Spiel startet bereits!");
							}
							}else{
								p.sendMessage(Var.pr + "§cDu kannst das Spiel nur starten wenn genügend Spieler online sind!");
							}
						}else{
							p.sendMessage(Var.pr+ "§cDu kannst den Countdown nur in der Lobby überspringen!");
						}
						
					}else{
						p.sendMessage(Var.pr + "§cBitte benutze /start!");
					}
				}else{
					p.sendMessage(Var.noperm);
				}
			}
		}
		
		return false;
	}

}
