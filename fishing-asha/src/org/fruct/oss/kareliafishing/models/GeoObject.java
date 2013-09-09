package org.fruct.oss.kareliafishing.models;

import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.map.MapFactory;
import com.nokia.maps.map.MapMarker;
import com.nokia.maps.map.Point;
import javax.microedition.lcdui.Image;

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
    private GeoCoordinate geoCoordinate;
    private MapMarker marker;

    public GeoObject() {
        name = "";
        geoCoordinate = new GeoCoordinate(0, 0, 0);
    }
    
    public GeoObject(String name, double latitude, double longitude) {
        this.name = name;
        geoCoordinate = new GeoCoordinate(latitude, longitude, 0);
    }
    
    /*
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return geoCoordinate.getLatitude();
    }

    public double getLongitude() {
        return geoCoordinate.getLongitude();
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }
    
    public void setLatitude(double latitude) {
        geoCoordinate.setLatitude(latitude);
    }

    public void setLongitude(double longitude) {
        geoCoordinate.setLongitude(longitude);
    }

    public MapMarker getMarker() {
        return marker;
    }

    public void setMarker(MapMarker marker) {
        this.marker = marker;
    }
}
