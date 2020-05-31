
/*
Name: Oritsejolomi Sillo
Student Number: 20091
*/

package com.example.dublinbikes;

public class dublin_bikes_item {
    private String mContract_name;
    private String mName;
    private String mAddress;
    private double mPositionLat;
    private double mPositionLng;
    private int mBike_stands;
    private int mAvailable_bike_stands;
    private int mAvailable_bikes;
    private String mStatus;

    public dublin_bikes_item(String Contract_name, String Name, String Address, double PositionLat, double PositionLng, int Bike_stands, int Available_bike_stands, int Available_bikes, String Status) {
        this.mContract_name = Contract_name;
        this.mName = Name;
        this.mAddress = Address;
        this.mPositionLat = PositionLat;
        this.mPositionLng = PositionLng;
        this.mBike_stands = Bike_stands;
        this.mAvailable_bike_stands = Available_bike_stands;
        this.mAvailable_bikes = Available_bikes;
        this.mStatus = Status;
    }

    public String getmContract_name() {
        return mContract_name;
    }

    public String getmName() {
        return mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public double getmPositionLat() {
        return mPositionLat;
    }

    public double getmPositionLng() {
        return mPositionLng;
    }

    public int getmBike_stands() {
        return mBike_stands;
    }

    public int getmAvailable_bike_stands() {
        return mAvailable_bike_stands;
    }

    public int getmAvailable_bikes() {
        return mAvailable_bikes;
    }

    public String getmStatus() {
        return mStatus;
    }
}
