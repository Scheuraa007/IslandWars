package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.Scheuraa.IslandWars.Gamestates.GameState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTlogin implements Listener{
	
	@EventHandler
	public void onHandleKicks(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(GameStateHandler.getCurrentState() instanceof IngameState){
		}
		if(Var.playing.size() >= LobbyState.MAX_PLAYERS){
			if(p.hasPermission("iw.premium")){
				if(GameStateHandler.getCurrentState() instanceof LobbyState){
					for(int i = 0; i< Var.playing.size(); i++) {
						Player kick =  Var.playing.get(i);
						if(!kick.hasPermission("iw.premium")) {
							kick.kickPlayer(Var.pr + "§cDu wurdest von einem Premium oder höher gekickt!");
							e.allow();
							return;
						}
					}
				}
			}else{
			e.disallow(Result.KICK_FULL, Var.pr + "§cDie Runde ist bereits voll!");
			}
		}
	}

}
