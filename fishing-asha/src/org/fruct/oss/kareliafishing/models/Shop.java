package org.fruct.oss.kareliafishing.models;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.13
 * This class describes properties of shop object.
 */
public class Shop extends GeoObject{
    
    private String address;
    private String phone;

    public Shop() {
        address = "";
        phone = "";
    }

    public Shop(String address, String phone, String name, double latitude, 
            double longitude) {
        super(name, latitude, longitude);
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
