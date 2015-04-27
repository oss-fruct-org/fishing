package oss.fruct.org.fishing.geoobjects;

import android.location.Location;
import com.google.android.gms.maps.model.Marker;

public class GeoObject {

    private String name;
    private Location geoCoordinate;
    private int marker; // id of resource

    public GeoObject() {
        name = "";
        geoCoordinate = new Location("") ;
        geoCoordinate.setLatitude(0);


    }

    public GeoObject(String name, double latitude, double longitude) {
        this.name = name;
        geoCoordinate = new Location("") ;
        geoCoordinate.setLatitude(latitude);
        geoCoordinate.setLongitude(longitude);
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

    public Location getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setLatitude(double latitude) {
        geoCoordinate.setLatitude(latitude);
    }

    public void setLongitude(double longitude) {
        geoCoordinate.setLongitude(longitude);
    }

    public int getMarker() {
        return marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public boolean equals(GeoObject o){
        if(this.name.equalsIgnoreCase(o.getName()) &&
                this.getLongitude() == o.getLongitude() &&
                this.getLatitude() == o.getLatitude())
            return true;
        else
            return false;
    }
}