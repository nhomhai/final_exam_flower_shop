package com.flowershop.flowershop.Object;

/**
 * Created by kenjita.tran on 3/7/18.
 */

    public class Flower {

    private String id;
    private String flowername;
    private String price;
    private String imageFlower;
    private String details;
    private String status;
    private String species;
    public Flower ()
    {

    }
    public Flower(String imageFlower, String flowername,  String price,String status, String details, String species) {
        this.flowername = flowername;
        this.price = price;
        this.status=status;
        this.species=species;
        this.details = details;
        this.imageFlower=imageFlower;
    }
    public Flower (String id, String imageFlower, String flowername, String price, String status, String details, String species)
    {
        this.id=id;
        this.imageFlower=imageFlower;
        this.flowername=flowername;
        this.price=price;
        this.status=status;
        this.details=details;
        this.species=species;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlowername() {
        return flowername;
    }

    public void setFlowername(String flowername) {
        this.flowername = flowername;
    }

    public String getimageFlower() {
        return imageFlower;
    }

    public void setimageFlower(String imageFlower) {
        this.imageFlower = imageFlower;
    }

}



