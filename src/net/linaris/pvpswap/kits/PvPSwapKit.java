package net.linaris.pvpswap.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.kits.Kit;

public abstract class PvPSwapKit extends Kit{

	protected List<KitItem> items;
	
	public PvPSwapKit(String uuid, String name, ItemStack item, String description, boolean firstFree) {
		super(uuid, name, item, description, firstFree);
		this.items = new ArrayList<>();
	}
	
	public List<KitItem> getItems() {
		return items;
	}
	
	public int getChance(Player p) {
		int level = getLevelKit(p);
		if (level == 0)
			return 10;
		if (level == 0)
			return 15;
		if (level == 0)
			return 20;
		if (level == 0)
			return 25;
		if (level == 0)
			return 30;
		if (level == 0)
			return 35;
		return 0;
	}

}
