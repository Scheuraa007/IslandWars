package de.Scheuraa.IslandWars.Utils;

import org.bukkit.entity.Player;

import de.Scheuraa.IslandWars.Kits.KitManager;
import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Var;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholder extends PlaceholderExpansion{
	private Main plugin;
	public Placeholder(Main plugin) {
		this.plugin = plugin;
	}
	
    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }
	
	@Override
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier() {
		return "islandwars";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}
	
	 @Override
	    public String onPlaceholderRequest(Player player, String identifier){

	        if(player == null){
	            return "";
	        }

	        // %islandwars_kit%
	        if(identifier.equals("kit")){
	        	if(KitManager.getPlayerKit(player)!=null) {
	        		return KitManager.getPlayerKit(player).getName();
	        	}else {
	        		return "-";
	        	}
	        }

	        // %islandwars_spieler%
	        if(identifier.equals("spieler")){
	            return Var.playing.toString();
	        }
	        // %islandwars_kills%
	        if(identifier.equals("kills")){
	            return Var.playerKils.get(player).toString();
	        }
	        // %islandwars_teamkills%
	        if(identifier.equals("teamkills")){
	            return Var.teamKills.get(player).toString();
	        }
	        
	 
	        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%) 
	        // was provided
	        return null;
	    }

}
