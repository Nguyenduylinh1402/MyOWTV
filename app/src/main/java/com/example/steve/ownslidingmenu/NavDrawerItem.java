package com.example.steve.ownslidingmenu;

import com.example.steve.ownslidingmenu.DataItem.Item;

/**
 * Created by Steve on 8/9/2015.
 */
public class NavDrawerItem implements Item{
    private String title;
    private int icon;

    public NavDrawerItem() {
    }

    public NavDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }
    public NavDrawerItem(String title){
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
