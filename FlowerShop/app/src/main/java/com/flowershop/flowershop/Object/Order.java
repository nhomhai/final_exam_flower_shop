package com.flowershop.flowershop.Object;

/**
 * Created by mpxv2 on 3/24/18.
 */

public class Order {

    private String key;
    private String user_key;
    private String username;
    private String address;
    private String phone;
    private String status;
    private String time;
    private String total;
    private String recieve_name;
    private String recieve_phone;
    private String getRecieve_address;
    public void Order (String key, String user_key, String username, String address, String phone, String status, String time,String recieve_name, String recieve_phone, String getRecieve_address)
    {
        this.key=key;
        this.user_key=user_key;
        this.username=username;
        this.address=address;
        this.phone=phone;
        this.status=status;
        this.time=time;
        this.recieve_name=recieve_name;
        this.recieve_phone=recieve_phone;
        this.getRecieve_address=getRecieve_address;
    }

    public String getGetRecieve_address() {
        return getRecieve_address;
    }

    public String getRecieve_name() {
        return recieve_name;
    }

    public String getRecieve_phone() {
        return recieve_phone;
    }

    public String getKey() {
        return key;
    }

    public String getTotal() {
        return total;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getUser_key() {
        return user_key;
    }

    public String getUsername() {
        return username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setGetRecieve_address(String getRecieve_address) {
        this.getRecieve_address = getRecieve_address;
    }

    public void setRecieve_name(String recieve_name) {
        this.recieve_name = recieve_name;
    }

    public void setRecieve_phone(String recieve_phone) {
        this.recieve_phone = recieve_phone;
    }
}
