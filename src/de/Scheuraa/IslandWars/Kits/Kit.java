package de.Scheuraa.IslandWars.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class Kit {
	private String name;
	private String description;
	private Material symbol;
	private List<ItemStack> startItems;
	private ItemStack helm=null,brust=null,hose=null,schuhe=null;
	private int preis;
	public Kit(String name, String description,int preis, Material symbol, List<ItemStack> startItems, ItemStack helm, ItemStack brust, ItemStack hose, ItemStack schuhe) {
		super();
		this.preis = preis;
		this.name = name;
		this.description = description;
		this.symbol = symbol;
		this.startItems = startItems;
		this.helm = helm ;
		this.brust = brust;
		this.hose = hose ;
		this.schuhe = schuhe;
	}
	public Kit(String name, String description,int preis, Material symbol, List<ItemStack> startItems) {
		super();
		this.preis = preis;
		this.name = name;
		this.description = description;
		this.symbol = symbol;
		this.startItems = startItems;
	}
	
	
	public ItemStack getMenuItem(){
		ItemStack item = new ItemStack(symbol);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName(this.name);
		
		String[] split = description.split("//");
		ArrayList<String> lore = new ArrayList<>();
		for(String lorePart: split){
			lore.add(lorePart);
		}
		imeta.setLore(lore);
		item.setItemMeta(imeta);
		return item;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Material getSymbol() {
		return symbol;
	}
	public void setSymbol(Material symbol) {
		this.symbol = symbol;
	}
	public List<ItemStack> getStartItems() {
		return startItems;
	}
	public void setStartItems(List<ItemStack> startItems) {
		this.startItems = startItems;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}
	public Integer getPreis() {
		return preis;
	}
	public ItemStack getHelm() {
		return helm;
	}
	public void setHelm(ItemStack helm) {
		this.helm = helm;
	}
	public ItemStack getBrust() {
		return brust;
	}
	public void setBrust(ItemStack brust) {
		this.brust = brust;
	}
	public ItemStack getHose() {
		return hose;
	}
	public void setHose(ItemStack hose) {
		this.hose = hose;
	}
	public ItemStack getSchuhe() {
		return schuhe;
	}
	public void setSchuhe(ItemStack schuhe) {
		this.schuhe = schuhe;
	}
	
	
	
	
	

}
