package net.linaris.pvpswap.heads;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.linaris.pvpswap.heads.Head.HeadAction;
import net.linaris.pvpswap.utils.MathUtils;

public class ExpelliarmusShieldHead extends HeadAction {

    @Override
    public void onDamage(Player player, Player damager) {
        player.sendMessage("Votre bouclier vous a protégé de l'attaque !");
        damager.sendMessage(ChatColor.RED + "Expelliarmus !!");
        ItemStack itemStack = damager.getItemInHand();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            damager.setItemInHand(null);
            Item item = damager.getWorld().dropItem(damager.getLocation(), itemStack);
            item.setVelocity(new Vector(MathUtils.random(0.6F), MathUtils.random(0.4F), MathUtils.random(0.6F)));
        }
    }

    @Override
    public void onRun(Player player) {}
}
