package io.github.Cnly.BusyInv.BusyInv.events;

import io.github.Cnly.BusyInv.BusyInv.menus.apis.IBusyMenu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ItemClickEvent
{
    
    private final Player player;
    private final IBusyMenu menu;
    private final ClickType clickType;
    private final int hotbarButton;
    /**
     * Sets if the parent window will be opened after clicking this item
     */
    private boolean openParent;
    /**
     * Sets if the current window should be closed when there's no parent
     * window(Used with {@link ItemClickEvent#openParent})
     */
    private boolean closeOnNoParent;
    private boolean closeDirectly;
    private boolean reloadMenu;
    
    public ItemClickEvent(Player player, IBusyMenu menu, ClickType clickType,
            int hotbarButton, boolean openParent, boolean closeOnNoParent,
            boolean closeDirectly, boolean reloadMenu)
    {
        super();
        this.player = player;
        this.menu = menu;
        this.clickType = clickType;
        this.hotbarButton = hotbarButton;
        this.openParent = openParent;
        this.closeOnNoParent = closeOnNoParent;
        this.closeDirectly = closeDirectly;
        this.reloadMenu = reloadMenu;
    }

    public ItemClickEvent(Player player, IBusyMenu menu, ClickType clickType,
            int hotbarButton)
    {
        super();
        this.player = player;
        this.menu = menu;
        this.clickType = clickType;
        this.hotbarButton = hotbarButton;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public IBusyMenu getMenu()
    {
        return menu;
    }
    
    public ClickType getClickType()
    {
        return clickType;
    }
    
    public int getHotbarButton()
    {
        return hotbarButton;
    }
    
    public boolean willOpenParent()
    {
        return openParent;
    }
    
    public void setOpenParent(boolean openParent)
    {
        this.openParent = openParent;
    }
    
    public boolean willCloseOnNoParent()
    {
        return closeOnNoParent;
    }
    
    public void setCloseOnNoParent(boolean closeOnNoParent)
    {
        this.closeOnNoParent = closeOnNoParent;
    }
    
    public boolean willCloseDirectly()
    {
        return closeDirectly;
    }
    
    public void setCloseDirectly(boolean closeDirectly)
    {
        this.closeDirectly = closeDirectly;
    }
    
    public boolean willReloadMenu()
    {
        return reloadMenu;
    }
    
    public void setReloadMenu(boolean reloadMenu)
    {
        this.reloadMenu = reloadMenu;
    }
    
}
