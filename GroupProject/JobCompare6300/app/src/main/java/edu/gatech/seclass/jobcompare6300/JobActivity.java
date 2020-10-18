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

        button_cancel_job = findViewById(R.id.button_cancel_job);
        button_cancel_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(JobActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Job offer entry cancelled",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        button_save_job = findViewById(R.id.button_save_job);
        button_save_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                if (hasEmptyFields()) {
                    Toast.makeText(getApplicationContext(),
                            "Please fill in all fields",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    saveInDB(view);
                    Intent intent = new Intent(JobActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),
                            "Job offer entry saved",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        button_compare_job = findViewById(R.id.button_compare_job);
        button_compare_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                // Pass this job to be compared in the comparison activity
                if (hasEmptyFields()) {
                    Toast.makeText(getApplicationContext(),
                            "Please fill in all fields",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    saveInDB(view);
                    Cursor cursor = dbManager.getAllData();

                    int currentJobId = -1;
                    int enteredJobId = -1;
                    int i = 0;

                    while (cursor.moveToNext()) {
                        if (Integer.parseInt(cursor.getString(10)) == 1) {
                            currentJobId = Integer.parseInt(cursor.getString(0));
                            cursor.moveToLast();
                            enteredJobId = Integer.parseInt(cursor.getString(0));
                            break;
                        }
                        Log.v("Text",cursor.getString(0));
                        i++;
                    }

                    if (currentJobId > -1) {
                        Intent intent = new Intent(JobActivity.this, ComparisonActivity.class);
                        intent.putExtra("job1",currentJobId);
                        intent.putExtra("job2",enteredJobId);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(JobActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),
                                "A current job hasn't been entered, please enter one for comparison",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }
        });

        button_another_job = findViewById(R.id.button_another_job);
        button_another_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                if (hasEmptyFields()) {
                    Toast.makeText(getApplicationContext(),
                            "Please fill in all fields",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    saveInDB(view);
                    Intent intent = new Intent(JobActivity.this, JobActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),
                            "Job offer entry saved. Enter another job offer",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    public void saveInDB(View view) {

        String job = text_job.getText().toString();
        String company = text_company.getText().toString();
        String location = text_location.getText().toString();
        int col = Integer.parseInt(text_col.getText().toString());
        double commute = Double.parseDouble(text_commute.getText().toString());
        int salary = Integer.parseInt(text_salary.getText().toString());
        int bonus = Integer.parseInt(text_bonus.getText().toString());
        int retirement = Integer.parseInt(text_retirement.getText().toString());
        int leave = Integer.parseInt(text_leave.getText().toString());

        Log.v("Text","Trying to Save in DB");

        dbManager.insert(1, job, company, location, col, commute, salary, bonus, retirement, leave, 0);
    }

    private boolean hasEmptyFields() {
        int job = text_job.getText().toString().isEmpty() ? 0 : 1;
        int company = text_company.getText().toString().isEmpty() ? 0 : 1;
        int location = text_location.getText().toString().isEmpty() ? 0 : 1;
        int col = text_col.getText().toString().isEmpty() ? 0 : 1;
        int commute = text_commute.getText().toString().isEmpty() ? 0 : 1;
        int salary = text_salary.getText().toString().isEmpty() ? 0 : 1;
        int bonus = text_bonus.getText().toString().isEmpty() ? 0 : 1;
        int retirement = text_retirement.getText().toString().isEmpty() ? 0 : 1;
        int leave = text_leave.getText().toString().isEmpty() ? 0 : 1;

        if ((job * company * location * col * commute * salary * bonus * retirement * leave) == 0)
            return true;
        else
            return false;
    }
}