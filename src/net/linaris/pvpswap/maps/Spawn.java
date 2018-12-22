package net.linaris.pvpswap.maps;

import org.bukkit.Location;

public class Spawn {

	Location location;
	RarityChest chest;
	
	public Spawn(Location loc, RarityChest chest) {
		this.location = loc;
		this.chest = chest;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public RarityChest getChest() {
		return chest;
	}
	
}
