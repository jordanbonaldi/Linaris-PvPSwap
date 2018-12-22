package net.linaris.pvpswap.managers;

import fr.icrotz.gameapi.modules.kits.KitsModuleManager;
import net.linaris.pvpswap.kits.KitAlchimiste;
import net.linaris.pvpswap.kits.KitBourrin;
import net.linaris.pvpswap.kits.KitEnchanteur;
import net.linaris.pvpswap.kits.KitMineur;
import net.linaris.pvpswap.kits.KitRoublard;

public class KitsManager extends KitsModuleManager{

	@Override
	public void inits() {
		register(new KitBourrin());
		register(new KitEnchanteur());
		register(new KitMineur());
		register(new KitRoublard());
		register(new KitAlchimiste());
	}

}
