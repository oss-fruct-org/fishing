package oss.fruct.org.fishing.geoobjects;

import java.util.Vector;

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
