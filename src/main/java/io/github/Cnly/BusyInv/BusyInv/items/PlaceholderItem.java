package io.github.Cnly.BusyInv.BusyInv.items;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlaceholderItem extends BusyItem
{

    @SuppressWarnings("deprecation")
    public PlaceholderItem()
    {
        super("", new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
    }
    
}
