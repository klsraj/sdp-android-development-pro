package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcomparestorage.JobsDBHelper;
import edu.gatech.seclass.jobcomparestorage.JobsDBManager;
import edu.gatech.seclass.jobcomparestorage.WeightsDBHelper;

public class JobCurrent extends AppCompatActivity {

    private Button button_cancel_job_current;
    private Button button_save_job_current;

    private EditText text_job_current;
    private EditText text_company_current;
    private EditText text_location_current;
    private EditText text_col_current;
    private EditText text_commute_current;
    private EditText text_salary_current;
    private EditText text_bonus_current;
    private EditText text_retirement_current;
    private EditText text_leave_current;

    private JobsDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_current);

        text_job_current = (EditText) findViewById(R.id.text_job_current);
        text_company_current = (EditText) findViewById(R.id.text_company_current);
        text_location_current = (EditText) findViewById(R.id.text_location_current);
        text_col_current = (EditText) findViewById(R.id.text_col_current);
        text_commute_current = (EditText) findViewById(R.id.text_commute_current);
        text_salary_current = (EditText) findViewById(R.id.text_salary_current);
        text_bonus_current = (EditText) findViewById(R.id.text_bonus_current);
        text_retirement_current = (EditText) findViewById(R.id.text_retirement_current);
        text_leave_current = (EditText) findViewById(R.id.text_leave_current);
        button_save_job_current = findViewById(R.id.button_save_job_current);

        dbManager = new JobsDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchCurrent();

        if(cursor.getCount() > 0){
            text_job_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.job)));
            text_company_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.company)));
            text_location_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.location)));
            text_col_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.col)));
            text_commute_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.commute)));
            text_salary_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.salary)));
            text_bonus_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.bonus)));
            text_retirement_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.retirement)));
            text_leave_current.setText(cursor.getString(cursor.getColumnIndex(JobsDBHelper.leave)));
        }

        button_cancel_job_current = findViewById(R.id.button_cancel_job_current);
        button_cancel_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(JobCurrent.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Current job entry cancelled",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        button_save_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the current job goes here
                saveInDB(view);
                Intent intent = new Intent(JobCurrent.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Job offer entry saved",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
    public void saveInDB(View view) {

        String job = text_job_current.getText().toString();
        String company = text_company_current.getText().toString();
        String location = text_location_current.getText().toString();
        int col = Integer.parseInt(text_col_current.getText().toString());
        int commute = Integer.parseInt(text_commute_current.getText().toString());
        int salary = Integer.parseInt(text_salary_current.getText().toString());
        int bonus = Integer.parseInt(text_bonus_current.getText().toString());
        int retirement = Integer.parseInt(text_retirement_current.getText().toString());
        int leave = Integer.parseInt(text_leave_current.getText().toString());

        Log.v("Text","Trying to Save in DB");

        if (dbManager.fetchCurrent().getCount() > 0) {
            dbManager.update(1337, job, company, location, col, commute, salary, bonus, retirement, leave, 1);
        }
        else {
            dbManager.insert(1337, job, company, location, col, commute, salary, bonus, retirement, leave, 1);
        }
    }
}