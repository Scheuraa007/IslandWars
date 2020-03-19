package de.Scheuraa.IslandWars.methods.chest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;

public class ChestHandler {
	
	
	public List<Location> fakeChests;
	private Map<Location, Chest> chests;
	private ArrayList<ChestItem> items;
	private int minItems,maxItems;
	
	public ChestHandler(int minItems, int maxItems, ArrayList<ChestItem> items){
		fakeChests = new ArrayList<>();
		this.chests = new HashMap<>();
		this.minItems = minItems;
		this.maxItems = maxItems;
		this.items = items;
	}
	
	
	public Chest getChest(Location loc){
		if(chests.containsKey(loc)){
			return chests.get(loc);
		}
		return createNewChest(loc);
	}
	
	private Chest createNewChest(Location loc){
		Chest chest = new Chest(minItems, maxItems, items);
		this.chests.put(loc, chest);
		return chest;
	}

	public Map<Location, Chest> getChests() {
		return chests;
	}

	public List<ChestItem> getItems() {
		return items;
	}

	public int getMinItems() {
		return minItems;
	}

	public int getMaxItems() {
		return maxItems;
	}
	
	

}
