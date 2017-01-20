package com.example.abdussamed.getraenkelisteapp.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abdussamed on 1/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PASS + " TEXT,"
            + COLUMN_EMAIL + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db =sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }

    public void insertUser(User user){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PASS, user.getPass());
        values.put(COLUMN_EMAIL, user.getEmail());

        db.insert(TABLE_NAME, null, values);

    }
    public String searchPass(String username){

        db = this.getReadableDatabase();
        String query = "SELECT "+COLUMN_USERNAME +", "+COLUMN_PASS+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String uname,pass;
        pass = "not found";
        if(cursor.moveToFirst()){

            do{
                uname = cursor.getString(0); //0 is the username according to the query in this scope not table structure

                if(uname.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return pass;
    }
}
