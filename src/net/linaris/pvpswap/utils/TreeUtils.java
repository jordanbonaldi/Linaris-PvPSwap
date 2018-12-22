package net.linaris.pvpswap.utils;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.icrotz.gameserver.utils.Particles;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;

public class TreeUtils {

	public static void breakTree(Block b) {
		if (b.getTypeId() == 17 || b.getTypeId() == 162) {
			Particles.BLOCK_CRACK.display(new Particles.BlockData(b.getType(), b.getData()),new Vector(), 0.1f, b.getLocation(), 10);
			b.breakNaturally();
			SoundUtils.broadcastSound(Sound.DIG_WOOD, b.getLocation());
			
			for (BlockFace face : BlockFace.values())
				TaskManager.runTaskLater(() -> {breakTree(b.getRelative(face));},2);
		}
		if (b.getTypeId() == 18 || b.getTypeId() == 161) {
			
			Particles.BLOCK_CRACK.display(new Particles.BlockData(b.getType(),  b.getData()),new Vector(), 0.1f, b.getLocation(), 10);
			b.setType(Material.AIR);
			SoundUtils.broadcastSound(Sound.DIG_GRASS, b.getLocation(),0.3f);
			
			Random r = new Random();
			double randomValue = 0 + (100 - 0) * r.nextDouble();
			if (randomValue < 0.5) b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE));
			for (BlockFace face : BlockFace.values())
				TaskManager.runTaskLater(() -> {breakLeaves(b.getRelative(face), 2);}, 2);

		}
	}

	public static void breakLeaves(Block b, int number) {
		if (number <= 0)
			return;
		if (b.getTypeId() == 18 || b.getTypeId() == 161) {
			
			Particles.BLOCK_CRACK.display(new Particles.BlockData(b.getType(),  b.getData()),new Vector(), 0.1f, b.getLocation(), 10);			
			b.setType(Material.AIR);			SoundUtils.broadcastSound(Sound.DIG_GRASS, b.getLocation(),0.3f);
			
			Random r = new Random();
			double randomValue = 0 + (100 - 0) * r.nextDouble();
			if (randomValue < 0.5) b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE));
			
			for (BlockFace face : BlockFace.values())
				TaskManager.runTaskLater(() -> {breakLeaves(b.getRelative(face), number - 1);},2);
		}
	}

	public static boolean isWood(Block b) {
		return (b.getTypeId() == 17);
	}

	public static boolean isLeaves(Block b) {
		return (b.getTypeId() == 18);
	}

}
