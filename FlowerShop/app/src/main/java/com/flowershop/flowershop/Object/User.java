package com.flowershop.flowershop.Object;

/**
 * Created by kenjita.tran on 3/18/18.
 */

public class User {
    private String userid;
    private String password;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String key;
    private String point;
    private String permisson;

    public User (String userid, String password, String name, String email, String address, String phone,String key, String point, String permisson)
    {
        this.userid = userid;
        this.password = password;
        this.address = address;
        this.name=name;
        this.email=email;
        this.point=point;
        this.permisson=permisson;
        this.phone=phone;
    }
    public User()
    {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public String getPermisson() {
        return permisson;
    }

    public void setPermisson(String permisson) {
        this.permisson = permisson;
    }

    public String getPoint() {
        return point;
    }

    public String getUserid() {
        return userid;
    }
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
