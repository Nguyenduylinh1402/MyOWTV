package com.example.steve.ownslidingmenu.DataItem;

/**
 * Created by Steve on 8/10/2015.
 */
public class SectionItem implements Item {
    private final String title;

    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
