package com.flowershop.flowershop.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flowershop.flowershop.Object.Flower;

import java.util.ArrayList;
import java.util.List;

public class FlowerAction  {
    private MySqlHelper mysql;
    private FlowerTable flowerTable;

    public FlowerAction(MySqlHelper mysql) {
        //mysql = new MySqlHelper();
        this.mysql = mysql;
        this.flowerTable = mysql.getFlower();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public FlowerTable getFlowerTable() {
        return flowerTable;
    }

    public void setFlowerTable(FlowerTable flowerTable) {
        this.flowerTable = flowerTable;
    }

    //add a flower
    public void AddFlower(Flower fl) {
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(flowerTable.getCOL_ID(),fl.getId());
        values.put(flowerTable.getCOL_GIA(), fl.getPrice());
        values.put(flowerTable.getCOL_HINHANH(), fl.getimageFlower());
        values.put(flowerTable.getCOL_LOAI(), fl.getSpecies());
        values.put(flowerTable.getCOL_STATUS(), fl.getStatus());
        values.put(flowerTable.getCOL_TEN(), fl.getFlowername());
        values.put(flowerTable.getCOL_MOTA(),fl.getDetails());
        db.insert(flowerTable.getTABLE_NAME(), null, values);
        db.close();
    }
    //get all flower in db

    public List<Flower> getAllFlower(){
        List<Flower> list =  new ArrayList<>();
        String query = "select * from "+ flowerTable.getTABLE_NAME() +";";
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                Flower fl = new Flower();
                fl.setId(cursor.getString(0));
                fl.setSpecies(cursor.getString(1));
                fl.setFlowername(cursor.getString(2));
                fl.setimageFlower(cursor.getString(3));
                fl.setPrice(cursor.getString(4));
                fl.setStatus(cursor.getString(5));
                fl.setDetails(cursor.getString(6));
                list.add(fl);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    // get ListFlower from Loai
    public List<Flower> getTypeFlower(String loai){
        List<Flower> list =  new ArrayList<>();
        String query = "select * from "+ flowerTable.getTABLE_NAME() + " where "+ flowerTable.getCOL_LOAI()+"=?;";
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,new String[]{loai});
        if(cursor.getCount() == 0) return  list;
        if(cursor.moveToFirst()) {

            do{
                Flower fl = new Flower();
                fl.setId(cursor.getString(0));
                fl.setSpecies(cursor.getString(1));
                fl.setFlowername(cursor.getString(2));
                fl.setimageFlower(cursor.getString(3));
                fl.setPrice(cursor.getString(4));
                fl.setStatus(cursor.getString(5));
                fl.setDetails(cursor.getString(6));
                list.add(fl);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    // Update a Flower
    public int Update(Flower fl){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(flowerTable.getCOL_ID(),fl.getId());
        values.put(flowerTable.getCOL_GIA(), fl.getPrice());
        values.put(flowerTable.getCOL_HINHANH(), fl.getimageFlower());
        values.put(flowerTable.getCOL_LOAI(), fl.getSpecies());
        values.put(flowerTable.getCOL_STATUS(), fl.getStatus());
        values.put(flowerTable.getCOL_TEN(), fl.getFlowername());
        values.put(flowerTable.getCOL_MOTA(),fl.getDetails());
        db.update(flowerTable.getTABLE_NAME(),values,flowerTable.getCOL_ID()+"="+fl.getId(),null);
        return  1;
     /*   }
        else return 0;*/

    }

    // Delete a Flower

    public void DeleteFlower(Flower fl){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(flowerTable.getTABLE_NAME(),flowerTable.getCOL_ID()+ "=?",new String[]{String.valueOf(fl.getId())});
        db.close();
    }

    public ArrayList<Flower> Search(String s_Search){
        ArrayList<Flower> kq = new ArrayList<>();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+ flowerTable.getTABLE_NAME()+" where ";
        String where = flowerTable.getCOL_TEN()+ " =?  or "+ flowerTable.getCOL_LOAI()+" =?  or "+flowerTable.getCOL_MOTA()+" =? ";
        //Cursor cursor = db.query(flowerTable.getTABLE_NAME(),new String[]{flowerTable.getCOL_ID()},where,)
        return kq;
    }
}
