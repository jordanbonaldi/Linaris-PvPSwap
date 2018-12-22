package net.linaris.pvpswap.maps;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private String name;
	private List<Spawn> spawns;
	private List<RarityChest> chests;
	
	public Map(String name) {
		this.name = name;
		this.spawns = new ArrayList<>();
		this.chests = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<Spawn> getSpawns() {
		return spawns;
	}
	
	public List<RarityChest> getChests() {
		return chests;
	}
	
	public void addSpawn(Spawn spawn) {
		spawns.add(spawn);
	}
	
	public void addChest(RarityChest chest) {
		chests.add(chest);
	}

}
