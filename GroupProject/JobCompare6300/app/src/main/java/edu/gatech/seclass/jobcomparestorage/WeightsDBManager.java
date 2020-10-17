package edu.gatech.seclass.jobcomparestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class WeightsDBManager {

    private WeightsDBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public WeightsDBManager(Context c) {
        context = c;
    }

    public WeightsDBManager open() throws SQLException {
        dbHelper = new WeightsDBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int id, int commute_weight, int salary_weight, int bonus_weight, int retirement_weight, int leave_weight) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(WeightsDBHelper._ID, id);
        contentValue.put(WeightsDBHelper.commute_weight, commute_weight);
        contentValue.put(WeightsDBHelper.salary_weight, salary_weight);
        contentValue.put(WeightsDBHelper.bonus_weight, bonus_weight);
        contentValue.put(WeightsDBHelper.retirement_weight, retirement_weight);
        contentValue.put(WeightsDBHelper.leave_weight, leave_weight);
        database.insert(WeightsDBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { WeightsDBHelper._ID, WeightsDBHelper.commute_weight, WeightsDBHelper.salary_weight, WeightsDBHelper.bonus_weight, WeightsDBHelper.retirement_weight, WeightsDBHelper.leave_weight };
        Cursor cursor = database.query(WeightsDBHelper.TABLE_NAME, columns, "_ID = 100", null, null, null, null);
        if (cursor != null) {
            Log.v("Text","Inside If");
            cursor.moveToFirst();
            Log.v("Text","If 2nd line");
        }
        return cursor;
    }

    public int update(int _id, int commute_weight, int salary_weight, int bonus_weight, int retirement_weight, int leave_weight) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WeightsDBHelper.commute_weight, commute_weight);
        contentValues.put(WeightsDBHelper.salary_weight, salary_weight);
        contentValues.put(WeightsDBHelper.bonus_weight, bonus_weight);
        contentValues.put(WeightsDBHelper.retirement_weight, retirement_weight);
        contentValues.put(WeightsDBHelper.leave_weight, leave_weight);
        int i = database.update(WeightsDBHelper.TABLE_NAME, contentValues, WeightsDBHelper._ID + " = " + _id, null);

        return i;
    }

    public void delete(long _id) {
        database.delete(WeightsDBHelper.TABLE_NAME, WeightsDBHelper._ID + "=" + _id, null);
    }

    public void reset() {
        database.execSQL("DELETE FROM " + WeightsDBHelper.TABLE_NAME + ";");
    }

}