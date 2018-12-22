package net.linaris.pvpswap.managers;

import org.bukkit.inventory.ItemStack;

import net.linaris.pvpswap.maps.Rarity;

public class Item {
	
	ItemStack item;
	Rarity rarity;
	int minAmount;
	int maxAmount;
	
	public Item(ItemStack item, Rarity rarity, int minAmount,int maxAmount) {
		this.item = item;
		this.rarity = rarity;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	
	public int getMinAmount() {
		return minAmount;
	}
	
	public int getMaxAmount() {
		return maxAmount;
	}
	

}
