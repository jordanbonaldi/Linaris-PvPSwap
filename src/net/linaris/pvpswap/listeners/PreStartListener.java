package net.linaris.pvpswap.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.icrotz.gameapi.modules.events.GamePreStartTickEvent;
import fr.icrotz.gameapi.modules.prestart.PreStartModuleListener;
import fr.icrotz.gameapi.utils.WaitingRoomUtils;

public class PreStartListener extends PreStartModuleListener {

	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onSendMessage(GamePreStartTickEvent e) {
		e.setMessage("§6Debut du jeu dans §e" + e.getTime() +" secondes.");
		e.setTitle("§6Début du jeu dans §c" + e.getTime());
		e.setSubTitle("§r");
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		if (!WaitingRoomUtils.isInBase(e.getTo())) {
			e.getPlayer().sendMessage("§fVous ne pouvez pas sortir de la WaitingRoom");
			e.getPlayer().teleport(getGame().getGameManager().getLobbyLocation());
		}
	}
}
