package io.github.Cnly.BusyInv.BusyInv;

import io.github.Cnly.BusyInv.BusyInv.listeners.BusyListener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class BusyInv extends JavaPlugin
{
    
    private static BusyInv instance;
    
    @Override
    public void onEnable()
    {
        
        instance = this;
        
        Bukkit.getPluginManager().registerEvents(new BusyListener(), this);
        
    }
    
    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll(this);
    }
    
    public static void registerFor(JavaPlugin jp)
    {
        Bukkit.getPluginManager().registerEvents(new BusyListener(), jp);
    }
    
    public static BusyInv getInstance()
    {
        return instance;
    }
    
}