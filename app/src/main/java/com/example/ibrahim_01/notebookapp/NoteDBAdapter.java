//package com.example.ibrahim_01.notebookapp;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//
///**
// * Created by ibrahim-01 on 1/6/2017.
// */
//
//public class NoteDBAdapter {
//
//    private static  final  String DATABASE_NAME = "groocceryFruits.db";
//    private static  final  int DATABASE_VERSION = 1;
//
//    private static  final  String TABLE_NAME = "Fruits";
//
//
//
//    private static  final  String COL_ID = "id";
//    private static  final  String COL_DESCRIPTION = "desciption";
//    private static  final  String COL_PRICE = "id";
//    private static  final  String COL_NAME = "name";
//    private static  final  String COL_CATEGORY = "category";
//
//    private static  final  String TAG = "DBAdapter";
//
//    private String[] allColumns = {COL_ID,COL_NAME,COL_DESCRIPTION,COL_PRICE,COL_CATEGORY};
//
//
//    public  static  final String TABLE_CREATE_FRUITS = "create table" + TABLE_NAME + " ( "
//            + COL_ID + "integer primary key autoincrement, "
//            + COL_NAME + "text not null, "
//            + COL_DESCRIPTION + "text not null, "
//            + COL_PRICE + "text not null, "
//            + COL_CATEGORY + "text not null, )";
//
//
//    final Context context;
//    NoteDBAdapter.DatabaseHelper DBHelper;
//    SQLiteDatabase db;
//
//    public NoteDBAdapter(Context ctx) {
//        this.context = ctx;
//        DBHelper = new NoteDBAdapter.DatabaseHelper(context);
//    }
//
//    private static class DatabaseHelper extends SQLiteOpenHelper {
//        DatabaseHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            try {
//                db.execSQL(TABLE_CREATE_FRUITS);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
//                    + newVersion + ", which will destroy all old data");
//                db.execSQL("DROP TABLE IF EXISTS Fruits");
//            onCreate(db);
//        }
//    }
//    //---opens the database---
//    public NoteDBAdapter open() throws SQLException {
//        db = DBHelper.getWritableDatabase();
//        return this;
//    }
//
//    //---closes the database---
//    public void close() {
//        DBHelper.close();
//    }
//
//    public Note createNote(String name, String description, String price,String image){
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(COL_NAME, name);
//        initialValues.put(COL_DESCRIPTION, description);
//        initialValues.put(COL_PRICE, price);
//        initialValues.put(COL_CATEGORY, image);
//
//
//        long insertid =  db.insert(TABLE_NAME, null, initialValues);
//
//        Cursor cursor = db.query(TABLE_NAME,allColumns,COL_ID + " = " + insertid,null,null,null,null );
//
//        cursor.moveToFirst();
//
//        Note newNote = cursorToNote(cursor);
//        cursor.close();
//        return  newNote;
//
//
//    }
//
//
//    public ArrayList<Note> getAllNotes(){
//
//        ArrayList<Note> notes = new ArrayList<Note>();
//
//        Cursor cursor = db.query(TABLE_NAME,allColumns,null,null,null,null,null);
//
//        for(cursor.moveToLast();cursor.isBeforeFirst();cursor.moveToPrevious()){
//
//            Note note = cursorToNote(cursor);
//            notes.add(note);
//        }
//        cursor.close();
//
//        return  notes;
//
//    }
//
//    private Note cursorToNote(Cursor cursor) {
//            Note newNote =  new Note(cursor.getString(1),cursor.getString(2),cursor.getString(3),
//                    Note.Category.valueOf(cursor.getString(4)),cursor.getLong(0));
//
//
//            return  newNote;
//
//    }
//
//
//}
