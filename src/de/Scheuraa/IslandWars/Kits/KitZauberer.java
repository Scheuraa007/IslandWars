package de.Scheuraa.IslandWars.Kits;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KitZauberer extends Kit{

	public KitZauberer(String name, String description, int preis, Material symbol, List<ItemStack> startItems,
			ItemStack helm, ItemStack brust, ItemStack hose, ItemStack schuhe) {
		super(name, description, preis, symbol, startItems, helm, brust, hose, schuhe);
	}

}
