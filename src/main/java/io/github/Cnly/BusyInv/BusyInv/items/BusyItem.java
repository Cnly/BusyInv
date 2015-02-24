package io.github.Cnly.BusyInv.BusyInv.items;

import io.github.Cnly.BusyInv.BusyInv.ItemUtils;
import io.github.Cnly.BusyInv.BusyInv.events.ItemClickEvent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BusyItem
{
    
    protected ItemStack look;
    
    /**
     * Note: This constructor will add {@code ChatColor.RESET} before
     * {@code displayName} and every line of lore.
     * 
     * @param displayName
     *            The display name of the icon. Will use the icon's display name
     *            when this is null.
     * @param icon
     *            The icon
     * @param lores
     *            The lore of the icon. Will use the icon's lore when this is
     *            null.
     */
    public BusyItem(String displayName, ItemStack icon, String... lores)
    {
        
        this.look = icon.clone();
        
        ItemMeta meta = this.look.getItemMeta();
        boolean changed = false;
        if(null != displayName)
        {
            meta.setDisplayName(ChatColor.RESET + displayName);
            changed = true;
        }
        if(null != lores)
        {
            ArrayList<String> loreList = new ArrayList<>();
            for(String lore : lores)
            {
                loreList.add(ChatColor.RESET + lore);
            }
            meta.setLore(loreList);
            changed = true;
        }
        
        if(changed)
            this.look.setItemMeta(meta);
        
    }
    
    public BusyItem(String displayName, ItemStack icon)
    {
        this(displayName, icon, (String[])null);
    }
    
    public BusyItem(ItemStack look)
    {
        this.look = look.clone();
    }
    
    public void onClick(ItemClickEvent e)
    {
    }
    
    public ItemStack getLookFor(Player p)
    {
        return this.look.clone();
    }
    
    /**
     * Get a clone of the look.
     * 
     * @return
     */
    public ItemStack getLook()
    {
        return this.look.clone();
    }
    
    public List<String> getLores()
    {
        return ItemUtils.getLores(this.look);
    }
    
    /**
     * Note: This method will add {@code ChatColor.RESET} before every line of
     * lore.
     * 
     * @param lore
     *            The lore to add
     * @return this
     */
    public BusyItem addLore(String lore)
    {
        ItemUtils.addLore(this.look, ChatColor.RESET + lore);
        return this;
    }
    
    /**
     * Note: This method does NOT add {@code ChatColor.RESET} before every line
     * of lore.
     * 
     * @param lores
     *            The lore to set
     * @return this
     */
    public BusyItem setLores(List<String> lores)
    {
        ItemUtils.setLores(this.look, lores);
        return this;
    }
    
    public String getDisplayName()
    {
        return ItemUtils.getDisplayName(this.look);
    }
    
    /**
     * Note: This method will add {@code ChatColor.RESET} before
     * {@code displayName}.
     * 
     * @param displayName
     *            The display name to set
     * @return this
     */
    public BusyItem setDisplayName(String displayName)
    {
        ItemUtils.setDisplayName(this.look, ChatColor.RESET + displayName);
        return this;
    }
    
    public BusyItem setLook(ItemStack look)
    {
        this.look = look.clone();
        return this;
    }
    
    public BusyItem setIcon(ItemStack icon)
    {
        
        ItemStack tmp = icon.clone();
        ItemUtils.setDisplayName(tmp, this.getDisplayName());
        ItemUtils.setLores(tmp, this.getLores());
        
        this.look = tmp;
        
        return this;
    }
    
}
