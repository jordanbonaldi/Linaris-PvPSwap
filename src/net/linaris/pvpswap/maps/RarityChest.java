package net.linaris.pvpswap.maps;

import org.bukkit.Location;
import org.bukkit.block.Chest;

public class RarityChest {

	Location chest;
	Rarity rarity;
	
	public RarityChest(Location chest, Rarity rarity) {
		this.chest = (Location) chest;
		this.rarity = rarity;
	}
	
	public Chest getChest() {
		return (Chest) chest.getBlock().getState();
	}

	public Rarity getRarity() {
		return rarity;
	}
}
