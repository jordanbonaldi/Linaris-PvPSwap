package net.linaris.pvpswap.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class TeleportUtils {

	public static List<Location> makeCircleLocation(Location loc, Integer r, int number)
	 {
	  List<Location> locs = new ArrayList<Location>();
	  int x;
	  int y = loc.getWorld().getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()).getLocation().getBlockY() + 20;
	  int z;
	  
	  for(double i = 0.0; i < 360.0; i += 360 / number)
	  {
	   double angle = i * Math.PI / 180;
	   x = (int) (loc.getX() + r * Math.cos(angle));
	   z = (int) (loc.getZ() + r * Math.sin(angle));
	   
	   locs.add(new Location(loc.getWorld(), x, y, z));
	   
	  }
	  
	  return locs;
	 }
	 
	 public static HashMap<Location, Double> makeCircleLocationPI(Location loc, Integer r, int number)
	 {
	  HashMap<Location, Double> locs = new HashMap<Location, Double>();
	  int x;
	  int y = loc.getBlockY();
	  int z;
	  
	  for(double i = 0.0; i < 360.0; i += 360 / number)
	  {
	   double angle = i * Math.PI / 180;
	   x = (int) (loc.getX() + r * Math.cos(angle));
	   z = (int) (loc.getZ() + r * Math.sin(angle));
	   
	   locs.put(new Location(loc.getWorld(), x, y, z), i);
	   
	  }
	  
	  return locs;
	 }
	
	 public static List<BlockState> makePlatform(Location loc,int radius) {
		 
		 List<BlockState> bs = new ArrayList<BlockState>();
		 byte data = DyeColor.values()[new Random().nextInt(DyeColor.values().length)].getWoolData();
		 for (int x = -radius ; x <= radius; x++) {
			 for (int z = -radius ; z <= radius; z++) {
				 Block block = loc.clone().add(x, 0, z).getBlock();
				 bs.add(block.getState());
				 block.setType(Material.STAINED_GLASS);
		         block.setData(data);
			 }
		 }
				 
		 return bs;
	 }
}
