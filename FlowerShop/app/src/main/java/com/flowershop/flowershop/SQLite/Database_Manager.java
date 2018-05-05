package com.flowershop.flowershop.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.flowershop.flowershop.Object.Flower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenjita.tran on 3/18/18.
 */

public class Database_Manager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="flower_db";
    //-----------------------------------------------
    private static final String TABLE_NAME_USER ="User"; // table User
    private static final String ID_USER ="UserID"; // primary key
    private static final String PASSWORD = "Password";
    private static final String NAME_USER ="FullName";
    private static final String EMAIL ="Email";
    private static final String ADDRESS ="Address";
    private static final String POINT ="FlowerPoint";
    private static final String PHONE = "Phone";
    private static final String PERMISSON = "Permisson";
    //-----------------------------------------------
    private static final String TABLE_NAME_FLOWER = "FLOWER"; // table Flower
    private static final String ID_FLOWER="id";
    private static final String NAME_FLOWER = "FlowerName";
    private static final String VIEW_FLOWER = "FlowerView"; // primary key
    private static final String PRICE_FLOWER = "Price";
    private static final String DETAILS = "Details";
    private static final String SPECIES = "Species";
    private static final String STATUS = "Status";
    //-----------------------------------------------
    private static final String TABLE_NAME_ORDER_DETAIL = "OrderDetail"; // table OrderDetail
    private static final String FLOWER_NAME_ORDERDETAIL = "Flower_Name";
    private static final String ID_FLOWER_ORDERDETAIL = "FlowerID";
    private static final String PRICE_ORDERDETAIL = "Price";
    private static final String ID_ORDERDETAIL = "OrderID"; // primary key
    private static final String SL = "SL";
    //-----------------------------------------------
    private static final String TABLE_NAME_ORDER = "Order";
    private static final String ID_ORDER = "OrderID";
    private static final String ID_USER_ORDER = "UserID";
    private static final String NAME_USER_ORDER = "UserName";
    private static final String ADDRESS_ORDER = "Address";
    private static final String PHONE_ORDER = "Phone";
    private static final String TIME_ORDER = "Time";
    private static final String STATUS_ORDER = "Status";
    private Context context;

    public Database_Manager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("Database_Manager", "Database_Manager: ");
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_FLOWER+TABLE_NAME_USER+TABLE_NAME_ORDER_DETAIL+TABLE_NAME_ORDER);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }


    public void addFlower (Flower flower) // add new Flower
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(ID_FLOWER,flower.getId());
        values.put(VIEW_FLOWER,flower.getimageFlower());
        values.put(NAME_FLOWER,flower.getFlowername());
        values.put(PRICE_FLOWER,flower.getPrice());
        values.put(DETAILS,flower.getDetails());
        values.put(SPECIES,flower.getSpecies());
        values.put(STATUS,flower.getStatus());

        db.insert(TABLE_NAME_FLOWER,null,values);
        db.close();
    }
    public List<Flower> getAll()
    {
        List<Flower> listdata = new ArrayList();
        String querry = "SELECT * FROM " + TABLE_NAME_FLOWER+" ;";
        String querry2 = "DELETE FROM "+TABLE_NAME_FLOWER+" WHERE 1;";
        String querry3 = "DROP TABLE "+DATABASE_NAME+"."+TABLE_NAME_FLOWER+" ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(querry,null);
        if(cursor.moveToFirst())
        {
            do
            {
                Flower flower = new Flower();
                flower.setId(cursor.getString(0));//ID
                flower.setimageFlower(cursor.getString(1));// VIEW
                flower.setFlowername(cursor.getString(2));// Name
                flower.setPrice(cursor.getString(3));// Price
                flower.setDetails(cursor.getString(4));// Detail
                flower.setSpecies(cursor.getString(5));// Species
                flower.setStatus(cursor.getString(6));// Status
                listdata.add(flower);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listdata;
    }
    public void deleteFlower (Flower flower)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //String querry_delete = "delete from "+TABLE_NAME_FLOWER+ "where "+NAME_FLOWER+" = "+ flower.getFlowername();
        db.delete(TABLE_NAME_FLOWER,NAME_FLOWER + " = ? ", new String[]{String.valueOf(flower.getFlowername())});
        db.close();
    }
    public void updateFlower (Flower flower)
    {

    }
    public boolean login  (String id, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String querry_login = "SELECT * FROM User WHERE UserID = '"+id+"' AND Password = '"+password+"'";
        Cursor cursor = db.rawQuery(querry_login,null);
        if(cursor.getCount()==0)
            return false;
        else
            return true;
    }
    public void addUser(String userid, String password, String email, String address, String phone, String fullname )
    {
        int point=0;
        SQLiteDatabase db = this.getWritableDatabase();
        String querry_addUser = "INSERT INTO User VALUES ("+"'"+userid+"',"+"'"+password+"',"+"'"+fullname+"',"+"'"+email+"',"+"'"+address+"',"+point+","+"'"+phone+"',"+0+");";
        db.execSQL(querry_addUser);
    }
    public boolean findUserById (String userid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String querry_findUserById = "SELECT UserID FROM User WHERE UserID = '"+userid+"';";
        Cursor cursor = db.rawQuery(querry_findUserById,null);
        if(cursor.getCount()==0) // chua co user nay truoc do
            return false;
        else
            return true;
    }
}
