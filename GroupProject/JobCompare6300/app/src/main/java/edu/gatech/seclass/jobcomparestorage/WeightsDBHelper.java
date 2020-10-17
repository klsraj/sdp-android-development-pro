package edu.gatech.seclass.jobcomparestorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeightsDBHelper extends SQLiteOpenHelper {

    public WeightsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Table Name
    public static final String TABLE_NAME = "Weights";

    // Table columns
    public static final String _ID = "_ID";
    public static final String commute_weight = "commute_weight";
    public static final String salary_weight = "salary_weight";
    public static final String bonus_weight = "bonus_weight";
    public static final String retirement_weight = "retirement_weight";
    public static final String leave_weight = "leave_weight";

    // Database Information
    static final String DB_NAME = "WEIGHTS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY, " + commute_weight + " INTEGER , " + salary_weight + " INTEGER , " + bonus_weight
            + " INTEGER , " + retirement_weight + " INTEGER , " + leave_weight + " INTEGER);";

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