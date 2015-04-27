package oss.fruct.org.fishing.geoobjects;


import android.os.Parcel;
import android.os.Parcelable;

public class Hostel extends GeoObject implements Parcelable {

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

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Hostel createFromParcel(Parcel in ) {
            return new Hostel(in);
        }

        public Hostel[] newArray(int size) {
            return new Hostel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeDouble(getLatitude());
        dest.writeDouble(getLongitude());
        dest.writeString(description);
        dest.writeString(phone);
        dest.writeString(site);
    }

    public void readFromParcel(Parcel in){
        setName(in.readString());
        setLatitude(in.readDouble());
        setLongitude(in.readDouble());
        setDescription(in.readString());
        setPhone(in.readString());
        setSite(in.readString());
    }

    public Hostel(Parcel in){
        super();
        readFromParcel(in);
    }
}