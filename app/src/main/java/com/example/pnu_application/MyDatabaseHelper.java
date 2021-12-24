package com.example.pnu_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.model.User;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="PnU.sqlite";

    public static final String  CUSTOMER_TB_NAME="KhachHang";
    public static final String  ACCOUNT_TB_NAME="TAIKHOAN";
    public static final String  ORDER_TB_NAME="DONHANG";


    public static final String ACCOUNT_COL_ID="MATK";
    public static final String ACCOUNT_COL_USERNAME="USERNAME";
    public static final String ACCOUNT_COL_NUMBER="SODT";
    public static final String ACCOUNT_COL_PASSWORD="MATKHAU";
    public static final String ACCOUNT_COL_OTP="OTP";
    public static final String ACCOUNT_COL_STATUS="STATUS";

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
    public static final String ORDER_COL_DATE="NGAYTAO";
    public static final String ORDER_COL_STATUS="TRANGTHAI";
    public static final String ORDER_COL_QUANTITY="SOLUONG";
    public static final String ORDER_COL_TOTAL="THANHTIEN";
    public static final String ORDER_COL_ACT_ID="MATK";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Table Account
        String sql1="CREATE TABLE IF NOT EXISTS " +ACCOUNT_TB_NAME +"("+ ACCOUNT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_COL_USERNAME + " VARCHAR(100), " + ACCOUNT_COL_NUMBER + " TEXT," + ACCOUNT_COL_PASSWORD + " TEXT," + ACCOUNT_COL_OTP + " INTEGER," + ACCOUNT_COL_STATUS + " INTEGER)";

        //Table Customer
        String sql2="CREATE TABLE IF NOT EXISTS " +CUSTOMER_TB_NAME +"("+ CUSTOMER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                      CUSTOMER_COL_NAME+ " TEXT, " + CUSTOMER_COL_BIRTHDAY + " DATE, "+ CUSTOMER_COL_EMAIL + " VARCHAR(100),"+ CUSTOMER_COL_NUMBER + " TEXT,"+
                      CUSTOMER_COL_ADDRESS + " VARCHAR(200), " + CUSTOMER_COL_PHOTO + " BLOB, " + CUSTOMER_COL_ACT_ID +" INTEGER REFERENCES " + ACCOUNT_TB_NAME + "(" + ACCOUNT_COL_ID +")" +")";

        //Table Order
        String sql3= "CREATE TABLE IF NOT EXISTS " +ORDER_TB_NAME +"("+ ORDER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                     ORDER_COL_NAME+ " VARCHAR(200), " + ORDER_COL_DATE + " DATE, "+ ORDER_COL_STATUS + " VARCHAR(100), "+ ORDER_COL_QUANTITY + " INTEGER, "+ ORDER_COL_TOTAL + " REAL, "  + ORDER_COL_ACT_ID +" INTEGER REFERENCES " + ACCOUNT_TB_NAME + "(" + ACCOUNT_COL_ID +")" +")";

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
            //Thêm Test thử
            queryExec("INSERT INTO "+CUSTOMER_TB_NAME+" VALUES(null,'Lâm Hữu Gia','LAPTOP',12,'KTX Khu B ĐHQG TP. HCM',0978362814,null,1)");
            queryExec("INSERT INTO "+CUSTOMER_TB_NAME+" VALUES(null,'Trần Thị Mỹ Thảo','LAPTOP22','thaotran123@gmail.com',0978362814,'KTX Khu B ĐHQG TP. HCM',null,2)");
        }
    }

    //Customer
    public boolean insertCustomerData(String name, String birthday,String email, String phone, String address, byte[] photo, int MAKH){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + CUSTOMER_TB_NAME + " VALUES(null,?,?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, name);
            statement.bindString(2,birthday);
            statement.bindString(3, email);
            statement.bindString(4,phone);
            statement.bindString(5, address);
            statement.bindBlob(6, photo);
            statement.bindDouble(7, MAKH);
            statement.executeInsert();

            return true;
        } catch (Exception Ex){
            return false;
        }
    }

    public boolean updateCustomerData(String name,String birthday, String email, String phone, String address,byte[] photo, int MATK){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(CUSTOMER_COL_NAME, name);
        contentValues.put(CUSTOMER_COL_BIRTHDAY,birthday);
        contentValues.put(CUSTOMER_COL_EMAIL,email);
        contentValues.put(CUSTOMER_COL_NUMBER,phone);
        contentValues.put(CUSTOMER_COL_ADDRESS,address);
        contentValues.put(CUSTOMER_COL_PHOTO, photo);

        db.update(CUSTOMER_TB_NAME,contentValues,CUSTOMER_COL_ID + " = ? ", new String[]{ MATK +""});
        return true;
    }

    //Order
    public boolean insertOrderData(String name, String date, String status, int quantity, double total , int MATK){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = " INSERT INTO " + ORDER_TB_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, name);
            statement.bindString(2, date);
            statement.bindString(3, status);
            statement.bindDouble(4, quantity);
            statement.bindDouble(5, total);
            statement.bindDouble(6, MATK);

            statement.executeInsert();
            return true;
        } catch (Exception Ex){
            return false;
        }
    }

    //Account
    public boolean insertAccountData(String username, String phone, String password, int OTP, int status){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = " INSERT INTO " + ACCOUNT_TB_NAME + " VALUES(null, ?, ?, ?, ?,?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, username);
            statement.bindString(2, phone);
            statement.bindString(3, password);
            statement.bindDouble(4, OTP);
            statement.bindDouble(5, status);

            statement.executeInsert();
            return true;
        } catch (Exception Ex){
            return false;
        }
    }

    public boolean updateAccountStatus(int status, int MATK){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(ACCOUNT_COL_STATUS, status);
        db.update(ACCOUNT_TB_NAME,contentValues,ACCOUNT_COL_ID + " = ? ", new String[]{ MATK +""});
        return true;
    }

    public boolean updatePassword(String newpass, int MATK){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(ACCOUNT_COL_PASSWORD, newpass);
        db.update(ACCOUNT_TB_NAME,contentValues,ACCOUNT_COL_ID + " = ? ", new String[]{ MATK +""});
        return true;
    }

    public boolean isUserNameExists (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TB_NAME,
                new String[]{ACCOUNT_COL_ID, ACCOUNT_COL_USERNAME, ACCOUNT_COL_NUMBER, ACCOUNT_COL_PASSWORD, ACCOUNT_COL_OTP, ACCOUNT_COL_STATUS},
                ACCOUNT_COL_USERNAME + "=?",
                new String[]{username},
                null,null,null);
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public User Authenticate(User user) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TB_NAME,// Selecting Table
                new String[]{ACCOUNT_COL_ID, ACCOUNT_COL_USERNAME, ACCOUNT_COL_NUMBER, ACCOUNT_COL_PASSWORD, ACCOUNT_COL_OTP, ACCOUNT_COL_STATUS},//Selecting columns want to query
                ACCOUNT_COL_USERNAME + "=?",
                new String[]{user.getUserName()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5));
            //kiểm tra mật khẩu
            if (user.getUserPassword().equalsIgnoreCase(user1.getUserPassword())) {
                return user1;
            }
        }
        return null;
    }

}
