package com.flowershop.flowershop.Object;

public class Cart_Object {
    private String key;
    private String name;
    private String sl;
    private String price;
    private String image;
    private String userid;
    private String flowername;
    public void Cart_Object(String userid, String key,String image,String flowername, String name, String sl, String price)
    {
        this.flowername=flowername;
        this.userid=userid;
        this.image=image;
        this.name=name;
        this.price=price;
        this.sl=sl;
    }

    public String getFlowername() {
        return flowername;
    }

    public String getUserid() {
        return userid;
    }

    public String getImage() {
        return image;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSl() {
        return sl;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFlowername(String flowername) {
        this.flowername = flowername;
    }
}

