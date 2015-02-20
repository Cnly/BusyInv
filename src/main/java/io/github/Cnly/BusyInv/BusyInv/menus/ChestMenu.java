package io.github.Cnly.BusyInv.BusyInv.menus;

import io.github.Cnly.BusyInv.BusyInv.holders.BusyHolder;
import io.github.Cnly.BusyInv.BusyInv.items.AbstractBusyItem;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ChestMenu extends BusyMenu
{
    
    public ChestMenu(String title, IBusyMenu parent, int size)
    {
        super(InventoryType.CHEST, title, parent, getVerifiedSize(size));
    }
    
    public ChestMenu(String title, IBusyMenu parent, ChestSize size)
    {
        super(InventoryType.CHEST, title, parent, size.getValue());
    }
    
    @Override
    public ChestMenu openFor(Player p)
    {
        Inventory inv = Bukkit.createInventory(new BusyHolder(this), this.size,
                this.title);
        this.applyOn(p, inv);
        p.openInventory(inv);
        return this;
    }
    
    /**
     * Set items for a chest menu in a easy way.
     * 
     * @param naturalRow
     *            The row the item should be on. 1~6(both included)
     * @param naturalColumn
     *            The column the item should be on. 1~9(both included)
     * @param item
     *            The item
     * @return itself
     */
    public ChestMenu naturalSet(int naturalRow, int naturalColumn,
            AbstractBusyItem item)
    {
        
        int rawSlot = ((naturalRow - 1) * 9 + naturalColumn) - 1;
        
        return (ChestMenu)this.setItem(rawSlot, item);
    }
    
    public static enum ChestSize
    {
        
        ONE_LINE(9),
        TWO_LINES(18),
        THREE_LINES(27),
        FOUR_LINES(36),
        FIVE_LINES(45),
        SIX_LINES(54);
        
        private final int value;
        
        private ChestSize(int value)
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return value;
        }
        
        public static ChestSize nearest(int size)
        {
            if(size <= 9)
                return ONE_LINE;
            if(size <= 18)
                return TWO_LINES;
            if(size <= 27)
                return THREE_LINES;
            if(size <= 36)
                return FOUR_LINES;
            if(size <= 45)
                return FIVE_LINES;
            else
                return SIX_LINES;
        }
        
    }
    
    @Override
    public ChestMenu setSize(int size)
    {
        return (ChestMenu)super.setSize(getVerifiedSize(size));
    }
    
    protected static int getVerifiedSize(int size)
    {
        if(size % 9 != 0)
            throw new IllegalArgumentException("size must be a multiple of 9");
        return size;
    }
    
}
