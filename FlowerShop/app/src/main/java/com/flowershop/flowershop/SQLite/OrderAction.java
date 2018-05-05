package com.flowershop.flowershop.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flowershop.flowershop.Object.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAction {
    private MySqlHelper mysql;
    OrderTable orderTable;

    public OrderAction(MySqlHelper mysql) {
        this.mysql = mysql;
        this.orderTable = mysql.getOrder();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }
    public void AddOrder(Order order){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(orderTable.getCOL_ID(),order.getKey());
        values.put(orderTable.getCOL_ADDR(),order.getAddress());
        values.put(orderTable.getCOL_NAME(),order.getUsername());
        values.put(orderTable.getCOL_PHONE(),order.getPhone());
        values.put(orderTable.getCOL_STATUS(),order.getStatus());
        values.put(orderTable.getCOL_TIME(),order.getTime());
        values.put(orderTable.getCOL_USER(),order.getUser_key());
        values.put(orderTable.getCOL_TONGTIEN(),order.getTotal());
        db.insert(orderTable.getTABLE_NAME(),null,values);
        db.close();
    }
    public List<Order> GetOrderByUserId(String idUser){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+ orderTable.getTABLE_NAME()+" where "+orderTable.getCOL_USER()+ " = '"+ idUser+ "';";
        Cursor cursor = db.rawQuery(query,new String[]{idUser});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{

                Order order = new Order();
                order.setKey(cursor.getString(0));
                order.setUser_key(cursor.getString(1));
                order.setStatus(cursor.getString(2));
                order.setAddress(cursor.getString(3));
                order.setPhone(cursor.getString(4));
                order.setUsername(cursor.getString(5));
                order.setTime(cursor.getString(6));
                order.setTotal(cursor.getString(7));
                list.add(order);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    //public Order GetOrderById()
    public void DeleteOrder(Order order){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(orderTable.getTABLE_NAME(),orderTable.getCOL_ID()+ "=?",new String[]{String.valueOf(order.getKey())});
        db.close();
    }
}


