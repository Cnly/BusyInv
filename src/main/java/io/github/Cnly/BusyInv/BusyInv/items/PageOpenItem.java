package io.github.Cnly.BusyInv.BusyInv.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IPagedBusyMenu;

public class PageOpenItem extends AbstractBusyItem
{
    
    protected int newPage;
    
    public PageOpenItem(int newPage, ItemStack look)
    {
        super(look);
        this.newPage = newPage;
    }
    
    public PageOpenItem(int newPage, String displayName, ItemStack icon,
            String... lores)
    {
        super(displayName, icon, lores);
        this.newPage = newPage;
    }
    
    public PageOpenItem(int newPage, String displayName, ItemStack icon)
    {
        super(displayName, icon);
        this.newPage = newPage;
    }
    
    @Override
    public void onClick(ItemClickEvent e)
    {
        
        IPagedBusyMenu menu = (IPagedBusyMenu)e.getMenu();
        Player p = e.getPlayer();
        menu.updateFor(p, this.newPage);
        
    }
    
    public int getNewPage()
    {
        return newPage;
    }
    
    public PageOpenItem setNewPage(int newPage)
    {
        this.newPage = newPage;
        return this;
    }
    
}
