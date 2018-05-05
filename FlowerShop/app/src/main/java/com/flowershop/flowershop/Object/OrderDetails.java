package com.flowershop.flowershop.Object;

/**
 * Created by mpxv2 on 3/24/18.
 */

public class OrderDetails {
    private String image;
    private String key;
    private String species;
    private String flowername;
    private String flowerID;
    private String price;
    private String SL;
    private String orderID;
    public void OrderDetails(String key, String flowerID, String flowername,String image, String price, String SL, String orderID, String species)
    {
        this.key=key;
        this.image=image;
        this.species=species;
        this.flowerID=flowerID;
        this.flowername=flowername;
        this.price=price;
        this.SL=SL;
        this.orderID=orderID;
    }

    public String getImage() {
        return image;
    }

    public String getKey() {
        return key;
    }

    public String getSpecies() {
        return species;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getPrice() {
        return price;
    }

    public String getSL() {
        return SL;
    }

    public String getFlowerID() {
        return flowerID;
    }

    public String getFlowername() {
        return flowername;
    }

    public void setFlowerID(String flowerID) {
        this.flowerID = flowerID;
    }

    public void setFlowername(String flowername) {
        this.flowername = flowername;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
