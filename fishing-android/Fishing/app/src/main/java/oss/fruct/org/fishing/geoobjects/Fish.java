package oss.fruct.org.fishing.geoobjects;


import android.os.Parcel;
import android.os.Parcelable;

public class Fish implements Parcelable{

    private String id;
    private String name;
    private String fishInfo;
    private String picture_path;

    public Fish() {
        id = "";
        name = "";
        fishInfo = "";
        picture_path = null;
    }

    public Fish(String name, String fishInfo) {
        this.name = name;
        this.fishInfo = fishInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFishInfo() {
        return fishInfo;
    }

    public String getPicturePath() {
        return picture_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFishInfo(String fishInfo) {
        this.fishInfo = fishInfo;
    }

    public void setPicturePath(String picture_path) {
        this.picture_path = picture_path;
    }

    public void setPicturePath(String directory, String extension){
        this.picture_path = directory + this.id + extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Fish(Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Fish createFromParcel(Parcel in ) {
            return new Fish(in);
        }

        public Fish[] newArray(int size) {
            return new Fish[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(fishInfo);
        dest.writeString(picture_path);
    }

    private void readFromParcel(Parcel in ) {
        id = in.readString();
        name = in.readString();
        fishInfo = in.readString();
        picture_path = in.readString();
    }
}
