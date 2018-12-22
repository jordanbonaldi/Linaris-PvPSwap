package net.linaris.pvpswap.utils.biomes;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.server.v1_8_R3.BiomeBase;

public class BiomeHandler
{
    private BiomeBase[] origBiomes;
    private Random random;
    
    public BiomeHandler() {
        this.origBiomes = this.getMcBiomesCopy();
        this.random = new Random();
    }
    
    
    public void swapBiome(final Biome oldBiome, final Biome newBiome) {
        if (oldBiome.getId() != Biome.SKY.getId()) {
            final BiomeBase[] biomes = this.getMcBiomes();
            biomes[oldBiome.getId()] = this.getOrigBiome(newBiome.getId());
        }
    }
    
    
    public void swapBiome(final Biome newBiome) {
        final BiomeBase[] biomes = this.getMcBiomes();
        final BiomeBase newB = this.getOrigBiome(newBiome.getId());
        for (int i = 0; i < Biome.values().length; ++i) {
            if (i != newBiome.getId() && i != Biome.SKY.getId()) {
                biomes[i] = newB;
            }
        }
    }
    
    
    public void resetBiomes() {
        final BiomeBase[] biomes = this.getMcBiomes();
        for (final Biome b : Biome.values()) {
            biomes[b.getId()] = this.getOrigBiome(b.getId());
        }
    }
    
    
    public Biome getRandomBiome() {
        return Biome.values()[this.random.nextInt(Biome.values().length)];
    }
    
    
    public Biome fromString(final String biome) {
        for (final Biome b : Biome.values()) {
            if (b.toString().equalsIgnoreCase(biome)) {
                return b;
            }
        }
        return null;
    }
    
    private BiomeBase[] getMcBiomesCopy() {
        final BiomeBase[] b = this.getMcBiomes();
        return Arrays.copyOf(b, b.length);
    }
    
    private BiomeBase[] getMcBiomes() {
        try {
            final Field biomeF = BiomeBase.class.getDeclaredField("biomes");
            biomeF.setAccessible(true);
            return (BiomeBase[])biomeF.get(null);
        }
        catch (IllegalAccessException | NoSuchFieldException ex2) {
            ex2.printStackTrace();
            return new BiomeBase[256];
        }
    }
    
    private BiomeBase getOrigBiome(final int value) {
        return this.origBiomes[value];
    }
}
