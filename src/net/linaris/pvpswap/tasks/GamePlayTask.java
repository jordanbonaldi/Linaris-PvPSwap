package net.linaris.pvpswap.tasks;

import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.gametasks.InGameTask;
import net.linaris.pvpswap.managers.GameManager;

public class GamePlayTask extends InGameTask {

	boolean now;
	
	public GamePlayTask(Game game) {
		super(game);
	}

	@Override
	public void run() {
		
		GameManager manager = (GameManager) getGame().getGameManager();
		
		manager.setTimeStart(manager.getTimeStart()+1);
	
		super.run();
	}

}
