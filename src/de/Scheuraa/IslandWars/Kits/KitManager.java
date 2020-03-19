package de.Scheuraa.IslandWars.Kits;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.Team;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import de.Scheuraa.IslandWars.Gamestates.EndState;
import de.Scheuraa.IslandWars.Gamestates.GameStateHandler;
import de.Scheuraa.IslandWars.Gamestates.LobbyState;
import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.Teams.TeamHandler;
import de.Scheuraa.IslandWars.methods.Factory;
import de.Scheuraa.IslandWars.methods.MySQlPoints;
import de.Scheuraa.IslandWars.methods.Var;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PermissionNode;
import net.luckperms.api.query.QueryOptions;

public class KitManager implements Listener {

	private ArrayList<Kit> kits;
	private static HashMap<Player, Kit> kitConnection;

	public KitManager() {
		kits = new ArrayList<>();
		kitConnection = new HashMap<>();

		List<ItemStack> startBarbarianItems = new ArrayList<>();
		startBarbarianItems.add(new ItemStack(Material.STONE_SWORD));
		ItemStack helm = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack brust = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack hose = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack schuhe = new ItemStack(Material.CHAINMAIL_BOOTS);
		kits.add(new KitBarbarian("§bBarbar", "§7Startet mit voller Kettenrüstung//§7und einem Steinschwert!", 9000,
				Material.CHAINMAIL_CHESTPLATE, startBarbarianItems, helm, brust, hose, schuhe));

		List<ItemStack> startEndermanItems = new ArrayList<>();
		startEndermanItems.add(new ItemStack(Material.ENDER_PEARL));
		kits.add(new KitEnderman("§bEnderman", "§7Startet mit einer Enderperle.//§7Erhält außerdem keinen Fallschaden.",
				30000, Material.ENDER_PEARL, startEndermanItems));

		List<ItemStack> startZauberer = new ArrayList<>();
		startZauberer.add(new ItemStack(Material.EXPERIENCE_BOTTLE, 64));
		startZauberer.add(new ItemStack(Material.ENCHANTING_TABLE, 1));
		ItemStack brustzau = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta metazau = (LeatherArmorMeta) brustzau.getItemMeta();
		metazau.setColor(Color.BLACK);
		brustzau.setItemMeta(metazau);
		brustzau.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		brustzau.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

		kits.add(new KitZauberer("§bZauberer", "§7Hat Magische Dinge zum Verzaubern.", 10000, Material.ENCHANTING_TABLE,
				startZauberer, new ItemStack(Material.AIR), brustzau, new ItemStack(Material.AIR),
				new ItemStack(Material.AIR)));

		List<ItemStack> startAssasine = new ArrayList<>();
		ItemStack schwert = new ItemStack(Material.DIAMOND_SWORD);
		schwert.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		startAssasine.add(schwert);
		ItemStack brustass = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta metaass = (LeatherArmorMeta) brustass.getItemMeta();
		metaass.setColor(Color.BLACK);
		brustass.setItemMeta(metaass);

		ItemStack helmass = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta metahelmass = (LeatherArmorMeta) helmass.getItemMeta();
		metahelmass.setColor(Color.BLACK);
		helmass.setItemMeta(metahelmass);

		ItemStack hoseass = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta metahoseass = (LeatherArmorMeta) hoseass.getItemMeta();
		metahoseass.setColor(Color.BLACK);
		hoseass.setItemMeta(metahoseass);

		ItemStack schiheass = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta metashuass = (LeatherArmorMeta) schiheass.getItemMeta();
		metashuass.setColor(Color.BLACK);
		schiheass.setItemMeta(metashuass);

		kits.add(new KitAssasine("§bAssasine", "§7Gutes Schwert aber schlechte Rüstung.", 30000, Material.DIAMOND_SWORD,
				startAssasine, helmass, brustass, hoseass, schiheass));
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (!Factory.isSetetup(Var.cfg)) {
				if (p.hasPermission("iw.admin")) {
					e.setCancelled(false);
					return;
				}
			}
			if (!Var.canInteract) {
				e.setCancelled(true);
			}
			if (e.getView().getTitle().equalsIgnoreCase("§6§lWähle dein Kit!")) {
				if (e.getCurrentItem() == null) {
					return;
				}
				if (e.getCurrentItem().getType() != Material.AIR || e.getCurrentItem() != null) {
					for (Kit kit : kits) {
						String name = kit.getName();
						if (e.getCurrentItem().equals(kit.getMenuItem())) {
							openKitBuy(p, name, kit.getMenuItem(), kit.getPreis());
						}

					}
				}
			}
			if (e.getView().getTitle().equalsIgnoreCase("§6§lWähle dein Team!")) {
				if (e.getCurrentItem() == null) {
					return;
				}
				if (e.getCurrentItem().getType() == Material.RED_WOOL) {
					p.sendMessage(Var.pr + "§cDu kannst dem Team nicht beitreten da es bereits voll ist.");
					p.closeInventory();
					return;
				}
				if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
					TeamHandler.setPlayersTeam(p, e.getSlot() + 1);
					p.sendMessage(Var.pr + "§aDu bist nun in Team §e" + (e.getSlot()+1));
					p.closeInventory();
					return;
				}

			}
			for (Kit kit : kits) {
				if (e.getView().getTitle().equalsIgnoreCase("§c" + kit.getName())) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAuswählen")) {
						kitConnection.put(p, kit);
						p.sendMessage(Var.pr + "§aDu hast das Kit §e" + kit.getName() + " §aAusgewählt!");
						p.closeInventory();
					} else if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
						if (CoinsAPI.getCoins(p.getUniqueId().toString()) >= kit.getPreis()) {
							String perm = kit.getName().replace("§b", "");
							User user = Main.getApi().getUserManager().getUser(p.getUniqueId());
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
									"/lp user" + p.getName() + " permission set " + "iw.kits." + perm);

