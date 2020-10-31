package com.example.projectiteration1.model;

/**
 * Restaurant class models the information about a
 * single restaurant. Data includes tracking number,
 * restaurant name, address, city, facility type, and
 * GPS coordinates.
 */
public class Restaurant {
    private String trackingNumber;
    private String resName;
    private String address;
    private String city;
    private String facType;
    private String latitude;
    private String longitude;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // For debugging purposes
    @Override
    public String toString() {
        return "Restaurant{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", resName='" + resName + '\'' +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", facType='" + facType + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
