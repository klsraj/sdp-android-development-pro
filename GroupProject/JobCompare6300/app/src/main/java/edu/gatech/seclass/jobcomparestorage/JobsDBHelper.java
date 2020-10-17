package edu.gatech.seclass.jobcomparestorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobsDBHelper extends SQLiteOpenHelper {

    public JobsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Table Name
    public static final String TABLE_NAME = "Jobs";

    // Table columns
    public static final String _ID = "_ID";
    public static final String job = "job";
    public static final String company = "company";
    public static final String location = "location";
    public static final String col = "col";
    public static final String commute = "commute";
    public static final String salary = "salary";
    public static final String bonus = "bonus";
    public static final String retirement = "retirement";
    public static final String leave = "leave";
    public static final String current = "current";

    // Database Information
    static final String DB_NAME = "JOBS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + job + " TEXT , "
            + company + " TEXT , "
            + location
            + " TEXT , "
            + col + " INTEGER , "
            + commute + " REAL , "
            + salary + " INTEGER , "
            + bonus + " INTEGER , "
            + retirement + " INTEGER , "
            + leave + " INTEGER , "
            + current + " INTEGER);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}