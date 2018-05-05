package com.flowershop.flowershop.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flowershop.flowershop.Object.User;

public  class UserAction {
    private MySqlHelper mysql;
    UserTable userTable;

    public UserAction(MySqlHelper mysql) {
        this.mysql = mysql;
        this.userTable=mysql.getUser();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    // add new user
    public void AddUser(User u){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.getColId(),u.getKey());
        values.put(userTable.getColTen(),u.getName());
        values.put(userTable.getColAddr(),u.getAddress());
        values.put(userTable.getColEmail(),u.getEmail());
        values.put(userTable.getColPass(),u.getPassword());
        values.put(userTable.getColPermisson(),u.getPermisson());
        values.put(userTable.getColPhone(),u.getPhone());
        values.put(userTable.getColPoint(),u.getPoint());
        db.insert(userTable.getTableName(),null,values);
        db.close();
    }

    // select user by ID
    public User GetUserById(String id){
        User u = new User();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+userTable.getTableName()+" where "+ userTable.getColId()+ " = '"+ id+ "';";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
                    /*COL_ID +" TEXT primary key , "+
                    COL_TEN+" TEXT, "+
                    COL_PASS +" TEXT, "+
                    COL_ADDR+ " TEXT," +
                    COL_EMAIL+ " TEXT, "+
                    COL_POINT+" long, "+
                    COL_PHONE+ " TEXT, "+
                    COL_PERMISSON+ " INTEGER)";*/
            u.setKey(cursor.getString(0));
            u.setName(cursor.getString(1));
            u.setPassword(cursor.getString(2));
            u.setAddress(cursor.getString(3));
            u.setEmail(cursor.getString(4));
            u.setPoint(cursor.getString(5));
            u.setPhone(cursor.getString(6));
            u.setPermisson(cursor.getString(7));
        }
        cursor.close();
        db.close();
        return  u;
    }
    public void DeleteFlower(User user){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(userTable.getTableName(),userTable.getColId()+ "=?",new String[]{String.valueOf(user.getKey())});
        db.close();
    }

}
