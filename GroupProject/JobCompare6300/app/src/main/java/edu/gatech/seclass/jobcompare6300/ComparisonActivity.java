package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.jobcomparestorage.JobsDBHelper;
import edu.gatech.seclass.jobcomparestorage.JobsDBManager;
import edu.gatech.seclass.jobcomparestorage.WeightsDBHelper;
import edu.gatech.seclass.jobcomparestorage.WeightsDBManager;

public class ComparisonActivity extends AppCompatActivity {

    private Button button_cancel_comparison;
    private Button button_ranking_comparison;
    private JobsDBManager jobsDB;
    private WeightsDBManager weightsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        int Job1 = getIntent().getIntExtra("job1",0);
        int Job2 = getIntent().getIntExtra("job2",1);

        Log.v("Text","Inside Comparison");
        Log.v("Text","Job1 : "+Job1);
        Log.v("Text","Job2 : "+Job2);

        loadComparison(Job1,Job2);

        button_cancel_comparison = findViewById(R.id.button_cancel_comparison);
        button_cancel_comparison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ComparisonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button_ranking_comparison = findViewById(R.id.button_ranking_comparison);
        button_ranking_comparison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ComparisonActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadComparison(int Job1, int Job2) {

        // Example filler right now for visualization. Pass an array in containing the jobs sorted
        // by rank
        String[] headers = {"Metric", "Job", "Job"};
        String[] labels = {"Job Title", "Company", "Location", "Commute Time", "Yearly Salary", "Yearly Bonus", "Retirement Benefits", "Leave Time"};
        String[] example_job1 = {"Software Developer I", "Amazon", "New York, NY", "30", "100000", "20000", "3", "15"};
        String[] example_job2 = {"Test Engineer", "Georgia Tech", "Atlanta, GA", "10", "80000", "10000", "8", "20"};

        String[] job1 = getJobfromDB(Job1);
        String[] job2 = getJobfromDB(Job2);

        double job1Score = getJobScore(Integer.parseInt(job1[4]), Integer.parseInt(job1[5]), Integer.parseInt(job1[6]), Integer.parseInt(job1[7]), Integer.parseInt(job1[3]));
        double job2Score = getJobScore(Integer.parseInt(job2[4]), Integer.parseInt(job2[5]), Integer.parseInt(job2[6]), Integer.parseInt(job2[7]), Integer.parseInt(job2[3]));

        if(job1Score > job2Score){
            example_job1 = job1;
            example_job2 = job2;
        }
        else{
            example_job1 = job2;
            example_job2 = job1;
        }

        TableLayout table_job_comparison = (TableLayout) findViewById(R.id.table_job_comparison);



        table_job_comparison.setStretchAllColumns(true);

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        for (int i = 0; i < headers.length; i++) {
            TextView textview = new TextView(this);
            textview.setTypeface(null, Typeface.BOLD);
            textview.setText(headers[i]);
            row.addView(textview);
        }
        table_job_comparison.addView(row, 0);

        for (int i = 0; i < labels.length; i++) {
            row = new TableRow(this);

            TextView textview = new TextView(this);
            textview.setText(labels[i]);
            row.addView(textview);

            textview = new TextView(this);
            textview.setText(example_job1[i]);
            row.addView(textview);

            textview = new TextView(this);
            textview.setText(example_job2[i]);
            row.addView(textview);

            table_job_comparison.addView(row, i+1);
        }
    }

    private String[] getJobfromDB(int jobId) {
        jobsDB = new JobsDBManager(this);
        jobsDB.open();
        String[] job = {"","","","","","","",""};
        Cursor cursor = jobsDB.fetchById(1337+jobId);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            job[0] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.job));
            job[1] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.company));
            job[2] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.location));
            job[3] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.commute));
            job[4] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.salary));
            job[5] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.bonus));
            job[6] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.retirement));
            job[7] = cursor.getString(cursor.getColumnIndex(JobsDBHelper.leave));
        }

        return job;
    }

    public double getJobScore(int AYS ,int AYB, int RBP, int LT, int CT){

        weightsDB = new WeightsDBManager(this);
        weightsDB.open();

        int commute= 0;
        int salary= 0;
        int retirement= 0;
        int bonus= 0;
        int leave= 0;

        Cursor cursor = weightsDB.fetch();

        if(cursor.getCount() > 0) {
            Log.v("Text", "Getting Weights");
            commute = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.commute_weight)));
            salary = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.salary_weight)));
            retirement = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.retirement_weight)));
            bonus = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.bonus_weight)));
            leave = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.leave_weight)));
        }

        double jobScore = 0;
        int weightTotal = bonus + salary + commute + leave+ retirement;
        jobScore = ((salary/weightTotal) * AYS) + ((bonus/weightTotal) * AYB) + ( (retirement/weightTotal) * (RBP * AYS))
                + ( (leave/weightTotal) * (LT * AYS / 260) ) - ( (commute/weightTotal) * (CT * AYS / 8) );
        return jobScore;
    }
}