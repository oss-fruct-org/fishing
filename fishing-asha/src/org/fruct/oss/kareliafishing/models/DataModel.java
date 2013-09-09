package org.fruct.oss.kareliafishing.models;

import java.util.Vector;
import org.fruct.oss.kareliafishing.Localization;

/**
 *
 * @author Nikita Davydovskii
 * date: 24.07.2013
 * This class represents data model of the application. It contains all 
 * other objects.
 */
public class DataModel {
    
    private String[] geoTypesList;
    private Vector hostels;
    private Vector lakes;
    private Vector shops;
    private Vector fishes;
    
    private int currentLake = 0;
    private int currentShop = 0;
    private int currentHostel = 0;
    
    public static final int NUMBER_OF_GEOTYPES = 3;
    public static final String[] DEFAULT_GEOTYPES_LIST = {"Lakes", "Shops",
                                                            "Hostels"};
    public DataModel(Localization strings) {
        geoTypesList = new String[NUMBER_OF_GEOTYPES];
        for (int i = 0; i < NUMBER_OF_GEOTYPES; i++) {
            geoTypesList[i] = strings.localize("geotype-".concat(String.valueOf(i)), 
                    DEFAULT_GEOTYPES_LIST[i]);
        }
        hostels = new Vector();
        lakes = new Vector();
        shops = new Vector();
        fishes = new Vector();
    }

    public String[] getGeoTypesList() {
        return geoTypesList;
    }
    
    public Vector getHostels() {
        return hostels;
    }

    public Vector getLakes() {
        return lakes;
    }

    public Vector getShops() {
        return shops;
    }

    public Vector getFishes() {
        return fishes;
    }

    public void setHostels(Vector hostels) {
        this.hostels = hostels;
    }

    public void setLakes(Vector lakes) {
        this.lakes = lakes;
    }

    public void setShops(Vector shops) {
        this.shops = shops;
    }

    public void setFishes(Vector fishes) {
        this.fishes = fishes;
    }

    public int getCurrentLake() {
        return currentLake;
    }

    public int getCurrentShop() {
        return currentShop;
    }

    public int getCurrentHostel() {
        return currentHostel;
    }

    public void setCurrentLake(int currentLake) {
        this.currentLake = currentLake;
    }

    public void setCurrentShop(int currentShop) {
        this.currentShop = currentShop;
    }

    public void setCurrentHostel(int currentHostel) {
        this.currentHostel = currentHostel;
    }
}
