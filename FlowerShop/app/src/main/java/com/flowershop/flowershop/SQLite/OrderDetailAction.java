package com.flowershop.flowershop.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flowershop.flowershop.Object.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAction {
    private MySqlHelper mysql;
    private DetailOrderTable detailOrder;
    public OrderDetailAction(MySqlHelper m){
        this.mysql = m;
        this.detailOrder = m.getDetail();
    }
    public void AddDetail(OrderDetails detail){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(detailOrder.getCOL_ID_ORDER(),detail.getOrderID());
        values.put(detailOrder.getCOL_HINHANH(),detail.getImage());
        values.put(detailOrder.getCOL_GIA(),detail.getPrice());
        values.put(detailOrder.getCOL_LOAI(),detail.getSpecies());
        values.put(detailOrder.getCOL_TEN(),detail.getFlowername());
        values.put(detailOrder.getCOL_SOLUONG(),detail.getSL());
        values.put(detailOrder.getCOL_KEY_DETAIL(),detail.getKey());
        db.insert(detailOrder.getTABLE_NAME(),null,values);
        db.close();
    }
    public List<OrderDetails> GetDetailByIdOrder(String idOder){
        List<OrderDetails> list = new ArrayList<>();
        String query = "select * from "+ detailOrder.getTABLE_NAME()+" where "+ detailOrder.getCOL_ID_ORDER()+ " = '"+ idOder+ "';";
        SQLiteDatabase db = mysql.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            /*COL_ID_ORDER + " TEXT , "+
            COL_TEN+" TEXT, "+
            COL_LOAI +" TEXT, "+
            COL_HINHANH +" TEXT, "+
            COL_SOLUONG + " INTEGER , "+
            COL_GIA+" TEXT, "+
            COL_KEY_DETAIL+"TEXT)";*/
            do{
                OrderDetails detail=new OrderDetails();
                detail.setOrderID(cursor.getString(0));
                detail.setFlowername(cursor.getString(1));
                detail.setSpecies(cursor.getString(2));
                detail.setImage(cursor.getString(3));
                detail.setSL(cursor.getString(4));
                detail.setPrice(cursor.getString(5));
                detail.setKey(cursor.getString(6));
                list.add(detail);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return list;
    }
    // xóa các detail trong đơn hàng
    public void DeleteDetail(OrderDetails detail){
        SQLiteDatabase db =mysql.getWritableDatabase();
        db.delete(detailOrder.getTABLE_NAME(),detailOrder.getCOL_ID_ORDER()+ "=?",new String[]{String.valueOf(detail.getOrderID())});
        db.close();
    }
}
