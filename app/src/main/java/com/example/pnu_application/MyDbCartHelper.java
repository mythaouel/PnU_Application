package com.example.pnu_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class MyDbCartHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "cart_db.sqlite";

    public static final String TBL_NAME = "Cart";
    public static final String COL_PD_ID = "Product_Id";
    public static final String COL_PD_IMG = "Product_Image";
    public static final String COL_PD_NAME = "Product_Name";
    public static final String COL_PD_PRICE = "Product_Price";
    public static final String COL_PD_QTY = "Product_Qty";


    public MyDbCartHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_PD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PD_IMG + " INTEGER, "
                + COL_PD_NAME + " VARCHAR(200), " + COL_PD_PRICE + " DOUBLE, " + COL_PD_QTY + " INTEGER)";
        sqLiteDatabase.execSQL( sql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TBL_NAME );
        onCreate( sqLiteDatabase );
    }

    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + TBL_NAME,null );
        int count = cursor.getCount();
        cursor.close();
        return 0;
    }

    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL( sql );
    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery( sql,null );
    }

    public boolean insertData(int photo, String name, double price, int quantity){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = " INSERT INTO " + TBL_NAME + " VALUES(null, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindDouble(1,photo);
            statement.bindString( 2,name );
            statement.bindDouble( 3,price );
            statement.bindDouble( 4,quantity );

            statement.executeInsert();
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
