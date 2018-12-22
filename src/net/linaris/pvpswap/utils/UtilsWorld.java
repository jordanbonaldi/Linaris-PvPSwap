package net.linaris.pvpswap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.linaris.pvpswap.utils.schematics.Schematic;
import net.linaris.pvpswap.utils.schematics.jnbt.ByteArrayTag;
import net.linaris.pvpswap.utils.schematics.jnbt.CompoundTag;
import net.linaris.pvpswap.utils.schematics.jnbt.NBTInputStream;
import net.linaris.pvpswap.utils.schematics.jnbt.ShortTag;
import net.linaris.pvpswap.utils.schematics.jnbt.Tag;

public class UtilsWorld {

	public static void pasteSchematic(World world, Location loc, Schematic schematic)
	{
		short[] blocks = schematic.getBlocks();
		byte[] blockData = schematic.getData();

		short length = schematic.getLenght();
		short width = schematic.getWidth();
		short height = schematic.getHeight();

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				for (int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					Block block = new Location(world, x + loc.getX() - (width / 2), y + loc.getY(), z + loc.getZ() - (length/2)).getBlock();
					if (!block.getChunk().isLoaded())
						block.getChunk().load(true);
					try{
						block.setTypeIdAndData(blocks[index], blockData[index], true);
						if (block.getType() == Material.MOB_SPAWNER) {
							CreatureSpawner spawner = (CreatureSpawner) block.getState();
							spawner.setSpawnedType(EntityType.BLAZE);
						}
						if (block.getType() == Material.CHEST) {
							Chest chest = (Chest) block.getState();
							Inventory inv = chest.getBlockInventory();
							int number = randInt(2, 6);
							for (int i = 0; i < number; i++) {
								
								int r = randInt(0, 100);
								
								ItemStack item = null;
								
								if (r < 40) {
									item = getItem(Material.GOLD_INGOT,2);
								} else if (r < 50) {
									item = getItem(Material.GOLDEN_APPLE);
								} else if (r < 70) {
									item = getItem(Material.DIAMOND,2);
								} else {
									item = getItem(Material.IRON_INGOT,5);
								}
								
								int slot = randInt(0, 26);
								
								inv.setItem(slot, item);
								
							}
						}
						
					} catch (Exception e){ Bukkit.broadcastMessage("" + blocks[index] + " / " + blockData[index]); }
				}
			}
		}
	}
	
	public static ItemStack getItem(Material m) {
		return new ItemStack(m);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getItem(int id) {
		return new ItemStack(id);
	}
	
	public static ItemStack getItem(Material m,int a) {
		return new ItemStack(m,a);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getItem(int id,int a) {
		return new ItemStack(id,a);
	}

	public static Schematic loadSchematic(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        NBTInputStream nbtStream = new NBTInputStream(stream);
 
        CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
        if (!schematicTag.getName().equals("Schematic")) {
        	nbtStream.close();
            throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
        }
 
        Map<String, Tag> schematic = schematicTag.getValue();
        if (!schematic.containsKey("Blocks")) {
        	nbtStream.close();
            throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
        }
 
        short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
        short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
        short height = getChildTag(schematic, "Height", ShortTag.class).getValue();
 
 
        byte[] blockId = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
        byte[] blockData = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
        byte[] addId = new byte[0];
        short[] blocks = new short[blockId.length];
 
       
        if (schematic.containsKey("AddBlocks")) {
            addId = getChildTag(schematic, "AddBlocks", ByteArrayTag.class).getValue();
        }
 
        for (int index = 0; index < blockId.length; index++) {
            if ((index >> 1) >= addId.length) { 
                blocks[index] = (short) (blockId[index] & 0xFF);
            } else {
                if ((index & 1) == 0) {
                    blocks[index] = (short) (((addId[index >> 1] & 0x0F) << 8) + (blockId[index] & 0xFF));
                } else {
                    blocks[index] = (short) (((addId[index >> 1] & 0xF0) << 4) + (blockId[index] & 0xFF));
                }
            }
        }

    	nbtStream.close();
        return new Schematic(blocks, blockData, width, length, height);
    }

	private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException
	{
		if (!items.containsKey(key)) {
			throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
		}
		Tag tag = items.get(key);
		if (!expected.isInstance(tag)) {
			throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
		}
		return expected.cast(tag);
	}
	
	public static int randInt(int min, int max) {

		 
	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
