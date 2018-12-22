package net.linaris.pvpswap.utils.schematics;
public class Schematic
{
 
    private short[] blocks;
    private byte[] data;
    private short width;
    private short lenght;
    private short height;
 
    public Schematic(short[] blocks2, byte[] data, short width, short lenght, short height)
    {
        this.blocks = blocks2;
        this.data = data;
        this.width = width;
        this.lenght = lenght;
        this.height = height;
    }
 
    
    public short[] getBlocks()
    {
        return blocks;
    }
 
    
    public byte[] getData()
    {
        return data;
    }
 
    
    public short getWidth()
    {
        return width;
    }
 
    
    public short getLenght()
    {
        return lenght;
    }
 
    
    public short getHeight()
    {
        return height;
    }
}