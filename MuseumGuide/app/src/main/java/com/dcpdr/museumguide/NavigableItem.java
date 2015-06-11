package com.dcpdr.museumguide;

import android.os.Parcel;
import android.os.Parcelable;

public class NavigableItem implements Parcelable{
    private String name, author;
    private String description;
    private String sensorId;

    public NavigableItem() {}

    public NavigableItem(String name, String author, String description, String sensorId)
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

    public static final Parcelable.Creator<NavigableItem> CREATOR = new Creator<NavigableItem>() {
        public NavigableItem createFromParcel(Parcel in) {
            NavigableItem NavigableItem = new NavigableItem();
            NavigableItem.name = in.readString();
            NavigableItem.author = in.readString();
            NavigableItem.description = in.readString();
            NavigableItem.sensorId = in.readString();
            return NavigableItem;
        }

        public NavigableItem[] newArray(int size) {
            return new NavigableItem[size];
        }
    };
}
