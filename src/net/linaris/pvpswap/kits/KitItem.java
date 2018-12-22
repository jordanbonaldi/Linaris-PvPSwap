package net.linaris.pvpswap.kits;

import org.bukkit.inventory.ItemStack;

public class KitItem {
	
	ItemStack item;
	int minAmount;
	int maxAmount;
	
	public KitItem(ItemStack item,  int minAmount,int maxAmount) {
		this.item = item;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public ItemStack getItem() {
		return item;
	}

	
	public int getMinAmount() {
		return minAmount;
	}
	
	public int getMaxAmount() {
		return maxAmount;
	}
	

}
