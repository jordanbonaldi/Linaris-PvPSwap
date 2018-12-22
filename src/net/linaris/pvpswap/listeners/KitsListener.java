package net.linaris.pvpswap.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.modules.kits.KitsModuleListener;
import fr.icrotz.gameapi.utils.MathUtils;
import fr.icrotz.gameserver.api.PlayerLocal;
import net.linaris.pvpswap.kits.KitItem;
import net.linaris.pvpswap.kits.PvPSwapKit;
import net.linaris.pvpswap.managers.GameManager;
import net.linaris.pvpswap.maps.RarityChest;

public class KitsListener extends KitsModuleListener {

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent e) {
	}
	
	@Override
	public void onDrop(PlayerDropItemEvent e) {
	}
	
	@Override
	public void onPlayer(PlayerDeathEvent e) {
	}
	
	@EventHandler
	public void onPlayerOpenChest(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Block b = e.getClickedBlock();
		if (b == null) return;
		if (b.getType() != Material.CHEST) return;
		//if (b.getMetadata("opened") != null) return;
		if (!b.getMetadata("opened").isEmpty()) return;
		b.setMetadata("opened", new FixedMetadataValue(getGame(), true));
		
		Player p = e.getPlayer();
		PlayerLocal pl = PlayerLocal.getPlayer(p.getName());
		
		Chest chest = (Chest) b.getState();
		RarityChest bchest = null;
		
		
		GameManager manager = (GameManager) getGame().getGameManager();
		
		for (RarityChest rchest : manager.getRarityChests()) {
			if (rchest.getChest() != null && rchest.getChest().equals(chest))
				bchest = rchest;
		}
		
		if (bchest == null) return;
		
		
		manager.refillChests(bchest);
		
		
		if (pl.contains("kit")) {
			PvPSwapKit kit = (PvPSwapKit) Game.getGame().getKitsManager().getKitByID(pl.get("kit"));
			if (kit != null) {
				
				int random = MathUtils.integerRandomInclusive(0, 100);
				
				if (random >= kit.getChance(p)) return;
				
				
				List<KitItem> items = kit.getItems();

				int rand = new Random().nextInt(items.size());

				KitItem item = items.get(rand);

				ItemStack itemStack = item.getItem().clone();

				itemStack.setAmount(MathUtils.integerRandomInclusive(item.getMinAmount(), item.getMaxAmount()));

				int slot = new Random().nextInt(27);
//				if (chest.getBlockInventory().) {
//					slot = new Random().nextInt(27);
//				}
				
				chest.getBlockInventory().setItem(slot, itemStack);
				chest.update(true);

				
			}
		}
	}
}
