package net.linaris.pvpswap.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.LevelInfo;
import fr.icrotz.gameapi.utils.ItemBuilder;

public class KitEnchanteur extends PvPSwapKit {

	public KitEnchanteur() {
		super("kit-enchanteur","Enchanteur",new ItemStack(Material.EXP_BOTTLE),"",true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"));
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		
		items.add(new KitItem(new ItemBuilder(Material.ENCHANTED_BOOK).setTitle("§6Stuff Enchanteur").addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.KNOCKBACK, 2).build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.EXP_BOTTLE).setTitle("§6Stuff Enchanteur").build(),2,4));
		
	}

}
