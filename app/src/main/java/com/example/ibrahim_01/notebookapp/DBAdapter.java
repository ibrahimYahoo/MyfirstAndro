package com.example.ibrahim_01.notebookapp;

/**
 * Created by ibrahim-01 on 1/8/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ibrahim-01 on 12/3/2016.
 */
public class DBAdapter {

    static  final  String COL_ID = "id";
    static  final  String COL_NAME = "name";
    static  final  String COL_PASSWORD = "password";
    static  final  String COL_ADDRESS = "address";


    static  final  String DATABASE_NAME = "Mydb";
    static  final  int DATABASE_VERSION = 1;
    static  final  String TABLE_NAME = "contacts";
    static  final  String TAG = "DBAdapter";



    static  final  String CREATE_TABLE_CONTACTS_QUERY = " create table " + TABLE_NAME +
            "(id integer primary key autoincrement, name text not null, password text not null, address text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_CONTACTS_QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    public long insertContact(String name, String password, String address) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_NAME, name);
        initialValues.put(COL_PASSWORD, password);
        initialValues.put(COL_ADDRESS, address);


        return db.insert(TABLE_NAME, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId) {
        return db.delete(TABLE_NAME, COL_ID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(TABLE_NAME, new String[]{COL_ID, COL_NAME,
                COL_PASSWORD,COL_ADDRESS}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getContact(String name, String password) throws SQLException {

        Cursor mCursor = db.rawQuery("Select * from contacts where name = ? AND password = ? ", new String[] {name,password});
//                db.query(true, TABLE_NAME, new String[]{
//                                COL_NAME, COL_PASSWORD}, COL_NAME + " = " + name +  " and " +  COL_PASSWORD + " = " + password, null,
//                        null, null, null, null);
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String name, String email, String address) {
        ContentValues args = new ContentValues();
        args.put(COL_NAME, name);
        args.put(COL_PASSWORD, email);
        args.put(COL_ADDRESS, address);


        return db.update(TABLE_NAME, args, COL_ID + "=" + rowId, null) > 0;
    }




}
