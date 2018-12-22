package net.linaris.pvpswap.utils;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;



public class SoundUtils {

    
    public static void broadcastSound(Sound sound) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), sound, 1, 1);
        }
    }

    
    public static void broadcastSound(Sound sound, int volume) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getPlayer().getPlayer().getLocation(), sound, volume, 1);
        }
    }

    
    public static void broadcastSound(Sound sound, Location loc)  {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(loc, sound, 1, 1);
        }
    }

    
    public static void broadcastSound(Sound sound, Location loc, float volume)  {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(loc, sound, volume, 1);
        }
    }



    
    public static void broadcastSound(Sound sound, SoundSource source, int distance, int volume) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(source.getLocation(source, player.getLocation(), distance), sound, volume, 1);
        }
    }

    public enum SoundSource {
        BEHIND(),
        FRONT(),
        ONPLAYER();

        
        public static Location getLocation(SoundSource type, Location source, int distance) {
            return type == BEHIND ? source.setDirection(source.getDirection().normalize().multiply(-distance)) :
                        type == FRONT ? source.setDirection(source.getDirection().normalize().multiply(distance)) :
                             type == ONPLAYER ? source :
                                    null;
        }
    }
}
