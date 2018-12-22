package net.linaris.pvpswap.states;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.managers.GameManager;

public class FinalState extends State {

	public FinalState(GameManager manager) {
		super(manager, "FINISH", 60*10);
	}

	@Override
	public void onBegin() {
		getManager().setTimeStart(0);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§6Quel bruit horrible ! Mais étrangement, vous pétez la forme... Ceci était le dernier swap, que le meilleur gagne !");
		}
		
		for (Player online : getManager().getAlivesPlayers()) {
			online.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
			online.playSound(online.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
		}
		TaskManager.runTaskLater(() -> {
			
			for (Player player : getManager().getAlivesPlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
            }
			
			TaskManager.runTaskLater(() -> {
				
				getManager().findSwap(true);
				
			}, 20);
			
		}, 40);
	}

	@Override
	public void onRun() {
		for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("§8Les dieux en ont marre de vous voir vous toucher la nouille.");
        }
		for (Player player : getManager().getAlivesPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, 3));
            player.setFireTicks(Integer.MAX_VALUE);
            player.sendMessage("§2Vous êtes attaqué par §5une araignée ninja §2!");
            
        }
		
	}

	@Override
	public void onUpdate() {
		
	}

}
