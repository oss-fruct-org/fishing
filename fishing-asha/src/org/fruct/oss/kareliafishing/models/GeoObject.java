package org.fruct.oss.kareliafishing.models;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.13
 * This class is a base class for all geographical objects 
 * which will be used in this application. It contains object's 
 * name, latitude and longitude.
 */
public class GeoObject {
    
    private String name;
    private double latitude;
    private double longitude;

    public GeoObject() {
        name = "";
        latitude = 0.0;
        longitude = 0.0;
    }
    
    public GeoObject(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /*
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }   
}
