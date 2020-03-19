package de.Scheuraa.IslandWars.Gamestates;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;
import net.minecraft.server.v1_14_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_14_R1.PacketPlayInClientCommand.EnumClientCommand;
public abstract class GameState {
	
	public static final int LOBBY_STATE = 0,
			                INGAME_STATE = 1,
			                END_STATE=2;
	
	public abstract void init();
	public abstract void end();
	
	
	public static boolean checkWinning() {
		if(GameStateHandler.getCurrentState() instanceof IngameState) {
			Player p = Var.playing.get(0);
			for(Player pe : Var.playing) {
				if(TeamHandler.getPlayersTeams(p)!= TeamHandler.getPlayersTeams(pe)) {
					return false;
				}
			}
			Var.isinGame = false;
			Bukkit.broadcastMessage(Var.pr+"§aDas Team von §e" + Var.playing.get(0).getDisplayName() + "§a hat die Islandwars-Runde gewonnen!");
			for(Player pe: Var.playing) {
				MySQlPoints.update(pe.getUniqueId(), 300, false, 0, 0,1);
				pe.sendMessage(Var.pr + "§aDu hast für das Gewinnen einer Islandwars Runde 300 Coins bekommen !" );
			}
			
			
			GameStateHandler.setGameState(END_STATE);
			return true;
		}
		return false;
	}
	
	
	
	public static void Respawn(final Player player, int Time) {
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(),new Runnable() {
			
			@Override
			public void run() {
				((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				
			}
		}, Time);
	}

}
