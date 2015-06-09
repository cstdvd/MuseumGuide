package com.dcpdr.museumguide;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable{
    private String name, author;
    private String description;
    private String sensorId;

    public Picture() {}

    public Picture(String name, String author, String description, String sensorId)
    {
        this.name = name;
        this.author = author;
        this.description = description;
        this.sensorId = sensorId;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getSensorId()
    {
        return this.sensorId;
    }

    // Parcelable methods
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(author);
        out.writeString(description);
        out.writeString(sensorId);
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Creator<Picture>() {
        public Picture createFromParcel(Parcel in) {
            Picture picture = new Picture();
            picture.name = in.readString();
            picture.author = in.readString();
            picture.description = in.readString();
            picture.sensorId = in.readString();
            return picture;
        }

        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
