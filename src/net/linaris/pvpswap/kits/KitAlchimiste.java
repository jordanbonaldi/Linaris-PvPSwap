package net.linaris.pvpswap.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.LevelInfo;
import fr.icrotz.gameapi.utils.ItemBuilder;

public class KitAlchimiste extends PvPSwapKit {

	public KitAlchimiste() {
		super("kit-alchimiste","Alchimiste",new ItemStack(Material.BREWING_STAND_ITEM),"",true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eDes potions"));
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eDes potions"));
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eDes potions"));
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eDes potions"));
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eDes potions"));
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eDes potions"));
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		
		items.add(new KitItem(new ItemBuilder(Material.POTION,1,(short) 16418).setTitle("§6Stuff Alchimiste").build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.POTION,1,(short) 16420).setTitle("§6Stuff Alchimiste").build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.POTION,1,(short) 16428).setTitle("§6Stuff Alchimiste").build(),1,1));
		
	}

}
