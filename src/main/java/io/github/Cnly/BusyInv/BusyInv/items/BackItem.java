package io.github.Cnly.BusyInv.BusyInv.items;

import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;

import org.bukkit.inventory.ItemStack;

public class BackItem extends AbstractBusyItem
{

    public BackItem(ItemStack look)
    {
        super(look);
    }

    public BackItem(String displayName, ItemStack icon, String... lores)
    {
        super(displayName, icon, lores);
    }

    public BackItem(String displayName, ItemStack icon)
    {
        super(displayName, icon);
    }
    
    @Override
    public void onClick(ItemClickEvent e)
    {
        
    }
    
}
