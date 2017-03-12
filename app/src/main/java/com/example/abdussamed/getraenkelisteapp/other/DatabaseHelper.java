package com.example.abdussamed.getraenkelisteapp.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abdussamed on 1/20/2017.
 *
 *
 * Local Storage Database. Everything should be copied first locally and when internet available
 * uploaded to online database. Every Transaction is done via internet Connection
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "getraenkeliste.db";

    private static final String TABLE_Users = "users";

/*    private static final String TABLE_Gusers = "Gusers";  // Gusers stands for generall users; most
                                                         // users are classified as Gusers;
                                                         // cardserial is used
    private static final String TABLE_Busers = "Busers";  // Busers stand for blacklisted user; some
                                                          //users let their nfc card in front of
                                                          //everyone due to their tasks; busers cant
                                                          //log in via their student cards
    private static final String TABLE_Ausers = "Ausers";  //Ausers stand for administrator users;
                                                         // they have the rights to add/delete beve-
                                                         //rages, edit their prices, and so on.
                                                        // (still working on additional privileges)*/
    private static final String TABLE_Beverages = "beverages"; //table for holding beverages, more
                                                                // more infos about beverages found
                                                                //in the Beverage.java class
    // Common Column Names (in all tables)
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    // users Table- column names
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CARDSERIAL  = "cardserial";
    private static final String COLUMN_PRIVILEGE  = "privilege";  // three levels of privilege
                                                                // available: 0,1,2.
                                                                //0=Gusers; 1=Busers; 2=Ausers;
                                                             //definition is above, of each usertype

   /* // Gusers, Busers, Ausers Table- column names
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CARDSERIAL  = "cardserial";*/

    // beverages table - column names
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "pass";

    private static final String TABLE_CREATE0 = "CREATE TABLE " + TABLE_Users + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASS + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_CARDSERIAL + " TEXT,"
            + COLUMN_PRIVILEGE + " INTEGER" + ")";

  /*  private static final String TABLE_CREATE0 = "CREATE TABLE " + TABLE_Gusers + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PASS + " TEXT,"
            + COLUMN_EMAIL + " TEXT," + COLUMN_CARDSERIAL + " Text" + ")";

    // didnt create a cardserial column since these users arnt using it anyway
    private static final String TABLE_CREATE1 = "CREATE TABLE " + TABLE_Busers + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PASS + " TEXT,"
            + COLUMN_EMAIL + " TEXT" + ")";

    private static final String TABLE_CREATE2 = "CREATE TABLE " + TABLE_Ausers + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PASS + " TEXT,"
            + COLUMN_EMAIL + " TEXT," + COLUMN_CARDSERIAL + " Text" + ")";
  */

    private static final String TABLE_CREATE3 = "CREATE TABLE " + TABLE_Beverages + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_CATEGORY + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PRICE + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Gusers);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Busers);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Ausers);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Beverages);
        this.onCreate(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE0);
        //sqLiteDatabase.execSQL(TABLE_CREATE1);
        //sqLiteDatabase.execSQL(TABLE_CREATE2);
        sqLiteDatabase.execSQL(TABLE_CREATE3);
        this.db =sqLiteDatabase;
    }

    public void insertUser(User user){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getFname());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASS, user.getPass());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_CARDSERIAL, user.getCardserial());
        values.put(COLUMN_PRIVILEGE, user.getPrivilege());

        db.insert(TABLE_Users, null, values);

    }
    public String searchPass(String username){

        db = this.getReadableDatabase();
        String query = "SELECT "+COLUMN_USERNAME +", "+COLUMN_PASS+" FROM "+TABLE_Users;
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
