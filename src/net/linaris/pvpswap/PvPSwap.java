package net.linaris.pvpswap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.modules.chat.ChatModuleListener;
import fr.icrotz.gameapi.modules.finish.FinishModuleListener;
import fr.icrotz.gameapi.utils.WaitingRoomUtils;
import fr.icrotz.gameserver.BukkitAPI;
import fr.icrotz.gameserver.api.Games;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.listeners.GamePlayListener;
import net.linaris.pvpswap.listeners.KitsListener;
import net.linaris.pvpswap.listeners.PreStartListener;
import net.linaris.pvpswap.managers.GameManager;
import net.linaris.pvpswap.managers.KitsManager;
import net.linaris.pvpswap.managers.ScoreboardManager;
import net.linaris.pvpswap.tasks.GamePlayTask;

public class PvPSwap extends Game {

	protected boolean inits;

	@Override
	public void onLoad() {

		getConfig().options().copyDefaults(true);
		saveConfig();

		inits(Games.PVPSWAP, "Citadelle", new GameManager(this));

		inits = true;
	}

	@Override
	public void onEnable() {

		if (!inits) {
			Bukkit.shutdown();
			return;
		}

		setBuild(true);
		setPreStartListener(new PreStartListener());
		setGamePlayListener(new GamePlayListener());
		setFinishListener(new FinishModuleListener());
		setChatListener(new ChatModuleListener());
		setInGameTask(new GamePlayTask(this));
		setKits(new KitsManager(), new KitsListener());
		setMinPlayer(20 / 2); // 15
		setMaxPlayer(21);
		setTimeToStart(120);

		setScoreboard(new ScoreboardManager(this));

		TaskManager.scheduleSyncRepeatingTask("Scoreboard", () -> {

			getScoreboard().update();

		} , 0, 1);

		World world = Bukkit.getWorlds().get(0);
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setTime(6000);

		super.onEnable();

		getGame().getInfos().setCanJoin(true, false);
		getGame().getInfos().setCanSee(true, true);
		
		WaitingRoomUtils.setLoc1(new Location(world,-23,115,-73));
		WaitingRoomUtils.setLoc2(new Location(world,-67,139,-117));
	}

	

	@Override
	public void onDisable() {
		BukkitAPI.get().getTasksManager().addTask(() -> {
			getGame().getInfos().setCanJoin(false, false);
			getGame().getInfos().setCanSee(false, false);
		});
	}

	@Override
	public void preInits() {

	}

	@Override
	public void inits() {

	}

	public static String getPrefix() {
		return "§8[§9PvPSwap§8] ";
	}

	@Override
	public String playerJoinMessage(Player p, int players, int maxPlayers) {
		return "§f" + p.getName() + " §7a rejoint la partie §a(" + players + "/" + getMaxPlayer() + ")";
	}

	@Override
	public String playerQuitMessage(Player p, int players, int maxPlayers) {
		return "";
	}

}
