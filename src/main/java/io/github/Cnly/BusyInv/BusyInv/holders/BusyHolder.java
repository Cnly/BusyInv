package io.github.Cnly.BusyInv.BusyInv.holders;

import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BusyHolder implements InventoryHolder
{
    
    private final IBusyMenu menu;
    
    public BusyHolder(IBusyMenu menu)
    {
        this.menu = menu;
    }
    
    public IBusyMenu getMenu()
    {
        return this.menu;
    }
    
    @Override
    public Inventory getInventory()
    {
        return null;
    }
    
}
