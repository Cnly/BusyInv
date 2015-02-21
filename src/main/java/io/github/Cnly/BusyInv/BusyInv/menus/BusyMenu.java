package io.github.Cnly.BusyInv.BusyInv.menus;

import io.github.Cnly.BusyInv.BusyInv.BusyInv;
import io.github.Cnly.BusyInv.BusyInv.apis.IOpenable;
import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;
import io.github.Cnly.BusyInv.BusyInv.holders.BusyHolder;
import io.github.Cnly.BusyInv.BusyInv.items.AbstractBusyItem;
import io.github.Cnly.BusyInv.BusyInv.items.NonFunctionalItem;
import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BusyMenu implements IBusyMenu
{
    
    @SuppressWarnings("deprecation")
    protected AbstractBusyItem emptyItem = new NonFunctionalItem(new ItemStack(
            Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()))
            .setDisplayName("");
    
    protected InventoryType inventoryType;
    protected String title;
    protected IOpenable parent;
    protected int size;
    protected AbstractBusyItem[] items;
    
    /**
     * This constructor uses
     * {@link org.bukkit.event.inventory.InventoryType#getDefaultSize()} as
     * size. If you want to create a chest inventory which has more than 3
     * lines, you should NOT use this constructor. Use
     * {@link BusyMenu#BusyMenu(InventoryType, String, IBusyMenu, int)} or
     * {@link ChestMenu}(recommended) instead.
     * 
     * @param inventoryType
     *            One of {@link org.bukkit.event.inventory.InventoryType}
     * @param title
     *            The title
     * @param parent
     *            The parent window. Can be null.
     */
    public BusyMenu(InventoryType inventoryType, String title, IOpenable parent)
    {
        super();
        this.inventoryType = inventoryType;
        this.title = title;
        this.parent = parent;
        this.size = inventoryType.getDefaultSize();
        this.items = new AbstractBusyItem[this.size];
    }
    
    /**
     * This constructor uses a custom size as the inventory's size.
     * 
     * @param inventoryType
     *            One of {@link org.bukkit.event.inventory.InventoryType}
     * @param title
     *            The title
     * @param parent
     *            The parent window. Can be null.
     * @param size
     *            The inventory's size
     */
    public BusyMenu(InventoryType inventoryType, String title,
            IOpenable parent, int size)
    {
        super();
        this.inventoryType = inventoryType;
        this.title = title;
        this.parent = parent;
        this.size = size;
        this.items = new AbstractBusyItem[this.size];
    }
    
    @Override
    public void onMenuClick(InventoryClickEvent e)
    {
        
        Player p = (Player)e.getWhoClicked();
        
        int rawSlot = e.getRawSlot();
        if(rawSlot < 0 || rawSlot >= this.size)
            return;
        
        AbstractBusyItem bi = this.items[rawSlot];
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
            IOpenable parentMenu = this.openParentFor(p);
            if(null == parentMenu && ice.willCloseOnNoParent())
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
            return;
        }
        
    }
    
    @Override
    public void onMenuClose(InventoryCloseEvent e)
    {
    }
    
    @Override
    public BusyMenu setItem(int index, AbstractBusyItem item)
    {
        if(index > this.size - 1)
            throw new IllegalArgumentException(String.format(
                    "Index %s is larger than max index which is %s !", index,
                    this.size - 1));
        this.items[index] = item;
        return this;
    }
    
    @Override
    public BusyMenu openFor(Player p)
    {
        Inventory inv = Bukkit.createInventory(new BusyHolder(this),
                this.inventoryType, this.title);
        this.applyOn(p, inv);
        p.openInventory(inv);
        return this;
    }
    
    @Override
    public BusyMenu applyOn(Player p, Inventory inv)
    {
        for(int i = 0; i < this.size; i++)
        {
            AbstractBusyItem bi = this.items[i];
            
            if(null != bi)
                inv.setItem(i, bi.getLookFor(p));
            else
                inv.setItem(i, null);
        }
        return this;
    }
    
    @Override
    public BusyMenu fillEmptySlots(AbstractBusyItem item)
    {
        for(int i = 0; i < this.size; i++)
            if(null == this.items[i])
                this.items[i] = item;
        return this;
    }
    
    public BusyMenu fillEmptySlots()
    {
        this.fillEmptySlots(this.emptyItem);
        return this;
    }
    
    /**
     * If a BusyMenu is opened for the target player, this method will reload
     * the menu for the player.
     * 
     * @see {@link IBusyMenu#reload}
     * 
     * @param p
     *            Target player
     * @return true if and only if the menu is reloaded. Otherwise false(for
     *         example the player didn't open an inventory or the inventory
     *         isn't a menu)
     */
    public static boolean reloadFor(Player p)
    {
        InventoryView iview = p.getOpenInventory();
        if(null == iview)
            return false;
        Inventory inv = iview.getTopInventory();
        if(null == inv)
            return false;
        InventoryHolder holder = inv.getHolder();
        if(!(holder instanceof BusyHolder))
            return false;
        ((BusyHolder)holder).getMenu().updateFor(p);
        p.updateInventory();
        return true;
    }
    
    @Override
    public boolean updateFor(Player p)
    {
        InventoryView iview = p.getOpenInventory();
        if(null == iview)
            return false;
        Inventory inv = iview.getTopInventory();
        if(null == inv)
            return false;
        InventoryHolder holder = inv.getHolder();
        if(!(holder instanceof BusyHolder))
            return false;
        IBusyMenu menu = ((BusyHolder)holder).getMenu();
        if(!menu.equals(this))
            return false;
        menu.applyOn(p, inv);
        p.updateInventory();
        return true;
    }
    
    @Override
    public boolean isOpenFor(Player p)
    {
        InventoryView iview = p.getOpenInventory();
        if(null == iview)
            return false;
        Inventory inv = iview.getTopInventory();
        if(null == inv)
            return false;
        InventoryHolder holder = inv.getHolder();
        if(!(holder instanceof BusyHolder))
            return false;
        IBusyMenu menu = ((BusyHolder)holder).getMenu();
        if(!menu.equals(this))
            return false;
        return true;
    }
    
    @Override
    public IOpenable openParentFor(Player p)
    {
        if(null == this.parent)
            return null;
        return this.parent.openFor(p);
    }
    
    @Override
    public IOpenable getParent()
    {
        return parent;
    }
    
    public IOpenable setParent(IOpenable parent)
    {
        this.parent = parent;
        return this;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public BusyMenu setTitle(String title)
    {
        this.title = title;
        return this;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public BusyMenu setSize(int size)
    {
        this.size = size;
        return this;
    }
    
    public AbstractBusyItem[] getItems()
    {
        return items;
    }
    
    public BusyMenu setItems(AbstractBusyItem[] items)
    {
        if(items.length != this.size)
            throw new IllegalArgumentException("items.length != this.size");
        this.items = items;
        return this;
    }
    
    protected void closeInventorySafely(final Player p)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                p.closeInventory();
            }
        }.runTask(BusyInv.getInstance());
    }
    
}
