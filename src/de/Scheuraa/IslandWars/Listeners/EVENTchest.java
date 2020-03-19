package de.Scheuraa.IslandWars.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.IngameState;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.Var;

public class EVENTchest implements Listener{
	  private ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI, 3, (short)4);
	  
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(GameStateHandler.getCurrentState() instanceof IngameState){
				if(e.getClickedBlock().getType() == Material.CHEST){
					if(!Main.getPlugin().getChesthandler().fakeChests.contains(e.getClickedBlock().getLocation())){
					Chest chest = (Chest) e.getClickedBlock().getState();
					chest.getBlockInventory().setContents(Main.getPlugin().getChesthandler().getChest(e.getClickedBlock().getLocation()).getInv().getContents());
					e.getPlayer().openInventory(chest.getInventory());
					Main.getPlugin().getChesthandler().fakeChests.add(e.getClickedBlock().getLocation());
					e.setCancelled(true);
					}
				}
			}
		}
			if(GameStateHandler.getCurrentState() instanceof LobbyState){
				e.setCancelled(true);
			}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractAtEntityEvent e){
		if(!Var.canInteract){
			e.setCancelled(true);
		}
	}
	  @EventHandler
	  public void openInventoryEvent(InventoryOpenEvent e)
	  {
		  if(GameStateHandler.getCurrentState() instanceof IngameState) {
		  if(e.getInventory() instanceof EnchantingInventory) {
			  e.getInventory().setItem(1, this.lapis);
		  }
		  }
	  }
	  @EventHandler
	  public void closeInventoryEvent(InventoryCloseEvent e)
	  {
	    if ((e.getInventory() instanceof EnchantingInventory)) {
	        e.getInventory().setItem(1, new ItemStack(Material.AIR));
	    }
	  }
	  
	  @EventHandler
	  public void inventoryClickEvent(InventoryClickEvent e)
	  {
	    if ((e.getClickedInventory() instanceof EnchantingInventory)) {
	      if ((e.getSlot() == 1)) {
	        e.setCancelled(true);
	      }
	    }
	  }
	  
	  @EventHandler
	  public void enchantItemEvent(EnchantItemEvent e)
	  {
	      e.getInventory().setItem(1, this.lapis);
	  }

}
