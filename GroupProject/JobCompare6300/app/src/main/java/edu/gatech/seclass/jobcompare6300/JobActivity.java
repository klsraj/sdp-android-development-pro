package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcomparestorage.JobsDBManager;

public class JobActivity extends AppCompatActivity {

    private Button button_cancel_job;
    private Button button_save_job;
    private Button button_compare_job;
    private Button button_another_job;

    private EditText text_job;
    private EditText text_company;
    private EditText text_location;
    private EditText text_col;
    private EditText text_commute;
    private EditText text_salary;
    private EditText text_bonus;
    private EditText text_retirement;
    private EditText text_leave;

    private JobsDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        text_job = (EditText) findViewById(R.id.text_job);
        text_company = (EditText) findViewById(R.id.text_company);
        text_location = (EditText) findViewById(R.id.text_location);
        text_col = (EditText) findViewById(R.id.text_col);
        text_commute = (EditText) findViewById(R.id.text_commute);
        text_salary = (EditText) findViewById(R.id.text_salary);
        text_bonus = (EditText) findViewById(R.id.text_bonus);
        text_retirement = (EditText) findViewById(R.id.text_retirement);
        text_leave = (EditText) findViewById(R.id.text_leave);

        dbManager = new JobsDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();



        button_cancel_job = findViewById(R.id.button_cancel_job);
        button_cancel_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(JobActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Job offer entry cancelled",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        button_save_job = findViewById(R.id.button_save_job);
        button_save_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                //
                saveInDB(view);
                Intent intent = new Intent(JobActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Job offer entry saved",
                        Toast.LENGTH_LONG)
                        .show();

                Cursor data = dbManager.fetch();
                String[] test = data.getColumnNames();
                Log.v("Data",String.valueOf(data.getInt(1)));
                Log.v("Data",String.valueOf(data.getInt(2)));
            }
        });

        button_compare_job = findViewById(R.id.button_compare_job);
        button_compare_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                // Pass this job to be compared in the comparison activity
//                Intent intent = new Intent(JobActivity.this, ComparisonActivity.class);
//                startActivity(intent);
                Cursor cursor = dbManager.getAllData();
                if (cursor.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id: " + cursor.getString(0) + "\n");
                    buffer.append("Job: " + cursor.getString(1) + "\n");
                    buffer.append("Company: " + cursor.getString(2) + "\n");
                    buffer.append("Location: " + cursor.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());
            }
        });

        button_another_job = findViewById(R.id.button_another_job);
        button_another_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                //
                Intent intent = new Intent(JobActivity.this, JobActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Enter another job offer",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void saveInDB(View view) {

        int job = Integer.parseInt(text_job.getText().toString());
        int company = Integer.parseInt(text_company.getText().toString());
        int location = Integer.parseInt(text_location.getText().toString());
        int col = Integer.parseInt(text_col.getText().toString());
        int commute = Integer.parseInt(text_commute.getText().toString());
        int salary = Integer.parseInt(text_salary.getText().toString());
        int bonus = Integer.parseInt(text_bonus.getText().toString());
        int retirement = Integer.parseInt(text_retirement.getText().toString());
        int leave = Integer.parseInt(text_leave.getText().toString());

        Log.v("Text","Trying to Save in DB");

        dbManager.insert(1, job, company, location, col, commute, salary, bonus, retirement, leave, 0);

//        Cursor cursor = dbManager.fetch();
//        if(cursor.getCount() > 0) {
//            dbManager.insert(500,job,company,location,col, commute, salary, bonus, retirement, leave, 0);
//            Log.v("Text","Update in DB Complete");
//            Toast.makeText(getApplicationContext(),
//                    "SAVED",
//                    Toast.LENGTH_SHORT)
//                    .show();
//        }
//        else {
//            dbManager.insert(500,job,company,location,col, commute, salary, bonus, retirement, leave, 0);
//            Log.v("Text","Insert in DB Complete");
//            Toast.makeText(getApplicationContext(),
//                    "INSERTED",
//                    Toast.LENGTH_SHORT)
//                    .show();
//        }
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}