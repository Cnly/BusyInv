package io.github.Cnly.BusyInv.BusyInv.items;

import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;

import org.bukkit.inventory.ItemStack;

public class NonFunctionalItem extends AbstractBusyItem
{
    
    public NonFunctionalItem(ItemStack look)
    {
        super(look);
    }
    
    public NonFunctionalItem(String displayName, ItemStack icon,
            String... lores)
    {
        super(displayName, icon, lores);
    }
    
    public NonFunctionalItem(String displayName, ItemStack icon)
    {
        super(displayName, icon);
    }
    
    @Override
    public void onClick(ItemClickEvent e)
    {
    }
    
}
