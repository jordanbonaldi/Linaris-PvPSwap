package net.linaris.pvpswap.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.modules.scoreboard.ScoreBoardModule;
import fr.icrotz.gameapi.utils.RainbowEffect;
import fr.icrotz.gameapi.utils.ScoreboardSign;
import fr.icrotz.gameserver.BukkitAPI;
import fr.icrotz.gameserver.api.ServerInfo;

public class ScoreboardManager extends ScoreBoardModule {

	RainbowEffect domainEffect;
	String domainDisplay;

	public ScoreboardManager(Game game) {
		super(game);

		domainEffect = new RainbowEffect("play.linaris.fr", "§e", "§c", 40);

	}

	@Override
	public void onUpdate(Player p) {

		GameManager manager = (GameManager) getGame().getGameManager();

		ServerInfo infos = BukkitAPI.get().getServerInfos();
		
		ScoreboardSign bar = ScoreboardSign.get(p);
		if (bar == null) {
			bar = new ScoreboardSign(p, "§ePvPSwap");
			bar.create();
		}

		HashMap<Integer, String> lines = new HashMap<Integer, String>();

		if (getGame().isPreStart()) {
			
			bar.setObjectiveName("§ePvPSwap");
			lines.put(15, "§7-----------");
			lines.put(14, "§fJoueurs §e" + manager.getAlivesPlayers().size());
			lines.put(3, "§r§7-----------");
			lines.put(2, "§7Id §f" + BukkitAPI.get().getServName());
			lines.put(1, domainDisplay);

		} else {
			
			bar.setObjectiveName("§ePvPSwap §6" + getTimeFormat(manager.getTimeStart()));
			lines.put(15, "§7-----------");
			lines.put(14, "§fJoueurs §e" + manager.getAlivesPlayers().size());
			if (manager.isSpectator(p)) {
				lines.put(13, "§6§7-----------");
				lines.put(12, "§6Menu §ftouche §eE");
			}
			lines.put(3, "§r§7-----------");
			lines.put(2, "§7Id §f" + BukkitAPI.get().getServName());
			lines.put(1, domainDisplay);

			
		} 
		
		if (lines.isEmpty())
			return;
		for (int i = 1; i < 16; i++) {
			if (!lines.containsKey(i)) {
				if (bar.getLine(i) != null)
					bar.removeLine(i);
			} else {
				if (bar.getLine(i) == null)
					bar.setLine(i, lines.get(i));
				else if (!bar.getLine(i).equals(lines.get(i)))
					bar.setLine(i, lines.get(i));
			}
		}

	}
	
	public String getTimeFormat(int time) {

		int minutes = (int) time / 60;
		int seconds = (int) time % 60;

		return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}



	@Override
	public void onUpdate() {
		domainDisplay = domainEffect.next();
	}

}
