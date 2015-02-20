package io.github.Cnly.BusyInv.BusyInv.menus.apis;

import io.github.Cnly.BusyInv.BusyInv.items.AbstractBusyItem;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public interface IBusyMenu
{
    
    public void onMenuClick(InventoryClickEvent e);
    
    public void onMenuClose(InventoryCloseEvent e);
    
    /**
     * Sets the item at the given position. Usually starts from 0.
     * 
     * @param index
     *            The absolute position
     * @param item
     *            The item
     * @return this
     */
    public IBusyMenu setItem(int index, AbstractBusyItem item);
    
    /**
     * Opens this menu for a player.
     * 
     * @param p
     *            The player
     * @return this
     */
    public IBusyMenu openFor(Player p);
    
    /**
     * Forces this menu to apply its items on an inventory. If you want to
     * ensure the inventory was opened for a player, use
     * {@link IBusyMenu#updateFor(Player)}.
     * 
     * @see {@link IBusyMenu#updateFor(Player)}
     * 
     * @param p
     *            The Player used for
     *            {@link AbstractBusyItem#getLookFor(Player)}
     * @param inv
     *            The inventory
     * @return this
     */
    public IBusyMenu applyOn(Player p, Inventory inv);
    
    /**
     * If THIS menu instance was opened for the target player, this method calls
     * {@link IBusyMenu#applyOn(Player, Inventory)} for the player.
     * 
     * @see {@link IBusyMenu#applyOn(Player, Inventory)}
     * 
     * @param p
     *            Target player
     * @return true if and only if the
     *         {@link IBusyMenu#applyOn(Player, Inventory)} method is called.
     *         Otherwise false(for example the player didn't open; inventory or
     *         the inventory isn't a menu; or the menu the player opened isn't
     *         this one)
     */
    public boolean updateFor(Player p);
    
    /**
     * Checks if THIS menu instance was opened for the specified player.
     * 
     * @param p
     *            The player to check
     * @return true if and only if this menu instance was opened for the
     *         specified player. Otherwise false.
     */
    public boolean isOpenFor(Player p);
    
    /**
     * Fills all empty slots with the given item.
     * 
     * @param item
     *            The item
     * @return this
     */
    public IBusyMenu fillEmptySlots(AbstractBusyItem item);
    
    /**
     * Gets the parent menu. Can be null if there isn't one.
     * 
     * @return The parent
     */
    public IBusyMenu getParent();
    
    /**
     * Opens the parent menu if there is one.
     * 
     * @param p
     *            The player
     * @return The parent menu if there is one; or null if there isn't one.
     */
    public IBusyMenu openParentFor(Player p);
    
}