							// PermissionsEx.getUser(p).addPermission("sw.kits." + perm);
							p.sendMessage(Var.pr + "§aDu hast das Kit §e" + kit.getName() + " §agekauft!");
							MySQlPoints.update(p.getUniqueId(), kit.getPreis(), true, 0, 0, 0);
							p.closeInventory();
						} else {
							p.sendMessage(Var.pr + "§cDu hast zu wenig Coins um dieses Kit zu kaufen");
							p.closeInventory();
						}

					}

				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getItemInHand().getType().equals(Material.CHEST)) {
				if (GameStateHandler.getCurrentState() instanceof LobbyState) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aWähle dein Kit!")) {
						openKitGUI(p);
					}
				}
			} else if (p.getItemInHand().getType().equals(Material.RED_BED)) {
				if (GameStateHandler.getCurrentState() instanceof LobbyState) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aWähle dein Team!")) {
						openTeamGUI(p);
					}
				}
			}
			if (GameStateHandler.getCurrentState() instanceof EndState) {
				e.setCancelled(true);
			}
		}
	}

	private void openTeamGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 1, "§6§lWähle dein Team!");
		for (int i = 0; i < TeamHandler.teams; i++) {
			ItemStack it;
			int tid = i+1;
			if (TeamHandler.getPlayers(i) == null) {
				it = new ItemStack(Material.GREEN_WOOL);
				ItemMeta im = it.getItemMeta();
				im.setDisplayName("§aT" + tid);
				ArrayList<String> lore = new ArrayList<String>();
				for (int j = 0; j < TeamHandler.playersinTeam; j++) {
					lore.add("§7---");
				}
				im.setLore(lore);
				it.setItemMeta(im);
			} else {
				if (TeamHandler.getPlayers(i).size() == TeamHandler.playersinTeam) {
					it = new ItemStack(Material.RED_WOOL);
					ItemMeta im = it.getItemMeta();
					im.setDisplayName("§cT" + tid);
					ArrayList<String> lore = new ArrayList<String>();
					for (int j = 0; j < TeamHandler.playersinTeam; j++) {
						lore.add("§a" + TeamHandler.getPlayers(i).get(j).getName());
					}
					im.setLore(lore);
					it.setItemMeta(im);
				} else {
					it = new ItemStack(Material.GREEN_WOOL);
					ItemMeta im = it.getItemMeta();
					im.setDisplayName("§aT" + tid);
					ArrayList<String> lore = new ArrayList<String>();
					for (int j = 0; j < TeamHandler.getPlayers(i).size(); j++) {
						lore.add("§a" + TeamHandler.getPlayers(i).get(j).getName());
					}
					for (int j = TeamHandler.getPlayers(i).size(); j < TeamHandler.playersinTeam; j++) {
						lore.add("§7---");
					}
					im.setLore(lore);
					it.setItemMeta(im);

				}
			}
			inv.setItem(i, it);
		}
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);

	}

	public void openKitGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6§lWähle dein Kit!");
		for (int i = 0; i < kits.size(); i++) {
			inv.setItem(i, kits.get(i).getMenuItem());
		}
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
	}

	public ArrayList<Kit> getKits() {
		return this.kits;
	}

	public static Kit getPlayerKit(Player p) {
		return kitConnection.containsKey(p) ? kitConnection.get(p) : null;
	}

	public void openKitBuy(Player p, String name, ItemStack item, int price) {
		Inventory inv = Bukkit.createInventory(null, 9, "§c" + name);
		inv.setItem(0, item);
		String perm = name.replace("§b", "");
		if (p.hasPermission("iw.kits." + perm) || p.hasPermission("iw.kits.all") || p.hasPermission("iw.kits.*")) {
			ItemStack itemb = new ItemStack(Material.INK_SAC, 1, (short) 10);
			ItemMeta imeta = itemb.getItemMeta();
			imeta.setDisplayName("§aAuswählen");
			itemb.setItemMeta(imeta);
			inv.setItem(8, itemb);
		} else {
			ItemStack itemb = new ItemStack(Material.GOLD_INGOT);
			ItemMeta imeta = itemb.getItemMeta();
			String price1 = los(price);
			imeta.setDisplayName("§aKaufen §e" + price1 + " Coins");
			itemb.setItemMeta(imeta);
			inv.setItem(8, itemb);
		}
		p.openInventory(inv);

	}

	public static String los(int zahl) {
		DecimalFormat nf = new DecimalFormat();
		String ausgabe = nf.format(zahl);
		return ausgabe;
	}

}
