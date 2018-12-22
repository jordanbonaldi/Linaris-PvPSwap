package net.linaris.pvpswap.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.LevelInfo;
import fr.icrotz.gameapi.utils.ItemBuilder;
import net.linaris.pvpswap.heads.Head;

public class KitRoublard extends PvPSwapKit {

	public KitRoublard() {
		super("kit-roublard","Roublard",new ItemStack(Material.WEB),"",true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eDes TnTs"," §7- §eDes Cobwebs"," §7- §eUn Briquet"," §7- §eUn Sceau de lave"," §7- §eUne Tête de poulet"));
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		
		items.add(new KitItem(new ItemBuilder(Material.TNT,1,(short) 0).setTitle("§6Stuff Roublart").build(),2,4));
		items.add(new KitItem(new ItemBuilder(Material.WEB,1,(short) 0).setTitle("§6Stuff Roublart").build(),6,8));
		items.add(new KitItem(new ItemBuilder(Material.LAVA_BUCKET,1,(short) 0).setTitle("§6Stuff Roublart").build(),1,1));
		items.add(new KitItem(new ItemBuilder(Material.FLINT_AND_STEEL,1,(short) 0).setTitle("§6Stuff Roublart").build(),1,1));
		items.add(new KitItem(Head.CHICKEN_ATTACK.getItem(),1,1));
		
	}

}
