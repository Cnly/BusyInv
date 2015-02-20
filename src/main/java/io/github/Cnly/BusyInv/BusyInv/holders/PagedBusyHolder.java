package io.github.Cnly.BusyInv.BusyInv.holders;

import io.github.Cnly.BusyInv.BusyInv.menus.apis.IPagedBusyMenu;

public class PagedBusyHolder extends BusyHolder
{
    
    private int currentNaturalPage;
    
    public PagedBusyHolder(IPagedBusyMenu menu)
    {
        super(menu);
    }
    
    public int getCurrentNaturalPage()
    {
        return currentNaturalPage;
    }

    public PagedBusyHolder setCurrentNaturalPage(int currentNaturalPage)
    {
        this.currentNaturalPage = currentNaturalPage;
        return this;
    }

}
