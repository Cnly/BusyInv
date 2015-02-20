package io.github.Cnly.BusyInv.BusyInv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils
{
    
    private ItemUtils()
    {
        throw new AssertionError("This is a util class");
    }
    
    public static List<String> getLores(ItemStack item)
    {
        
        if(!item.hasItemMeta())
            return Collections.emptyList();
        
        ItemMeta meta = item.getItemMeta();
        
        if(!meta.hasLore())
            return Collections.emptyList();
        
        return meta.getLore();
    }
    
    public static ItemStack addLore(ItemStack item, String lore)
    {
        
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.hasLore() ? meta.getLore()
                : new ArrayList<String>();
        lores.add(lore);
        meta.setLore(lores);
        item.setItemMeta(meta);
        
        return item;
    }
    
    public static ItemStack setLores(ItemStack item, List<String> lores)
    {
        
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        item.setItemMeta(meta);
        
        return item;
    }
    
    public static String getDisplayName(ItemStack item)
    {
        
        ItemMeta meta = item.getItemMeta();
        
        if(!meta.hasDisplayName())
            return null;
        
        return meta.getDisplayName();
    }
    
    public static ItemStack setDisplayName(ItemStack item, String displayName)
    {
        
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        
        return item;
    }
    
}
