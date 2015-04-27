package oss.fruct.org.fishing;


import oss.fruct.org.fishing.geoobjects.GeoObject;

public class GeoObjectItem {
    public static final int IS_LAKE = 0;
    public static final int IS_HOSTEL = 1;
    public static final int IS_SHOP = 2;

    private String itemTitle;
    private int itemType;
    private GeoObject object;

    public String getItemTitle() {
        return itemTitle;
    }

    public int getItemType(){return itemType;}

    public GeoObject getObject(){return object;}

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public GeoObjectItem(String title, GeoObject o, int type){
        this.object = o;
        this.itemTitle = title; this.itemType = type;
    }



}