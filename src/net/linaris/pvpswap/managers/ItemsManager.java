package net.linaris.pvpswap.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.utils.ItemBuilder;
import net.linaris.pvpswap.maps.Rarity;

public class ItemsManager {

	List<Item> items;

	public ItemsManager() {
		this.items = new ArrayList<Item>();

		this.items.add(new Item(new ItemBuilder(new ItemStack(320, 1, (short) 0)).build(), Rarity.LOW, 5, 10));
		this.items.add(new Item(new ItemBuilder(new ItemStack(307, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), Rarity.LOW, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(280, 1, (short) 0)).addEnchantment(Enchantment.KNOCKBACK, 1).build(),
				Rarity.LOW, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(403, 1, (short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),
				Rarity.LOW, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(257, 1, (short) 0)).build(), Rarity.LOW, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(384, 1, (short) 0)).build(), Rarity.LOW, 4, 8));
		this.items.add(new Item(new ItemBuilder(new ItemStack(262, 1, (short) 0)).build(), Rarity.LOW, 4, 16));
		this.items.add(new Item(new ItemBuilder(new ItemStack(424, 1, (short) 0)).build(), Rarity.LOW, 5, 10));
		this.items.add(new Item(new ItemBuilder(new ItemStack(267, 1, (short) 0)).build(), Rarity.LOW, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(261, 1, (short) 0)).build(), Rarity.MIDDLE, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(262, 1, (short) 0)).build(), Rarity.MIDDLE, 4, 16));
		this.items.add(new Item(new ItemBuilder(new ItemStack(384, 1, (short) 0)).build(), Rarity.MIDDLE, 4, 8));
		this.items.add(new Item(new ItemBuilder(new ItemStack(306, 1, (short) 0)).build(), Rarity.MIDDLE, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(307, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), Rarity.HIGHT, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(308, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), Rarity.HIGHT, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(309, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), Rarity.HIGHT, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(306, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), Rarity.HIGHT, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(267, 1, (short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),
				Rarity.HIGHT, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(261, 1, (short) 0)).addEnchantment(Enchantment.ARROW_DAMAGE, 1).build(),
				Rarity.HIGHT, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(320, 1, (short) 0)).build(), Rarity.HIGHT, 5, 10));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(403, 1, (short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 2).build(),
				Rarity.FINAL, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(403, 1, (short) 0)).addEnchantment(Enchantment.KNOCKBACK, 2).build(),
				Rarity.FINAL, 1, 1));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(403, 1, (short) 0)).addEnchantment(Enchantment.ARROW_DAMAGE, 2).build(),
				Rarity.FINAL, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(403, 1, (short) 0))
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(), Rarity.FINAL, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(384, 1, (short) 0)).build(), Rarity.FINAL, 20, 20));
		this.items.add(new Item(new ItemBuilder(new ItemStack(262, 1, (short) 0)).build(), Rarity.FINAL, 12, 12));
		this.items.add(new Item(
				new ItemBuilder(new ItemStack(276, 1, (short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),
				Rarity.FINAL, 1, 1));
		this.items.add(new Item(new ItemBuilder(new ItemStack(373, 1, (short) 16417)).build(), Rarity.FINAL, 1, 1));
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Item> getItemsByRarity(Rarity ra) {
		List<Item> items = new ArrayList<Item>();
		for (Item item : this.items)
			if (item.getRarity().equals(ra))
				items.add(item);

		return items;
	}

}
