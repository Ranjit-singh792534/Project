package com.example.assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {
//Variable
    public static String DATABASE_NAME = "Users";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "User";
    public static final String USER_DETAILS = "Userdetails";
    public static final String KEY_ID = "_id";
    public static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    public static final String CONTACT_NAME = "contactname";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
//Create userdetails table
    private static final String CREATE_TABLE_USER_DETAILS= "CREATE TABLE "
            + USER_DETAILS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CONTACT_NAME + " TEXT NOT NULL ,"+
            PHONE + " TEXT NOT NULL ,"+
            EMAIL + " TEXT NOT NULL "+
            "); ";
//create user table
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USER_NAME + " TEXT NOT NULL ,"+
            PASSWORD + " TEXT NOT NULL ,"+
            EMAIL + " TEXT NOT NULL "+
            "); ";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table2", CREATE_TABLE_USER);
        Log.d("table", CREATE_TABLE_USER_DETAILS);




    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + USER_DETAILS + "'");
        onCreate(db);
    }
    //add user detail
    public long addUserDetail(String contactname,String phone,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, contactname);
        values.put(PHONE, phone);
        values.put(EMAIL, email);
        long insert = db.insert(USER_DETAILS, null, values);

        return insert;
    }
    //Registration
    public long Registration(String username,String password,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, username);
        values.put(PASSWORD, password);
        values.put(EMAIL, email);
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }
    //login
    public boolean checkUser(String username, String password,String email) {
        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USER_NAME + " = ?" + " AND " + PASSWORD + " = ?" + " AND " + EMAIL + " = ?";
        String[] selectionArgs = {username,password,email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    //Read Data
    Cursor readAllData(){
        String query = "SELECT * FROM " +USER_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db !=null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }
}