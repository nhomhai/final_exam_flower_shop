package com.flowershop.flowershop.SQLite;

public class DetailOrderTable {
    private String TABLE_NAME = "DetailOrder";
    private String COL_KEY_DETAIL = "key";
    private String COL_ID_ORDER = "OrderID";
    private String COL_TEN ="FlowerName";
    private String COL_LOAI = "Species";
    private String COL_GIA = "Price";
    private String COL_HINHANH= "Image";
    private String COL_SOLUONG = "SL";
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+
            COL_ID_ORDER + " TEXT , "+
            COL_TEN+" TEXT, "+
            COL_LOAI +" TEXT, "+
            COL_HINHANH +" TEXT, "+
            COL_SOLUONG + " INTEGER , "+
            COL_GIA+" TEXT, "+
            COL_KEY_DETAIL+"TEXT)";

    public DetailOrderTable() {
    }

    public String getCOL_KEY_DETAIL() {
        return COL_KEY_DETAIL;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOL_ID_ORDER() {
        return COL_ID_ORDER;
    }

    public void setCOL_ID_ORDER(String COL_ID_ORDER) {
        this.COL_ID_ORDER = COL_ID_ORDER;
    }

    public String getCOL_TEN() {
        return COL_TEN;
    }

    public void setCOL_TEN(String COL_TEN) {
        this.COL_TEN = COL_TEN;
    }

    public String getCOL_LOAI() {
        return COL_LOAI;
    }

    public void setCOL_LOAI(String COL_LOAI) {
        this.COL_LOAI = COL_LOAI;
    }

    public String getCOL_GIA() {
        return COL_GIA;
    }

    public void setCOL_GIA(String COL_GIA) {
        this.COL_GIA = COL_GIA;
    }

    public String getCOL_HINHANH() {
        return COL_HINHANH;
    }

    public void setCOL_HINHANH(String COL_HINHANH) {
        this.COL_HINHANH = COL_HINHANH;
    }

    public String getCREATE_TABLE() {
        return CREATE_TABLE;
    }

    public void setCREATE_TABLE(String CREATE_TABLE) {
        this.CREATE_TABLE = CREATE_TABLE;
    }

    public String getCOL_SOLUONG() {
        return COL_SOLUONG;
    }

    public void setCOL_SOLUONG(String COL_SOLUONG) {
        this.COL_SOLUONG = COL_SOLUONG;
    }

    public void setCOL_KEY_DETAIL(String COL_KEY_DETAIL) {
        this.COL_KEY_DETAIL = COL_KEY_DETAIL;
    }
}
