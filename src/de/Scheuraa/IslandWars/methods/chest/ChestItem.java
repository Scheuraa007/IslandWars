package de.Scheuraa.IslandWars.methods.chest;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ChestItem {
	
	private ItemStack item;
	private int min,max,probability;
	
	private int minTicket, maxTicket;
	private static int currentTicket = 0;
	
	
	
	public ChestItem(ItemStack item, int min, int max, int probability){
		this.item = item;
		this.max = max;
		this.min = min;
		this.probability = probability;
		this.minTicket = currentTicket +1;
		this.maxTicket = minTicket + probability;
		currentTicket = maxTicket;
		
	}
	

	public ItemStack getItem() {
			return item;
	}


	public int getMin() {
		return min;
	}


	public int getMax() {
		return max;
	}


	public int getProbability() {
		return probability;
	}
	
	public int getMinTicket() {
		return minTicket;
	}
	 public int getMaxTicket() {
		return maxTicket;
	}
	 
	 public static int getCurrentTicket() {
		return currentTicket;
	}

}
