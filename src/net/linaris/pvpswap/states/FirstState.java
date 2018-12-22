package net.linaris.pvpswap.states;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.icrotz.gameapi.Game;
import fr.icrotz.gameapi.modules.permissions.PermissionsModuleManager;
import fr.icrotz.gameserver.utils.tasksmanager.TaskManager;
import net.linaris.pvpswap.managers.GameManager;

public class FirstState extends State {

	public FirstState(GameManager manager) {
		super(manager, "FIRST", 2*60);
	}

	@Override
	public void onBegin() {
		PermissionsModuleManager perms = Game.getGame().getPermissionsManager();
		perms.setMove(false);
		
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
				
				perms.setMove(true);
				perms.setMoveX(true);
				perms.setDeadDrop(true);
				perms.setDrop(true);
				perms.setAutoRespawn(true);
				perms.setItemPickup(true);
				perms.setDamageMob(true);
				perms.setFood(true);
				perms.setDamagePlayer(true);
				perms.setCraft(true);
				perms.setBlockBreak(true);
				perms.setBlockPlace(true);
				perms.setInventoryClick(true);
				perms.setEntityTarget(true);	
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage("§cPvp désactivé ! §7Prochain swap dans §62m00s§7, Ouvrez les §6coffres §7!");
					p.sendMessage("§7Activez votre §cson§7! A chaque swap, un §ccoup de canon §7indique §7la présence d'un §cadversaire§7.");
				}
				
			}, 20);
			
		}, 40);
	}

	@Override
	public void onRun() {
		
		PermissionsModuleManager perms = getManager().getGame().getPermissionsManager();
		perms.setPvp(true);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§cPvp activé ! §6Préparez votre épée !");
			p.sendMessage("§6Vous serez régulièrement swapé toutes les §92m00s §6à §93m00s§6.");
		}
		
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
