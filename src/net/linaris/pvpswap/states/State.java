package net.linaris.pvpswap.states;

import java.util.Random;

import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.managers.GameManager;

public abstract class State {

	private String id;
	private GameManager manager;
	private String name;
	private int time;
	
	public State(GameManager manager, String name, int time) {
		this.manager = manager;
		this.name = name;
		this.time = time+1;
	}
	
	public GameManager getManager() {
		return manager;
	}
	
	public String getName() {
		return name;
	}
	
	public void time() {
		this.time--;
	}
	
	public void update() {
		if (isFinish()) {
			onRun();
			TaskManager.cancelTaskByName(id);
			return;
		}
		time();
		onUpdate();
	}
	
	public boolean isFinish() {
		return (time <= 1);
	}
	
	public int getTime() {
		return time;
	}
	
	public void run() {
		onBegin();
		this.id = name + new Random().nextInt(Integer.MAX_VALUE);
		TaskManager.scheduleSyncRepeatingTask(id, this::update, 0, 20);
	}
	
	public abstract void onBegin();
	
	public abstract void onRun();
	
	public abstract void onUpdate();
	
}
