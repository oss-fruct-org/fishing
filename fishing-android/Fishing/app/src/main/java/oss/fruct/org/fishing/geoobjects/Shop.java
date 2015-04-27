package oss.fruct.org.fishing.geoobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class Shop extends GeoObject implements Parcelable {

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

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Shop createFromParcel(Parcel in ) {
            return new Shop(in);
        }

        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public Shop(Parcel in){
        super();
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeDouble(getLatitude());
        dest.writeDouble(getLongitude());
        dest.writeString(getPhone());
        dest.writeString(getAddress());
    }

    public void readFromParcel(Parcel in){
        setName(in.readString());
        setLatitude(in.readDouble());
        setLongitude(in.readDouble());
        setPhone(in.readString());
        setAddress(in.readString());
    }
}