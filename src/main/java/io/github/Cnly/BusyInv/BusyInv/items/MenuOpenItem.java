package io.github.Cnly.BusyInv.BusyInv.items;

import org.bukkit.inventory.ItemStack;

import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;

public class MenuOpenItem extends BusyItem
{
    
    private IBusyMenu newMenu;
    
    public MenuOpenItem(IBusyMenu newMenu, ItemStack look)
    {
        super(look);
        this.newMenu = newMenu;
    }
    
    public MenuOpenItem(IBusyMenu newMenu, String displayName, ItemStack icon,
            String... lores)
    {
        super(displayName, icon, lores);
        this.newMenu = newMenu;
    }
    
    public MenuOpenItem(IBusyMenu newMenu, String displayName, ItemStack icon)
    {
        super(displayName, icon);
        this.newMenu = newMenu;
    }
    
    @Override
    public void onClick(ItemClickEvent e)
    {
        if(null == this.newMenu)
            return;
        this.newMenu.openFor(e.getPlayer());
    }
    
    public IBusyMenu getnewMenu()
    {
        return newMenu;
    }
    
    public MenuOpenItem setnewMenu(IBusyMenu newMenu)
    {
        this.newMenu = newMenu;
        return this;
    }
    
}
