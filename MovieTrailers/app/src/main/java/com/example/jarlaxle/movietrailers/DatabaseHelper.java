package com.example.jarlaxle.movietrailers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    //Set up database variables
    public static final String DATABASE_NAME = "MovieTrailers.db";
    public static final String TABLE_NAME = "trailers_data";
    public static final Integer DB_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "YT_ID";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "Rating";

    public DatabaseHelper(Context context) {
        super(context, "MovieTrailers.db", null, 1);

    }

    //creates the database on app startup
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, YT_ID TEXT, NAME TEXT, DESCRIPTION TEXT, RATING REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Inserts the data in to the database
    public boolean insertData(String yt_id, String name, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, yt_id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, description);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //gets data from the DB
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return result;
    }

    //removes specified data from the database
    public Integer deleteData(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] { id.toString() });

    }

    //gets specified ID
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    //updates the rating in the DB
    public boolean updateRating(String id, String yt_id, String name, String description, Float rating){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_5 + " = '" + rating + "' WHERE " + COL_3 + " = '" + name + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, id);
        contentValues.put(COL_2, yt_id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, rating);
        return db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id }) >0;


    }


}
