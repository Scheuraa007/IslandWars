package de.Scheuraa.IslandWars.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import de.Scheuraa.IslandWars.Main.Main;
import de.Scheuraa.IslandWars.methods.chest.ChestHandler;
import de.Scheuraa.IslandWars.methods.chest.ChestItem;

public class ItemsHandler {
	private File itemsFile = null;
	private FileConfiguration itemsc = null;
	private Main plugin;

	public static ArrayList<ChestItem> itemsList = new ArrayList<ChestItem>();

	public ItemsHandler() {
		this.plugin = Main.getPlugin();
		loadItemsFile();
		loadChestHandler();
	}

	private void loadItemsFile() {
		if (itemsFile == null) {
			itemsFile = new File(plugin.getDataFolder(), "items.yml");
		}
		if (!itemsFile.exists()) {
			plugin.saveResource("items.yml", false);
			itemsc = YamlConfiguration.loadConfiguration(itemsFile);
		} else {
			itemsc = YamlConfiguration.loadConfiguration(itemsFile);
		}

	}

	private void loadChestHandler() {
		YamlConfiguration itemscfg = YamlConfiguration.loadConfiguration(itemsFile);
		ArrayList<String> items = (ArrayList<String>) itemscfg.getStringList("items");				
		for (String item : items) {
            String[] split2 = item.split("%");
            String[] split = split2[0].split("-");

            ItemStack itemStack = null;
            Material mat = Material.valueOf(split[0]);
            itemStack = new ItemStack(mat);
            if (split2.length > 1) {
                String[] hoehe = split2[1].split(":");
                String enchantment = hoehe[0];
                Enchantment enchantmentname = Enchantment.getByName(enchantment);
                int enchid;
                if (hoehe.length > 1) {
                    enchid = Integer.parseInt(hoehe[1]);
                } else {
                    enchid = 1;
                }
                if (enchantmentname!= null){
                    itemStack.addUnsafeEnchantment(enchantmentname, enchid);
            }
            }
            int min = Integer.parseInt(split[1]);
            int max = Integer.parseInt(split[2]);
            int prob = Integer.parseInt(split[3]);
            itemsList.add(new ChestItem(itemStack, min, max, prob));
        }
		
		ArrayList<String> splashpotions = (ArrayList<String>) itemscfg.getStringList("splash_potions");
		for(String potion : splashpotions) {
			String[] split2 = potion.split("%");
            String[] split = split2[0].split("-");
            PotionType type = PotionType.valueOf(split[0]);
            ItemStack potionitem = new ItemStack(Material.SPLASH_POTION);
            boolean extended = Boolean.valueOf(split2[1]);
            boolean upgraded = Boolean.valueOf(split2[2]);
            PotionMeta meta = (PotionMeta) potionitem.getItemMeta();
            meta.setBasePotionData(new PotionData(type, extended, upgraded));
            potionitem.setItemMeta(meta);
            int min = Integer.parseInt(split[1]);
            int max = Integer.parseInt(split[2]);
            int prob = Integer.parseInt(split[3]);
            itemsList.add(new ChestItem(potionitem, min, max, prob));
            
		}
		ArrayList<String> potions = (ArrayList<String>) itemscfg.getStringList("potions");
		for(String potion : potions) {
			String[] split2 = potion.split("%");
            String[] split = split2[0].split("-");
            PotionType type = PotionType.valueOf(split[0]);
            ItemStack potionitem = new ItemStack(Material.POTION);
            boolean extended = Boolean.valueOf(split2[1]);
            boolean upgraded = Boolean.valueOf(split2[2]);
            PotionMeta meta = (PotionMeta) potionitem.getItemMeta();
            meta.setBasePotionData(new PotionData(type, extended, upgraded));
            potionitem.setItemMeta(meta);
            int min = Integer.parseInt(split[1]);
            int max = Integer.parseInt(split[2]);
            int prob = Integer.parseInt(split[3]);
            itemsList.add(new ChestItem(potionitem, min, max, prob));
            
		}
		ArrayList<String> lingeringpotions = (ArrayList<String>) itemscfg.getStringList("lingering_potions");
		for(String potion : lingeringpotions) {
			String[] split2 = potion.split("%");
            String[] split = split2[0].split("-");
            PotionType type = PotionType.valueOf(split[0]);
            ItemStack potionitem = new ItemStack(Material.LINGERING_POTION);
            boolean extended = Boolean.valueOf(split2[1]);
            boolean upgraded = Boolean.valueOf(split2[2]);
            PotionMeta meta = (PotionMeta) potionitem.getItemMeta();
            meta.setBasePotionData(new PotionData(type, extended, upgraded));
            potionitem.setItemMeta(meta);
            int min = Integer.parseInt(split[1]);
            int max = Integer.parseInt(split[2]);
            int prob = Integer.parseInt(split[3]);
            itemsList.add(new ChestItem(potionitem, min, max, prob));
            
		}
		
		plugin.setChesthandler(new ChestHandler(2, 7, itemsList));
		

	}

}
