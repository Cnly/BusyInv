package io.github.Cnly.BusyInv.BusyInv.menus.apis;

import io.github.Cnly.BusyInv.BusyInv.apis.IOpenable;
import io.github.Cnly.BusyInv.BusyInv.apis.IParented;
import io.github.Cnly.BusyInv.BusyInv.holders.BusyHolder;
import io.github.Cnly.BusyInv.BusyInv.items.BusyItem;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public interface IBusyMenu extends IOpenable, IParented
{
    
    public void onMenuClick(InventoryClickEvent e);
    
    public void onMenuClose(InventoryCloseEvent e);
    
    /**
     * Gets the BusyHolder of the BusyMenu open for the player.
     * 
     * @param p
     *            The player
     * @return the BusyHolder, or null when this menu isn't open for that
     *         player.
     */
    public BusyHolder getHolderFor(Player p);
    
    /**
     * Sets the item at the given position. Usually starts from 0.
     * 
     * @param index
     *            The absolute position
     * @param item
     *            The item
     * @return this
     */
    public IBusyMenu setItem(int index, BusyItem item);
    
    /**
     * Forces this menu to apply its items on an inventory. If you want to
     * ensure the inventory was opened for a player, use
     * {@link IBusyMenu#updateFor(Player)}.
     * 
     * @see {@link IBusyMenu#updateFor(Player)}
     * 
     * @param p
     *            The Player used for {@link BusyItem#getLookFor(Player)}
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
     * Fills all empty slots with the given item.
     * 
     * @param item
     *            The item
     * @return this
     */
    public IBusyMenu fillEmptySlots(BusyItem item);
    
    @Override
    public IBusyMenu openFor(Player p);
    
}
