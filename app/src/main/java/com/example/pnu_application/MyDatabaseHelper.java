package com.example.pnu_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="PnU.sqlite";

    public static final String  CUSTOMER_TB_NAME="KhachHang";
    public static final String  ACCOUNT_TB_NAME="TAIKHOAN";
    public static final String  ORDER_TB_NAME="DONHANG";


    public static final String ACCOUNT_COL_ID="MATK";
    public static final String ACCOUNT_COL_EMAIL="EMAIL";
    public static final String ACCOUNT_COL_NUMBER="SODT";
    public static final String ACCOUNT_COL_PASSWORD="MATKHAU";
    public static final String ACCOUNT_COL_OTP="OTP";

    public static final String CUSTOMER_COL_ID="MAKH";
    public static final String CUSTOMER_COL_ACT_ID="MATK";
    public static final String CUSTOMER_COL_NAME="HOTEN";
    public static final String CUSTOMER_COL_BIRTHDAY="NGAYSINH";
    public static final String CUSTOMER_COL_EMAIL="EMAIL";
    public static final String CUSTOMER_COL_ADDRESS="DIACHI";
    public static final String CUSTOMER_COL_NUMBER="SODT";
    public static final String CUSTOMER_COL_PHOTO="AVATAR";

    public static final String ORDER_COL_ID="MADH";
    public static final String ORDER_COL_NAME="HOTEN";
    public static final String ORDER_COL_ADDRESS="DIACHI";
    public static final String ORDER_COL_NUMBER="SODT";
    public static final String ORDER_COL_QUANTITY="SOLUONG";
    public static final String ORDER_COL_TOTAL="THANHTIEN";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql1="CREATE TABLE IF NOT EXISTS " +ACCOUNT_TB_NAME +"("+ ACCOUNT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_COL_EMAIL + " VARCHAR(100), " + ACCOUNT_COL_NUMBER + " INTEGER," + ACCOUNT_COL_PASSWORD + " TEXT," + ACCOUNT_COL_OTP + " INTEGER)";

        String sql2="CREATE TABLE IF NOT EXISTS " +CUSTOMER_TB_NAME +"("+ CUSTOMER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                      CUSTOMER_COL_NAME+ " TEXT, " + CUSTOMER_COL_BIRTHDAY + " DATE, "+ CUSTOMER_COL_EMAIL + " VARCHAR(100),"+ CUSTOMER_COL_NUMBER + " INTEGER,"+
                      CUSTOMER_COL_ADDRESS + " VARCHAR(200), " + CUSTOMER_COL_PHOTO + " BLOB, " + CUSTOMER_COL_ACT_ID +" INTEGER REFERENCES " + ACCOUNT_TB_NAME + "(" + ACCOUNT_COL_ID +")" +")";

        String sql3= "CREATE TABLE IF NOT EXISTS " +ORDER_TB_NAME +"("+ ORDER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                     ORDER_COL_NAME+ " VARCHAR(200), " + ORDER_COL_ADDRESS + " VARCHAR(200), "+ ORDER_COL_NUMBER + " VARCHAR(15), "+ ORDER_COL_QUANTITY + " INTEGER, "+ ORDER_COL_TOTAL + " REAL)";

        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ CUSTOMER_TB_NAME);
        onCreate(sqLiteDatabase);
    }
    public Cursor getData(String sql){
        SQLiteDatabase db= getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public void queryExec(String sql){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(sql);
    }
    public int getCount(String tb_name){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ tb_name,null);
        int count= cursor.getCount();
        cursor.close();
        return count;
    };
    public void createSomeTestRows(){
        int count1=getCount(CUSTOMER_TB_NAME);
        int count2=getCount(ACCOUNT_TB_NAME);
        if (count1==0||count2==0){
//            queryExec("INSERT INTO "+CUSTOMER_TB_NAME+" VALUES(null,'LP01','LAPTOP',12,'Apple',null,null)");

            //Thêm Test thử
//            queryExec("INSERT INTO "+CUSTOMER_TB_NAME+" VALUES(null,'Lâm Hữu Gia','LAPTOP',12,'KTX Khu B ĐHQG TP. HCM',0978362814,null,null)");

            queryExec("INSERT INTO "+ACCOUNT_TB_NAME+" VALUES(null,'hoangyen@.study.com',0849111149,'123',123)");
        }
    }
    public boolean insertCustomerData(String name, String birthday,String email,String address,int phone, byte[] photo, int MAKH){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + CUSTOMER_TB_NAME + " VALUES(null,?,?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, name);
            statement.bindString(2,birthday);
            statement.bindString(3, email);

            statement.bindDouble(4,phone);
            statement.bindString(5, address);
            statement.bindBlob(6, photo);
            statement.bindDouble(7, MAKH);
            statement.executeInsert();

            return true;
        } catch (Exception Ex){
            return false;
        }
    }

    public boolean insertOrderData(String name, String address, String phone, int quantity, double total){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = " INSERT INTO " + ORDER_TB_NAME + " VALUES(null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, name);
            statement.bindString(2, address);
            statement.bindString(3, phone);
            statement.bindDouble(4, quantity);
            statement.bindDouble(5,total);

            statement.executeInsert();
            return true;
        } catch (Exception Ex){
            return false;
        }
    }
}
