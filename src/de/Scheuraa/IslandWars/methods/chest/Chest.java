package de.Scheuraa.IslandWars.methods.chest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Chest {
	
	private static Random random;
	
	private int minItems, maxItems;
	private ArrayList<ChestItem> items = new ArrayList<>();
	private Inventory inv;
	
	public Chest(int minItems, int maxItems, ArrayList<ChestItem>items){
		inv = Bukkit.createInventory(null, 9*3, "§6§lTruhe");
		
				
		this.minItems = minItems;
		this.maxItems = maxItems;
		this.items = items;
		createInventory();
		
	}
	static{
		random = new Random();
	}
	
	private void createInventory(){
		
		int itemAmount = Chest.random.nextInt(this.maxItems - this.minItems +1) + this.minItems;
		
		for(int i = 0; i < itemAmount; i++){
			int slot = getSlot(inv);
			
			ChestItem item = null;
			int ticket = random.nextInt(ChestItem.getCurrentTicket() - 1 + 1) + 1;
			for(ChestItem current : items){
				if(ticket <= current.getMaxTicket() && ticket >= current.getMinTicket()){
					item = current;
				}
			}
			
			
			int amount = Chest.random.nextInt(item.getMax()-item.getMin() +1) + item.getMin();
			ItemStack endItem = item.getItem();
			endItem.setAmount(amount);
			inv.setItem(slot, endItem);
		}
	}
	private int getSlot(Inventory inv){
		int slot = Chest.random.nextInt(inv.getSize());
		
		if(inv.getContents()[slot] == null || inv.getContents()[slot].getType() == Material.AIR){
			return slot;
		}
		return getSlot(inv);
	}
	
	public Inventory getInv() {
		return inv;
	}
	

	public int getMinItems() {
		return minItems;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public ArrayList<ChestItem> getItems() {
		return items;
	}

}
