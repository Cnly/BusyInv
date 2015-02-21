package io.github.Cnly.BusyInv.BusyInv.apis;

import org.bukkit.entity.Player;

public interface IParented
{
    
    /**
     * Gets the parent window. Can be null if there isn't one.
     * 
     * @return The parent
     */
    public IOpenable getParent();
    
    /**
     * Opens the parent window if there is one.
     * 
     * @param p
     *            The player
     * @return The parent menu if there is one; or null if there isn't one.
     */
    public IOpenable openParentFor(Player p);
    
}
