package io.github.Cnly.BusyInv.BusyInv.menus.apis;

import io.github.Cnly.BusyInv.BusyInv.holders.PagedBusyHolder;
import io.github.Cnly.BusyInv.BusyInv.items.BusyItem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IPagedBusyMenu extends IBusyMenu
{
    
    /**
     * Opens this menu for a player.
     * 
     * @param p
     *            The player
     * @param naturalPage
     *            The page to open. Starts from 1.
     * @return this
     */
    public IPagedBusyMenu openFor(Player p, int naturalPage);
    
    /**
     * Get the current page open for the player. If this menu isn't open for the
     * player, this method throws an {@code IllegalStateException}.
     * 
     * @param p
     *            The player
     * @return The page open for the player
     * @throws IllegalStateException
     *             when this menu isn't open for the player
     */
    public int getOpenPageFor(Player p) throws IllegalStateException;
    
    @Override
    public PagedBusyHolder getHolderFor(Player p);
    
    /**
     * Sets the item at the given position.
     * 
     * @param naturalPage
     *            The page. Starts from 1.
     * @param index
     *            The position on the page. Usually starts from 0.
     * @param item
     *            The item
     * @return this
     */
    public IPagedBusyMenu setItem(int naturalPage, int index, BusyItem item);
    
    /**
     * Forces this menu to apply its items on the specified page on an
     * inventory. If you want to ensure the inventory was opened for a player,
     * use {@link IPagedBusyMenu#updateFor(Player, int)}.
     * 
     * @see {@link IPagedBusyMenu#updateFor(Player, int)}
     * 
     * @param p
     *            The Player used for {@link BusyItem#getLookFor(Player)}
     * @param inv
     *            The inventory
     * @param naturalPage
     *            The page to get items from. Starts from 1.
     * @return this
     */
    public IPagedBusyMenu applyOn(Player p, Inventory inv, int naturalPage);
    
    /**
     * If THIS menu instance was opened for the target player, this method calls
     * {@link IPagedBusyMenu#applyOn(Player, Inventory, int)} for the player.
     * 
     * @see {@link IPagedBusyMenu#applyOn(Player, Inventory, int)}
     * 
     * @param p
     *            Target player
     * @param naturalPage
     *            The page used for calling
     *            {@link IPagedBusyMenu#applyOn(Player, Inventory, int)}
     * @return true if and only if the
     *         {@link IPagedBusyMenu#applyOn(Player, Inventory, int)} method is
     *         called. Otherwise false(for example the player didn't open;
     *         inventory or the inventory isn't a menu; or the menu the player
     *         opened isn't this one)
     */
    public boolean updateFor(Player p, int naturalPage);
    
    /**
     * Fills all empty slots on the given page with the given item.
     * 
     * @param naturalPage
     *            The page. Starts from 1.
     * @param item
     *            The item
     * @return this
     */
    public IPagedBusyMenu fillEmptySlots(int naturalPage, BusyItem item);
    
    /**
     * Opens the first page of this menu for a player.
     * 
     * @param p
     *            The player
     * @return this
     */
    @Override
    public IPagedBusyMenu openFor(Player p);
    
    /**
     * Forces this menu to apply its items on an inventory. If this menu is open
     * for a player, this method will call
     * {@link IPagedBusyMenu#applyOn(Player, Inventory, int)} with the currently
     * page as the page parameter. Otherwise it will use {@code 1} as the page
     * parameter.<br/>
     * If you want to ensure the inventory has been opened for a player, use
     * {@link IPagedBusyMenu#updateFor(Player)}.
     * 
     * @see {@link IPagedBusyMenu#updateFor(Player)}
     * 
     * @param p
     *            The Player used for {@link BusyItem#getLookFor(Player)}
     * @param inv
     *            The inventory
     * @return this
     */
    @Override
    public IPagedBusyMenu applyOn(Player p, Inventory inv);
    
    /**
     * If THIS menu instance was opened for the target player, this method calls
     * {@link IPagedBusyMenu#applyOn(Player, Inventory)} for the player.
     * 
     * @see {@link IPagedBusyMenu#applyOn(Player, Inventory)}
     * 
     * @param p
     *            Target player
     * @return true if and only if the
     *         {@link IBusyMenu#applyOn(Player, Inventory)} method is called.
     *         Otherwise false(for example the player didn't open; inventory or
     *         the inventory isn't a menu; or the menu the player opened isn't
     *         this one)
     */
    @Override
    public boolean updateFor(Player p);
    
}
