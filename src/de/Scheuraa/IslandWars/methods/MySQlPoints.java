package de.Scheuraa.IslandWars.methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;


public class MySQlPoints {

		public static boolean isUserExists(UUID uuid){
			try {
				PreparedStatement ps = API_MySQL.getConnection().prepareStatement("SELECT Kills FROM IslandwarsStats WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ResultSet rs = ps.executeQuery();
				return rs.next();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public static void update(UUID uuid, int amount, boolean remove, int Kills, int Deaths, int Wins) {
		
			int deathsx = getDeaths(uuid) + Deaths;	
			int killsx = getKills(uuid) + Kills;
			int winsx = getWins(uuid) + Wins;
			if(remove) {
				CoinsAPI.removeCoins(uuid.toString(), amount);
			}else {
				CoinsAPI.addCoins(uuid.toString(), amount);
			}
			if(isUserExists(uuid)){
				try {
					PreparedStatement ps = API_MySQL.getConnection().prepareStatement("UPDATE IslandwarsStats SET Kills = "+killsx +" WHERE UUID ='" + uuid + "'");
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					PreparedStatement ps = API_MySQL.getConnection().prepareStatement("UPDATE IslandwarsStats SET Deaths = "+deathsx +" WHERE UUID ='" + uuid + "'");
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static int getWins(UUID uuid) {
			try {
				PreparedStatement ps = API_MySQL.getConnection().prepareStatement("SELECT Wins FROM IslandwarsStats WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					return rs.getInt("Wins");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}

		public static void delete(UUID uuid){
			if(isUserExists(uuid)){
				try {
					PreparedStatement ps = API_MySQL.getConnection().prepareStatement("DELETE * FROM IslandwarsStats WHERE UUID = ?");
					ps.setString(1, uuid.toString());
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.err.println("[MySQL] Der Spiler mit der UUID: " + uuid.toString() + " ist nicht in der Datenbank eingetragen!");
			}
			
		} 
		public static Integer getKills(UUID uuid){
			try {
				PreparedStatement ps = API_MySQL.getConnection().prepareStatement("SELECT Kills FROM IslandwarsStats WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					return rs.getInt("Kills");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		public static Integer getDeaths(UUID uuid){
			try {
				PreparedStatement ps = API_MySQL.getConnection().prepareStatement("SELECT Deaths FROM IslandwarsStats WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					return rs.getInt("Deaths");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		public static void createPlayer(UUID uuid) {
			
			if(!(isUserExists(uuid))) {
				try {
					PreparedStatement ps = API_MySQL.getConnection().prepareStatement("INSERT INTO IslandwarsStats (UUID,Kills,Deaths,Wins) VALUES (?, ?, ?, ?)");
					ps.setString(1, uuid.toString());
					ps.setInt(2, 0);
					ps.setInt(3, 0);
					ps.setInt(4, 0);
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public static Integer getKills1(String uuid1) {
			UUID uuid = Bukkit.getPlayer(UUID.fromString(uuid1)).getUniqueId();
			return getKills(uuid);
		}

}
