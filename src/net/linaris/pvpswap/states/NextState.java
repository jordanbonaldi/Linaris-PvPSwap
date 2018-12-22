package net.linaris.pvpswap.states;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.icrotz.gameapi.utils.MathUtils;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.managers.GameManager;

public class NextState extends State {

	public NextState(GameManager manager) {
		super(manager, "ALL", MathUtils.integerRandomInclusive(120, 180));
	}

	@Override
	public void onBegin() {
		getManager().setTimeStart(0);
		for (Player online : getManager().getAlivesPlayers()) {
			online.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
			online.playSound(online.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
		}
		TaskManager.runTaskLater(() -> {
			
			for (Player player : getManager().getAlivesPlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
            }
			
			TaskManager.runTaskLater(() -> {
				
				getManager().findSwap(false);
				
			}, 20);
			
		}, 40);
	}

	@Override
	public void onRun() {
		
		if (getManager().getAlivesPlayers().size() > 4) {
			new NextState(getManager()).run();
		} else {
			new FinalState(getManager()).run();
		}
		
	}

	@Override
	public void onUpdate() {
		
	}

}
