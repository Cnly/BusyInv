package io.github.Cnly.BusyInv.BusyInv.apis;

import org.bukkit.entity.Player;

public interface IOpenable
{
    
    /**
     * Opens this menu for a player.
     * 
     * @param p
     *            The player
     * @return this
     */
    public IOpenable openFor(Player p);
    
    /**
     * Checks if THIS menu instance was opened for the specified player.
     * 
     * @param p
     *            The player to check
     * @return true if and only if this menu instance was opened for the
     *         specified player. Otherwise false.
     */
    public boolean isOpenFor(Player p);
    
}
