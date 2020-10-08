package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobCurrent extends AppCompatActivity {

    private Button button_cancel_job_current;
    private Button button_save_job_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_current);

        button_cancel_job_current = findViewById(R.id.button_cancel_job_current);
        button_cancel_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(JobCurrent.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Current job entry cancelled",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        button_save_job_current = findViewById(R.id.button_save_job_current);
        button_save_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the current job goes here
                //
                Intent intent = new Intent(JobCurrent.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Current job entry saved",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}