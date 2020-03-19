package de.Scheuraa.IslandWars.Teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.methods.Var;

public class TeamHandler {
	
	private static HashMap<String, Integer> playerTeams = new HashMap<String, Integer>();
	public static int playersinTeam = LobbyState.cfg.getInt("PlayersinTeam"), teams=LobbyState.cfg.getInt("Teams");
	public static ArrayList<Team> team = new ArrayList<Team>();
	
	public TeamHandler() {
		for(int i=1;i<=teams;i++) {
			Team t = TablistTeam.board.getTeam("T"+i);
			if(t!=null) {
				t.unregister();
			}
			t = TablistTeam.board.registerNewTeam("T" + i);
			t.setAllowFriendlyFire(false);
			t.setPrefix("§aT"+i+"| §f");
			t.setCanSeeFriendlyInvisibles(true);
			t.setNameTagVisibility(NameTagVisibility.ALWAYS);
			team.add(t);
		}
	}
	
	
	public static Integer getPlayersTeams(Player p) {
		String uuid = p.getUniqueId().toString();
		if(playerTeams.containsKey(uuid)) {
			return playerTeams.get(uuid)+1;
		}
		return 0;
	}
	
	public static boolean setPlayersTeam(Player p, int teamid) {
		String uuid = p.getUniqueId().toString();
		if(playerTeams.containsKey(uuid)) {
			int tid = playerTeams.get(uuid);
			team.get(tid).removePlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
			playerTeams.remove(p.getUniqueId().toString());
		}
		Team t = team.get(teamid-1);
		if(t.getSize()==playersinTeam) {
			return false;
		}
		playerTeams.put(p.getUniqueId().toString(), teamid);
		t.addPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
		return true;
	}
	
	
	public static ArrayList<OfflinePlayer> getMates(Player p){
		int tid = getPlayersTeams(p);
		Team t = team.get(tid-1);
		ArrayList<OfflinePlayer> players = (ArrayList<OfflinePlayer>) t.getPlayers();
		
		return players;
	}
	
	public static ArrayList<OfflinePlayer> getPlayers(int tid){
		Team t = team.get(tid);
		ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>(t.getPlayers());
		return players;
	}
	
	public static void setRandomteam(Player p) {
		boolean teamok = false;
		while(!teamok) {
			int teamid =(int) (Math.random()*teams-1)+1; 
			teamok = setPlayersTeam(p, teamid);
		}
	}
	
	public static void removePlayersTeam(Player p) {
		String uuid = p.getUniqueId().toString();
		if(playerTeams.containsKey(uuid)) {
			int tid = playerTeams.get(uuid);
			team.get(tid).removePlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
			playerTeams.remove(p.getUniqueId().toString());
		}
	}

}
