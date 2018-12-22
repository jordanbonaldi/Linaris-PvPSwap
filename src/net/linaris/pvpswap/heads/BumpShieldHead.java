package net.linaris.pvpswap.heads;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.linaris.pvpswap.heads.Head.HeadAction;
import net.linaris.pvpswap.utils.MathUtils;

public class BumpShieldHead extends HeadAction {

    @Override
    public void onDamage(Player player, Player damager) {
        player.sendMessage("Votre bouclier vous a protégé de l'attaque !");
        damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
        damager.setVelocity(new Vector(MathUtils.random(0.8F), MathUtils.random(0.8F) + 0.6F, MathUtils.random(0.8F)));
    }

    @Override
    public void onRun(Player player) {}
}
