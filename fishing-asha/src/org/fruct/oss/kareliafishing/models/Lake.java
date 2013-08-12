package org.fruct.oss.kareliafishing.models;

import java.util.Vector;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.2012
 * This class describes properties of lake object.
 */
public class Lake extends GeoObject{
    
    private String description;
    private Vector fishesInfo;

    public Lake() {
        description = "";
    }

    public Lake(String description, Vector fishesInfo, String name, 
            double latitude, double longitude) {
        super(name, latitude, longitude);
        this.description = description;
        this.fishesInfo = fishesInfo;
    }

    public String getDescription() {
        return description;
    }

    public Vector getFishesInfo() {
        return fishesInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFishesInfo(Vector fishesInfo) {
        this.fishesInfo = fishesInfo;
    } 
}
