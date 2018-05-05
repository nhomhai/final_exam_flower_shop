package com.flowershop.flowershop.SQLite;

public class FlowerTable {
    private String TABLE_NAME = "Flower";
    private String COL_ID = "key";
    private String COL_TEN ="FlowerName";
    private String COL_LOAI = "Species";
    private String COL_STATUS ="Status";
    private String COL_GIA = "Price";
    private String COL_HINHANH= "Image";
    private String COL_MOTA = "Describe";

    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+
            COL_ID + " TEXT , "+
            COL_LOAI +" TEXT, "+
            COL_TEN+" TEXT, "+
            COL_HINHANH +" TEXT,  "+
            COL_GIA+" TEXT,"+
            COL_STATUS + " TEXT, "+
            COL_MOTA + " TEXT)";

    public FlowerTable() {
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOL_ID() {
        return COL_ID;
    }

    public void setCOL_ID(String COL_ID) {
        this.COL_ID = COL_ID;
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

    public String getCOL_STATUS() {
        return COL_STATUS;
    }

    public void setCOL_STATUS(String COL_STATUS) {
        this.COL_STATUS = COL_STATUS;
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

    public String getCOL_MOTA() {
        return COL_MOTA;
    }

    public void setCOL_MOTA(String COL_MOTA) {
        this.COL_MOTA = COL_MOTA;
    }
}
