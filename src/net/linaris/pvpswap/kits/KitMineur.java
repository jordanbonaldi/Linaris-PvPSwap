package net.linaris.pvpswap.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.LevelInfo;
import fr.icrotz.gameapi.utils.ItemBuilder;

public class KitMineur extends PvPSwapKit {

	public KitMineur() {
		super("kit-mineur","Mineur",new ItemStack(Material.IRON_PICKAXE),"",true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eUne pioche"," §7- §eUne hache"," §7- §eDes minerais"));
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		
		items.add(new KitItem(new ItemBuilder(Material.STONE_AXE,1,(short) 0).addEnchantment(Enchantment.DIG_SPEED, 2).setTitle("§6Stuff Mineur").build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.STONE_AXE,1,(short) 0).addEnchantment(Enchantment.DIG_SPEED, 2).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2).setTitle("§6Stuff Mineur").build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.IRON_ORE,1,(short) 0).setTitle("§6Stuff Roublart").build(),3,5));
		
	}

}
