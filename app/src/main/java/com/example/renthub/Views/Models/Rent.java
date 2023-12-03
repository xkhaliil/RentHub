package com.example.renthub.Views.Models;

public class Rent {
    String id;
    String carUID;
    String userID;
    String startDate;
    String endDate;
    String price;
    String pickupLocation;

    public Rent(String id, String carUID, String userID, String startDate, String endDate, String price, String pickupLocation) {
        this.id = id;
        this.carUID = carUID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.pickupLocation = pickupLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarUID() {
        return carUID;
    }

    public void setCarUID(String carUID) {
        this.carUID = carUID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

}
