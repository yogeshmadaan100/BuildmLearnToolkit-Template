package com.imlearn.design.models;

import android.graphics.drawable.Drawable;

/**
 * @author amulya
 * @datetime 17 Oct 2014, 3:50 PM
 */
public class DataItem {

    private String name;
    private String info;

    private Drawable drawable;

    private int navigationInfo;

    public DataItem(String name,String info, Drawable drawable, int navigationInfo) {
        this.name= name;
        this.info=info;
        this.drawable = drawable;
        this.navigationInfo = navigationInfo;
    }

    public String getName() {
        return name;
    }
    public String getInfo()
    {
    	return info;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public int getNavigationInfo() {
        return navigationInfo;
    }
}
