package net.linaris.pvpswap.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import fr.icrotz.gameapi.modules.chat.SpectatorSpecEvent;
import fr.icrotz.gameapi.modules.gameplay.GamePlayModuleListener;
import fr.icrotz.gameapi.utils.MathUtils;
import fr.icrotz.gameserver.BukkitAPI;
import fr.icrotz.gameserver.api.PlayerData;
import fr.icrotz.gameserver.spectator.SpectatorManager;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.heads.Head;

public class GamePlayListener extends GamePlayModuleListener {

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		if (getGame().getGameManager().isSpectator(p))
			return;

		BukkitAPI api = BukkitAPI.get();

		if (p.getKiller() != null) {

			Player target = p.getKiller();

			api.getTasksManager().addTask(() -> {
				PlayerData data = api.getPlayerDataManager().getPlayerData(target.getName());
				data.creditCoins(10, "Kill", true, null);
			});

		}

		SpectatorManager.setSpectator(p);

		if (getGame().getGameManager().getAlivesPlayers().size() > 1) {
			Head head = Head.values()[MathUtils.integerRandomInclusive(0, Head.values().length - 1)];
			p.getWorld().dropItemNaturally(p.getLocation(), head.getItem());
		}

		getGame().getGameManager().testFinish();

	}


	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (getGame().getGameManager().isSpectator(e.getPlayer()))
			return;
		Player p = e.getPlayer();

		if (e.getPlayer().getInventory().getContents().length != 0)
			for (ItemStack item : e.getPlayer().getInventory().getContents()) {
				if (item != null)
					p.getWorld().dropItemNaturally(p.getLocation(), item);
			}
		
		e.setQuitMessage("§f" + p.getName() + " §7est mort en se déconnectant.");
		
		TaskManager.runTaskLater(() -> {
			getGame().getGameManager().testFinish();
		}, 20);
	
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerKickEvent e) {
		if (getGame().getGameManager().isSpectator(e.getPlayer()))
			return;
		Player p = e.getPlayer();

		if (e.getPlayer().getInventory().getContents().length != 0)
			for (ItemStack item : e.getPlayer().getInventory().getContents()) {
				if (item != null)
					p.getWorld().dropItemNaturally(p.getLocation(), item);
			}
		
		Bukkit.broadcastMessage("§f" + p.getName() + " §7est mort en se déconnectant.");

		TaskManager.runTaskLater(() -> {
			getGame().getGameManager().testFinish();
		}, 20);
	
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().name().contains("RIGHT")) {
			if (event.hasItem() && event.getMaterial() == Material.SKULL_ITEM) {
				for (Head head : Head.values()) {
					if (head.getItem().isSimilar(event.getItem())) {
						event.setCancelled(true);
						this.removeItemInHand(event);
						head.giveHeadEffect(event.getPlayer());
						event.getPlayer().sendMessage("Tête activée");
						break;
					}
				}
			}
		}
	}

	private void removeItemInHand(PlayerInteractEvent event) {
		if (event.getItem().getAmount() > 1) {
			event.getItem().setAmount(event.getItem().getAmount() - 1);
		} else {
			event.getPlayer().setItemInHand(null);
		}
	}
	
	
	@EventHandler
	public void onSpectatorSpeak(SpectatorSpecEvent e) {
		for (Player target : getGame().getGameManager().getSpectatorsPlayers())
			target.sendMessage("§8[§7Spec Only§8] §f" + e.getPlayer().getName() + ": " + e.getMessage());
	}

}
