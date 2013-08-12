package org.fruct.oss.kareliafishing.models;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.2012
 * This class describes properties of hostel object.
 */
public class Hostel extends GeoObject{
    
    private String description;
    private String phone;
    private String site;
    
    public Hostel() {
        description = "";
        phone = "";
        site = "";
    }

    public Hostel(String description, String phone, String site, String name, 
            double latitude, double longitude) {
        super(name, latitude, longitude);
        this.description = description;
        this.phone = phone;
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getSite() {
        return site;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSite(String site) {
        this.site = site;
    }   
}
