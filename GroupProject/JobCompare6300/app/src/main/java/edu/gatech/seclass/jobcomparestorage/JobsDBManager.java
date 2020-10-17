package edu.gatech.seclass.jobcomparestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class JobsDBManager {

    private JobsDBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public JobsDBManager(Context c) {
        context = c;
    }

    public JobsDBManager open() throws SQLException {
        dbHelper = new JobsDBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int id, String job, String company, String location, int col, double commute, int salary, int bonus, int retirement, int leave, int current) {
        ContentValues contentValue = new ContentValues();
        if (current > 0) {
            contentValue.put(JobsDBHelper._ID, id);
        }
        contentValue.put(JobsDBHelper.job, job);
        contentValue.put(JobsDBHelper.company, company);
        contentValue.put(JobsDBHelper.location, location);
        contentValue.put(JobsDBHelper.col, col);
        contentValue.put(JobsDBHelper.commute, commute);
        contentValue.put(JobsDBHelper.salary, salary);
        contentValue.put(JobsDBHelper.bonus, bonus);
        contentValue.put(JobsDBHelper.retirement, retirement);
        contentValue.put(JobsDBHelper.leave, leave);
        contentValue.put(JobsDBHelper.current, current);
        database.insert(JobsDBHelper.TABLE_NAME, null, contentValue);
    }

//    public Cursor fetch() {
//        String[] columns = new String[] { JobsDBHelper._ID, JobsDBHelper.job, JobsDBHelper.company, JobsDBHelper.location, JobsDBHelper.col, JobsDBHelper.commute, JobsDBHelper.salary, JobsDBHelper.bonus, JobsDBHelper.retirement, JobsDBHelper.leave, JobsDBHelper.current };
//        Cursor cursor = database.query(JobsDBHelper.TABLE_NAME, columns, "_ID = 500", null, null, null, null);
//        if (cursor != null) {
//            Log.v("Text","Inside If");
//            cursor.moveToFirst();
//            Log.v("Text","If 2nd line");
//        }
//        return cursor;
//    }

    public Cursor fetchCurrent() {
        String[] columns = new String[] { JobsDBHelper._ID, JobsDBHelper.job, JobsDBHelper.company, JobsDBHelper.location, JobsDBHelper.col, JobsDBHelper.commute, JobsDBHelper.salary, JobsDBHelper.bonus, JobsDBHelper.retirement, JobsDBHelper.leave, JobsDBHelper.current };
        Cursor cursor = database.query(JobsDBHelper.TABLE_NAME, columns, "_ID = 1337", null, null, null, null);
        if (cursor != null) {
            Log.v("Text","Inside If");
            cursor.moveToFirst();
            Log.v("Text","If 2nd line");
        }
        return cursor;
    }

    public Cursor fetchById(int id) {
        String[] columns = new String[] { JobsDBHelper._ID, JobsDBHelper.job, JobsDBHelper.company, JobsDBHelper.location, JobsDBHelper.col, JobsDBHelper.commute, JobsDBHelper.salary, JobsDBHelper.bonus, JobsDBHelper.retirement, JobsDBHelper.leave, JobsDBHelper.current };
        Cursor cursor = database.query(JobsDBHelper.TABLE_NAME, columns, "_ID = " + id, null, null, null, null);
        if (cursor != null) {
            Log.v("Text","Inside If");
            cursor.moveToFirst();
            Log.v("Text","If 2nd line");
        }
        return cursor;
    }

    public Cursor getAllData() {
        Cursor cursor = database.rawQuery("Select * from " + JobsDBHelper.TABLE_NAME, null);
        return cursor;
    }

    public int update(int _id, String job, String company, String location, int col, double commute, int salary, int bonus, int retirement, int leave, int current) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobsDBHelper.job, job);
        contentValues.put(JobsDBHelper.company, company);
        contentValues.put(JobsDBHelper.location, location);
        contentValues.put(JobsDBHelper.col, col);
        contentValues.put(JobsDBHelper.commute, commute);
        contentValues.put(JobsDBHelper.salary, salary);
        contentValues.put(JobsDBHelper.bonus, bonus);
        contentValues.put(JobsDBHelper.retirement, retirement);
        contentValues.put(JobsDBHelper.leave, leave);
        contentValues.put(JobsDBHelper.current, current);
        int i = database.update(JobsDBHelper.TABLE_NAME, contentValues, JobsDBHelper._ID + " = " + _id, null);

        return i;
    }

    public void delete(long _id) {
        database.delete(JobsDBHelper.TABLE_NAME, JobsDBHelper._ID + "=" + _id, null);
    }

    public void reset() {
        database.execSQL("DELETE FROM  " + JobsDBHelper.TABLE_NAME + ";");
    }

}