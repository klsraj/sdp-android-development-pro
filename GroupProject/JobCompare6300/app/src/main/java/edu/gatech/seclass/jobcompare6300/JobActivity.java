package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobActivity extends AppCompatActivity {

    private Button button_cancel_job;
    private Button button_save_job;
    private Button button_compare_job;
    private Button button_another_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

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
                Intent intent = new Intent(JobActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Job offer entry saved",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        button_compare_job = findViewById(R.id.button_compare_job);
        button_compare_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the job offer goes here
                // Pass this job to be compared in the comparison activity
                Intent intent = new Intent(JobActivity.this, ComparisonActivity.class);
                startActivity(intent);
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
}