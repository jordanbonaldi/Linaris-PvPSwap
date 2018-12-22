package net.linaris.pvpswap.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.LevelInfo;
import fr.icrotz.gameapi.utils.ItemBuilder;

public class KitBourrin extends PvPSwapKit {

	public KitBourrin() {
		super("kit-bourrin","Bourrin",new ItemStack(Material.STONE_SWORD),"",true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eUne épée"," §7- §eDu stuff en cuir P2"));
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		
		items.add(new KitItem(new ItemBuilder(Material.LEATHER_HELMET).setTitle("§6Stuff Bourrin").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.LEATHER_CHESTPLATE).setTitle("§6Stuff Bourrin").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.LEATHER_LEGGINGS).setTitle("§6Stuff Bourrin").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.LEATHER_BOOTS).setTitle("§6Stuff Bourrin").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.STONE_SWORD).setTitle("§6Stuff Bourrin").build(),1,1));
		
	}

}
