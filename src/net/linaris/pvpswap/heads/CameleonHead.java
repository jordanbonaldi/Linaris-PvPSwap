package net.linaris.pvpswap.heads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.icrotz.gameapi.Game;
import net.linaris.pvpswap.heads.Head.HeadAction;

public class CameleonHead extends HeadAction {

    @Override
    public void onDamage(final Player player, final Player damager) {
        damager.sendMessage("Et pouf ! Disparu !");
        Location playerLoc = player.getLocation();
        damager.playSound(playerLoc, Sound.FIREWORK_BLAST, 1, 0.8F);
        player.playSound(playerLoc, Sound.FIREWORK_BLAST, 1, 0.8F);
        player.sendMessage("Vos réfléxes de caméléon vous rendent temporairement invisible. Profitez en !");
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online != player) {
                online.hidePlayer(player);
            }
        }
        final CameleonListener listener = new CameleonListener(player, damager, false);
        Bukkit.getPluginManager().registerEvents(listener, Game.getGame());
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!listener.damage) {
                    CameleonHead.this.resetCameleonEffect(listener, player, damager);
                }
            }
        }.runTaskLater(Game.getGame(), 60);
    }

    private void resetCameleonEffect(CameleonListener listener, Player player, Player damager) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online != player) {
                online.showPlayer(player);
            }
        }
        player.sendMessage("*Pof*");
        player.sendMessage(ChatColor.RED + "Fin de l'invisibilité !!");
        damager.sendMessage("*Pof*");
        damager.sendMessage(ChatColor.RED + "Fin de l'invisibilité !!");
        HandlerList.unregisterAll(listener);
    }

    public class CameleonListener implements Listener {
        private Player player;
        private Player damager;
        private boolean damage;
        
        public CameleonListener(Player player,Player damager, boolean damage) {
			this.player = player;
			this.damager = damager;
			this.damage = damage;
		}

        @EventHandler
        public void onPlayerDamageByPlayer(EntityDamageByEntityEvent evt) {
            if (evt.getEntity() instanceof Player && (evt.getDamager() == player || evt.getDamager() instanceof Projectile && ((Projectile) evt.getDamager()).getShooter() == player)) {
                CameleonHead.this.resetCameleonEffect(this, player, damager);
                damage = true;
            }
        }
    }

    @Override
    public void onRun(Player player) {}
}
