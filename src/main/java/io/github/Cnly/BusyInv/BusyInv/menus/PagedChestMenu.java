package io.github.Cnly.BusyInv.BusyInv.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

import io.github.Cnly.BusyInv.BusyInv.apis.IOpenable;
import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;
import io.github.Cnly.BusyInv.BusyInv.holders.PagedBusyHolder;
import io.github.Cnly.BusyInv.BusyInv.items.AbstractBusyItem;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IPagedBusyMenu;

public class PagedChestMenu extends ChestMenu implements IPagedBusyMenu
{
    
    protected static final int ITEMS_PER_LINE = 9;
    
    protected int perPageSize;
    protected int perPageLineCount;
    protected int pages;
    
    public PagedChestMenu(String title, IOpenable parent,
            ChestSize perPageSize, int pages)
    {
        this(title, parent, perPageSize.getValue(), pages);
    }
    
    public PagedChestMenu(String title, IOpenable parent, int perPageSize,
            int pages)
    {
        super(title, parent, perPageSize * pages);
        this.perPageSize = perPageSize;
        this.pages = pages;
        this.perPageLineCount = perPageSize / 9;
    }
    
    public PagedChestMenu naturalSet(int naturalPage, int naturalRow,
            int naturalColumn, AbstractBusyItem item)
    {
        return (PagedChestMenu)super.naturalSet(
                ((naturalPage - 1) * perPageLineCount) + naturalRow,
                naturalColumn, item);
    }
    
    @Override
    public void onMenuClick(InventoryClickEvent e)
    {
        
        Player p = (Player)e.getWhoClicked();
        PagedBusyHolder holder = (PagedBusyHolder)e.getInventory().getHolder();
        int currentNaturalPage = holder.getCurrentNaturalPage();
        
        int rawSlot = e.getRawSlot();
        if(rawSlot < 0 || rawSlot >= this.perPageSize)
            return;
        int itemSlot = ((currentNaturalPage - 1) * this.perPageSize)
                + rawSlot;
        AbstractBusyItem bi = this.items[itemSlot];
        if(null == bi)
            return;
        ItemClickEvent ice = new ItemClickEvent(p, this, e.getClick(),
                e.getHotbarButton());
        bi.onClick(ice);
        
        if(ice.willCloseDirectly())
        {
            // The following is a magic
            if(rawSlot <= 44)
                p.closeInventory();
            else
                this.closeInventorySafely(p);
            return;
        }
        
        if(ice.willOpenParent())
        {
            IOpenable parentWindow = this.openParentFor(p);
            if(null == parentWindow && ice.willCloseOnNoParent())
                // The following is a magic
                if(rawSlot <= 44)
                    p.closeInventory();
                else
                    this.closeInventorySafely(p);
            return;
        }
        
        if(ice.willReloadMenu())
        {
            reloadFor(p);
        }
        
    }
    
    @Override
    public PagedChestMenu setItem(int naturalPage, int index,
            AbstractBusyItem item)
    {
        this.setItem(getItemStart(naturalPage) + index, item);
        return this;
    }
    
    @Override
    public PagedChestMenu openFor(Player p)
    {
        return this.openFor(p, 1);
    }
    
    @Override
    public PagedChestMenu openFor(Player p, int naturalPage)
    {
        Inventory inv = Bukkit.createInventory(new PagedBusyHolder(this),
                this.perPageSize, this.title);
        this.applyOn(p, inv, naturalPage);
        p.openInventory(inv);
        return this;
    }
    
    @Override
    public PagedChestMenu applyOn(Player p, Inventory inv)
    {
        return this.applyOn(p, inv, 1);
    }
    
    @Override
    public PagedChestMenu applyOn(Player p, Inventory inv, int naturalPage)
    {
        PagedBusyHolder holder = (PagedBusyHolder)inv.getHolder();
        holder.setCurrentNaturalPage(naturalPage);
        
        int itemStart = getItemStart(naturalPage);
        int itemEnd = itemStart + this.perPageSize;
        int invIndex = 0;
        for(int i = itemStart; i < itemEnd; i++)
        {
            AbstractBusyItem bi = this.items[i];
            if(null != bi)
                inv.setItem(invIndex, bi.getLookFor(p));
            else
                inv.setItem(invIndex, null);
            
            invIndex++;
        }
        return this;
    }
    
    public PagedChestMenu fillEmptySlots(int naturalPage)
    {
        this.fillEmptySlots(naturalPage, this.emptyItem);
        return this;
    }
    
    @Override
    public PagedChestMenu fillEmptySlots(int naturalPage, AbstractBusyItem item)
    {
        int itemStart = this.getItemStart(naturalPage);
        int itemEnd = itemStart + this.perPageSize;
        for(int i = itemStart; i < itemEnd; i++)
            if(null == this.items[i])
                this.items[i] = item;
        return this;
    }
    
    @Override
    public boolean updateFor(Player p, int naturalPage)
    {
        InventoryView iview = p.getOpenInventory();
        if(null == iview)
            return false;
        Inventory inv = iview.getTopInventory();
        if(null == inv)
            return false;
        InventoryHolder holder = inv.getHolder();
        if(!(holder instanceof PagedBusyHolder))
            return false;
        IBusyMenu menu = ((PagedBusyHolder)holder).getMenu();
        if(!menu.equals(this))
            return false;
        ((PagedChestMenu)menu).applyOn(p, inv, naturalPage);
        p.updateInventory();
        return true;
    }
    
    public int getPerPageSize()
    {
        return perPageSize;
    }
    
    public PagedChestMenu setPerPageSize(int perPageSize)
    {
        this.perPageSize = getVerifiedSize(perPageSize);
        this.perPageLineCount = perPageSize / 9;
        this.size = this.perPageSize * this.pages;
        return this;
    }
    
    public int getPerPageLineCount()
    {
        return perPageLineCount;
    }
    
    public PagedChestMenu setPerPageLineCount(int perPageLineCount)
    {
        if(perPageLineCount <= 0 || perPageLineCount >= 6)
            throw new IllegalArgumentException(
                    "perPageLineCount must be between 1 and 6(both included)");
        this.perPageLineCount = perPageLineCount;
        this.perPageSize = perPageLineCount * ITEMS_PER_LINE;
        this.size = this.perPageSize * this.pages;
        return this;
    }
    
    public int getPages()
    {
        return pages;
    }
    
    public PagedChestMenu setPages(int pages)
    {
        this.pages = pages;
        this.size = this.perPageSize * this.pages;
        return this;
    }
    
    private int getItemStart(int naturalPage)
    {
        return (naturalPage - 1) * perPageSize;
    }
    
}