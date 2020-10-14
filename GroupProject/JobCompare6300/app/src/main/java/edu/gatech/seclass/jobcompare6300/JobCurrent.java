package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        button_save_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the entered details of the current job goes here
                //
                String job = text_job_current.getText().toString();
                String company = text_company_current.getText().toString();
                String location = text_location_current.getText().toString();
                String col = text_col_current.getText().toString();
                String commute = text_commute_current.getText().toString();
                String salary = text_salary_current.getText().toString();
                String bonus = text_bonus_current.getText().toString();
                String retirement = text_retirement_current.getText().toString();
                String leave = text_leave_current.getText().toString();
                Job myjob = new Job();
                myjob.setJob(job);
                myjob.setCompany(company);
                myjob.setLocation(location);
                myjob.setCol(col);
                myjob.setCommute(commute);
                myjob.setSalary(salary);
                myjob.setBonus(bonus);
                myjob.setRetirement(retirement);
                myjob.setLeave(leave);

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