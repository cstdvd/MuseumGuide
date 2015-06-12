package com.dcpdr.museumguide;

import android.os.Parcel;
import android.os.Parcelable;

public class NavigableItem implements Parcelable, Comparable<NavigableItem>{
    private String name, author;
    private String description;
    private String roomId;

    public NavigableItem() {}

    public NavigableItem(String name, String author, String description, String sensorId)
    {
        this.name = name;
        this.author = author;
        this.description = description;
        this.roomId = sensorId;
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

    public String getRoomId()
    {
        return this.roomId;
    }

    // Parcelable methods
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(author);
        out.writeString(description);
        out.writeString(roomId);
    }

    public static final Parcelable.Creator<NavigableItem> CREATOR = new Creator<NavigableItem>() {
        public NavigableItem createFromParcel(Parcel in) {
            NavigableItem NavigableItem = new NavigableItem();
            NavigableItem.name = in.readString();
            NavigableItem.author = in.readString();
            NavigableItem.description = in.readString();
            NavigableItem.roomId = in.readString();
            return NavigableItem;
        }

        public NavigableItem[] newArray(int size) {
            return new NavigableItem[size];
        }
    };

    // Comparable methods
    @Override
    public int compareTo(NavigableItem item) {
        return name.compareTo(item.getName());
    }
}
