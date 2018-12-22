package net.linaris.pvpswap.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.icrotz.gameapi.AbstractGameManager;
import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.GameState;
import fr.icrotz.gameapi.modules.permissions.PermissionsModuleManager;
import fr.icrotz.gameapi.utils.MathUtils;
import fr.icrotz.gameapi.utils.PlayerUtils;
import fr.icrotz.gameapi.utils.TitleUtils;
import fr.icrotz.gameserver.BukkitAPI;
import fr.icrotz.gameserver.api.PlayerData;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.maps.Map;
import net.linaris.pvpswap.maps.Rarity;
import net.linaris.pvpswap.maps.RarityChest;
import net.linaris.pvpswap.maps.Spawn;
import net.linaris.pvpswap.states.FirstState;

public class GameManager extends AbstractGameManager {

	private State state;
	private int timeStart;
	private List<Map> maps;
	private List<Location> finalSpawns;
	private List<RarityChest> finalChests;
	private ItemsManager itemsManager;

	public GameManager(Game game) {
		super(game);
		this.maps = new ArrayList<>();
		this.finalSpawns = new ArrayList<>();
		this.finalChests = new ArrayList<>();
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	@Override
	public void inits() {
		setLobbyLocation(new Location(Bukkit.getWorld("world"), -45.5, 130, -95.5));

		this.itemsManager = new ItemsManager();
//
//		Chunk mainChunk = getLobbyLocation().getChunk();
//
//		for (int x = -10; x <= 10; x++) {
//			for (int z = -10; z <= 10; z++) {
//				Chunk now = getLobbyLocation().getWorld().getChunkAt(mainChunk.getX() + x, mainChunk.getZ() + z);
//				if (now.isLoaded())
//					now.load(true);
//				now.unload(true);
//			}
//		}
//		
		Map purpleMap = new Map("Violet");
		purpleMap.addSpawn(new Spawn(getLocation(-12, 45, -124), new RarityChest(getChest(-10, 45, -126), Rarity.LOW)));
		purpleMap.addSpawn(new Spawn(getLocation(-74, 45, -124), new RarityChest(getChest(-76, 45, -126), Rarity.LOW)));
		purpleMap.addSpawn(new Spawn(getLocation(-74, 45, -62), new RarityChest(getChest(-76, 45, -60), Rarity.LOW)));
		purpleMap.addSpawn(new Spawn(getLocation(-12, 45, -62), new RarityChest(getChest(-10, 45, -60), Rarity.LOW)));
		purpleMap.addChest(new RarityChest(getChest(-59, 38, -76), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-26, 38, -77), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-27, 38, -110), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-60, 38, -109), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-43, 36, -93), Rarity.HIGHT));
		purpleMap.addChest(new RarityChest(getChest(-24, 31, -92), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-44, 31, -74), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-62, 31, -94), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-42, 31, -112), Rarity.LOW));
		purpleMap.addChest(new RarityChest(getChest(-43, 30, -93), Rarity.MIDDLE));
		maps.add(purpleMap);

		Map redMap = new Map("Rouge");
		redMap.addSpawn(new Spawn(getLocation(61, 39, -63), new RarityChest(getChest(59, 39, -61), Rarity.LOW)));
		redMap.addSpawn(new Spawn(getLocation(123, 39, -63), new RarityChest(getChest(125, 39, -61), Rarity.LOW)));
		redMap.addSpawn(new Spawn(getLocation(123, 39, -125), new RarityChest(getChest(125, 39, -127), Rarity.LOW)));
		redMap.addSpawn(new Spawn(getLocation(61, 39, -125), new RarityChest(getChest(59, 39, -127), Rarity.LOW)));
		redMap.addChest(new RarityChest(getChest(76, 32, -77), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(109, 32, -78), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(108, 32, -111), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(75, 32, -110), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(92, 30, -94), Rarity.HIGHT));
		redMap.addChest(new RarityChest(getChest(93, 25, -113), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(111, 25, -93), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(91, 25, -75), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(73, 25, -95), Rarity.LOW));
		redMap.addChest(new RarityChest(getChest(92, 24, -94), Rarity.MIDDLE));
		maps.add(redMap);

		Map pinkMap = new Map("Rose");
		pinkMap.addSpawn(new Spawn(getLocation(-13, 39, -197), new RarityChest(getChest(-11, 39, -195), Rarity.LOW)));
		pinkMap.addSpawn(new Spawn(getLocation(-13, 39, -259), new RarityChest(getChest(-11, 39, -261), Rarity.LOW)));
		pinkMap.addSpawn(new Spawn(getLocation(-75, 39, -259), new RarityChest(getChest(-77, 39, -261), Rarity.LOW)));
		pinkMap.addSpawn(new Spawn(getLocation(-75, 39, -197), new RarityChest(getChest(-77, 39, -195), Rarity.LOW)));
		pinkMap.addChest(new RarityChest(getChest(-27, 32, -212), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-28, 32, -245), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-61, 32, -244), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-60, 32, -211), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-44, 30, -228), Rarity.HIGHT));
		pinkMap.addChest(new RarityChest(getChest(-25, 25, -227), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-45, 25, -209), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-63, 25, -229), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-43, 25, -247), Rarity.LOW));
		pinkMap.addChest(new RarityChest(getChest(-44, 24, -228), Rarity.MIDDLE));

		maps.add(pinkMap);

		Map greenMap = new Map("Vert");
		greenMap.addSpawn(
				new Spawn(getLocation(-147, 39, -124), new RarityChest(getChest(-145, 39, -126), Rarity.LOW)));
		greenMap.addSpawn(
				new Spawn(getLocation(-209, 39, -124), new RarityChest(getChest(-211, 39, -126), Rarity.LOW)));
		greenMap.addSpawn(new Spawn(getLocation(-209, 39, -62), new RarityChest(getChest(-211, 39, -60), Rarity.LOW)));
		greenMap.addSpawn(new Spawn(getLocation(-147, 39, -62), new RarityChest(getChest(-145, 39, -60), Rarity.LOW)));
		greenMap.addChest(new RarityChest(getChest(-162, 32, -110), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-195, 32, -109), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-194, 32, -76), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-161, 32, -77), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-178, 30, -93), Rarity.HIGHT));
		greenMap.addChest(new RarityChest(getChest(-177, 25, -112), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-159, 25, -92), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-179, 25, -74), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-197, 25, -94), Rarity.LOW));
		greenMap.addChest(new RarityChest(getChest(-178, 24, -93), Rarity.MIDDLE));
		maps.add(greenMap);

		Map yellowMap = new Map("Jaune");
		yellowMap.addSpawn(new Spawn(getLocation(-12, 39, 11), new RarityChest(getChest(-10, 39, 9), Rarity.LOW)));
		yellowMap.addSpawn(new Spawn(getLocation(-74, 39, 11), new RarityChest(getChest(-76, 39, 9), Rarity.LOW)));
		yellowMap.addSpawn(new Spawn(getLocation(-74, 39, 73), new RarityChest(getChest(-76, 39, 75), Rarity.LOW)));
		yellowMap.addSpawn(new Spawn(getLocation(-12, 39, 73), new RarityChest(getChest(-10, 39, 75), Rarity.LOW)));
		yellowMap.addChest(new RarityChest(getChest(-60, 32, 26), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-59, 32, 59), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-26, 32, 58), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-27, 32, 25), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-43, 30, 42), Rarity.HIGHT));
		yellowMap.addChest(new RarityChest(getChest(-24, 25, 43), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-44, 25, 61), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-62, 25, 41), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-42, 25, 23), Rarity.LOW));
		yellowMap.addChest(new RarityChest(getChest(-43, 24, 42), Rarity.MIDDLE));
		maps.add(yellowMap);

		finalSpawns.add(getLocation(-188, 41, -225));
		finalSpawns.add(getLocation(-216, 41, -253));
		finalSpawns.add(getLocation(-244, 41, -225));
		finalSpawns.add(getLocation(-216, 41, -197));

		finalChests.add(new RarityChest(getChest(-190, 41, -228), Rarity.FINAL));
		finalChests.add(new RarityChest(getChest(-219, 41, -253), Rarity.FINAL));
		finalChests.add(new RarityChest(getChest(-244, 41, -224), Rarity.FINAL));
		finalChests.add(new RarityChest(getChest(-215, 41, -199), Rarity.FINAL));
		finalChests.add(new RarityChest(getChest(-217, 47, -225), Rarity.HIGHT));

	}

	public List<RarityChest> getRarityChests() {
		List<RarityChest> chests = new ArrayList<>();
		for (Map map : maps) {
			chests.addAll(map.getChests());
			for (Spawn spawn : map.getSpawns())
				chests.add(spawn.getChest());
		}
		chests.addAll(finalChests);
		return chests;
	}

	public void refillChests(RarityChest rchest) {

		rchest.getChest().getLocation().getChunk().load(true);

		rchest.getChest().getBlockInventory().setItem(0, new ItemStack(Material.AIR));

		int amount = MathUtils.integerRandomInclusive(3, 4);

		List<Item> items = itemsManager.getItemsByRarity(rchest.getRarity());

		for (int i = 0; i < amount; i++) {

			int rand = new Random().nextInt(items.size());

			Item item = items.get(rand);
			items.remove(item);

			ItemStack itemStack = item.getItem().clone();

			itemStack.setAmount(MathUtils.integerRandomInclusive(item.getMinAmount(), item.getMaxAmount()));

			if (itemStack.getType() == Material.EXP_BOTTLE) {
				rchest.getChest().getBlockInventory().setItem(new Random().nextInt(27),
						new ItemStack(351, (int) itemStack.getAmount() / 2, (short) 4));
			}
			rchest.getChest().getBlockInventory().setItem(new Random().nextInt(27), itemStack);
			rchest.getChest().update(true);

		}

	}

	@Override
	public void gameStart() {

		setState(State.FIRST);

		getGame().getInfos().setCanJoin(false, false);
		getGame().getInfos().setCanSee(false, true);

		try {
			org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getMainScoreboard();
			Objective objective2 = board.registerNewObjective("health", "health");
			objective2.setDisplayName("ยงcโ?ค");
			objective2.setDisplaySlot(DisplaySlot.BELOW_NAME);

			Objective objective21 = board.registerNewObjective("kills", "totalKillCount");
			objective21.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		} catch (Exception e) {
			// TODO: handle exception
		}

		for (Player p : getAlivesPlayers()) {
			p.setFallDistance(0);
			PlayerUtils.razPlayer(p);
			p.setGameMode(GameMode.SURVIVAL);
		}

		World world = Bukkit.getWorlds().get(0);
		world.setGameRuleValue("doDaylightCycle", "true");

		new FirstState(this).run();

	}

	@Override
	public void testFinish() {

		if (getGameState() != GameState.INGAME)
			return;

		int alivesPlayers = getAlivesPlayers().size();
		if (alivesPlayers == 0)
			winGame(null);
		else if (alivesPlayers == 1)
			winGame(getAlivesPlayers().iterator().next());
	}

	@Override
	public void gameFinish() {
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public enum State {
		FIRST, OTHER, LAST
	}

	@Override
	public void onFinish() {
		PermissionsModuleManager perms = getGame().getPermissionsManager();

		perms.setDeadDrop(false);
		perms.setAutoRespawn(true);
		perms.setItemPickup(false);
		perms.setDamageMob(false);
		perms.setDamagePlayer(false);
		perms.setPvp(false);
		perms.setCraft(false);
		perms.setBlockBreak(false);
		perms.setBlockPlace(false);
		perms.setInventoryClick(false);
		perms.setEntityTarget(false);
		if (winner == null)
			return;
		if (winner instanceof Player) {
			Player p = (Player) winner;
			TitleUtils.sendTitleToAllPlayers("ยง6Victoire de ยงe" + p.getName(), "");
			BukkitAPI.get().getTasksManager().addTask(() -> {
				PlayerData data = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
				data.creditCoins(10, "Victoire", true, null);
				data.creditLegendaryCoins(2, "Victoire", true, null);
			});
		}
	}

	public Location getLocation(double x, double y, double z) {
		return new Location(Bukkit.getWorld("world"), x, y, z);
	}

	public Location getChest(int x, int y, int z) {
		return getLocation(x, y, z);
	}

	public void findSwap(boolean last) {

		if (!last) {

			List<Player> players = getAlivesPlayers();
			Collections.shuffle(players);

			for (Map map : maps) {

				if (players.isEmpty())
					break;

				List<Player> inMap = new ArrayList<>();

				for (int i = 0; i < map.getSpawns().size(); i++) {

					Spawn spawn = map.getSpawns().get(i);

					if (players.size() <= i)
						break;

					Player target = players.get(i);

					target.teleport(spawn.getLocation());

					inMap.add(target);
				}

				if (inMap.isEmpty())
					continue;

				for (Player target : inMap) {

					for (int drop = 0; drop < inMap.size() - 1; drop++) {
						TaskManager.runTaskLater(() -> {
							target.playSound(target.getLocation(), Sound.EXPLODE, 1, 0.5F);
						} , 10 + drop * 20);
					}

					players.remove(target);

				}

			}

		} else {

			for (int i = 0; i < getAlivesPlayers().size(); i++) {
				Player p = getAlivesPlayers().get(i);
				p.teleport(finalSpawns.get(i));
				p.setMaxHealth(30);
				p.setHealth(30);
				BukkitAPI.get().getTasksManager().addTask(() -> {
					PlayerData data = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
					data.creditCoins(10, "Arene", true, null);
					data.creditLegendaryCoins(1, "Arene", true, null);
				});
			}

		}

	}

}
