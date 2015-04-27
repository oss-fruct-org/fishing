package oss.fruct.org.fishing.fragments;

public class FishListItem {

    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public FishListItem(String title){
        this.itemTitle = title;
    }
}