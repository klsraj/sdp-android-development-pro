package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Comparator;

import edu.gatech.seclass.jobcomparestorage.JobsDBHelper;
import edu.gatech.seclass.jobcomparestorage.JobsDBManager;
import edu.gatech.seclass.jobcomparestorage.WeightsDBManager;

public class RankingActivity extends AppCompatActivity {

    private Button button_cancel_ranking;
    private Button button_compare_ranking;
    private TableLayout table_job;
    private boolean[] selected_jobs;

    private JobsDBManager dbManager;
    private WeightsDBManager weightsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        weightsDB = new WeightsDBManager(this);
        weightsDB.open();

        dbManager = new JobsDBManager(this);
        dbManager.open();

        String[][] jobList;
        String[] jobs;
        String[] companies;
        String[] current;


        final Cursor cursor = dbManager.getAllData();
        final int[] idIndex = new int[cursor.getCount()];

        if (cursor.getCount() < 2) {
            Toast.makeText(getApplicationContext(), "Enter at least 1 job offer", Toast.LENGTH_SHORT).show();
        }
        else {
            jobList = new String[cursor.getCount()][11];
            jobs = new String[cursor.getCount()];
            companies = new String[cursor.getCount()];
            current = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                for (int j = 0; j < 11; j++) {
                    jobList[i][j] = cursor.getString(j);
                }

                jobs[i] = cursor.getString(1);
                companies[i] = cursor.getString(2);
                current[i] = cursor.getString(10);
                idIndex[i] = Integer.parseInt(cursor.getString(0));
                Log.v("Text",cursor.getString(0));
                i++;
            }

            jobList = rankJobs(jobList);

            for (int j=0; j<jobList.length; j++) {
                idIndex[j] = Integer.parseInt(jobList[j][0]);
                jobs[j] = jobList[j][1];
                companies[j] = jobList[j][2];
                current[j] = jobList[j][10];
            }

            loadRanking(jobs, companies, current);
        }

        // Return to main menu
        button_cancel_ranking = findViewById(R.id.button_cancel_ranking);
        button_cancel_ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Compare two selected jobs
        button_compare_ranking = findViewById(R.id.button_compare_ranking);
        button_compare_ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Figure out how many jobs were selected to compare
                int selected_count = 0;
                int job_1 = -1;
                int job_2 = -1;

                for (int i = 0; i < selected_jobs.length; i++) {
                    if (selected_jobs[i]) {
                        selected_count++;
                        if (job_1 < 0) {
                            job_1 = i;
                        }
                        else {
                            job_2 = i;
                        }
                    }
                }

                // If exactly 2 jobs were selected to compare, do comparison
                if (selected_count == 2) {
                    // For now, just show which indexes were selected and pass them to compare
                    Toast.makeText(getApplicationContext(), "The indexes are: " + String.valueOf(job_1) + ", " + String.valueOf(job_2), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RankingActivity.this, ComparisonActivity.class);
                    intent.putExtra("job1",idIndex[job_1]);
                    intent.putExtra("job2",idIndex[job_2]);
                    Log.v("Text","Inside Ranking Job1 : "+idIndex[job_1]);
                    Log.v("Text","Inside Ranking Job2 : "+idIndex[job_2]);
                    startActivity(intent);
                }
                // If more or less than 2 jobs were selected to compare, provide error message
                else {
                    Toast.makeText(getApplicationContext(), "Please select only two jobs to compare", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loadRanking(String[] jobs, String[] companies, String[] current) {
        TableLayout table_job_ranking = (TableLayout) findViewById(R.id.table_job_ranking);

        // Example filler right now for visualization. Pass an array in containing the jobs sorted
        // by rank

        selected_jobs = new boolean[jobs.length];

        table_job_ranking.setStretchAllColumns(true);

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        TextView filler = new TextView(this);
        CheckBox checkBox = new CheckBox(this);
        TextView job = new TextView(this);
        TextView company = new TextView(this);

        filler.setText(" ");
        job.setTypeface(null, Typeface.BOLD);
        job.setText("Job Title");
        company.setTypeface(null, Typeface.BOLD);
        company.setText("Company");
        checkBox.setClickable(false);

        row.addView(filler);
        row.addView(job);
        row.addView(company);

        table_job_ranking.addView(row, 0);

        for (int i = 0; i < jobs.length; i++) {

            row = new TableRow(this);
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            checkBox = new CheckBox(this);
            job = new TextView(this);
            company = new TextView(this);

            if (Integer.parseInt(current[i]) == 1) {
                row.setBackgroundColor(Color.rgb(255, 222, 3));
            }

            job.setText(jobs[i]);
            company.setText(companies[i]);

            checkBox.setId(i);
            checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick (View view){
                    if (((CheckBox) view).isChecked()) {
                        selected_jobs[view.getId()] = true;
                    }
                    else {
                        selected_jobs[view.getId()] = false;
                    }
                }
            });

            selected_jobs[i] = false;
            row.addView(checkBox);
            row.addView(job);
            row.addView(company);
            table_job_ranking.addView(row, i+1);
        }
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private String[][] rankJobs(String[][] jobList) {
        Cursor weights = weightsDB.fetch();

        String[][] sortList = new String[jobList.length][12];

        for (int i=0; i<sortList.length; i++) {
            for (int j=0; j<11; j++)
                sortList[i][j] = jobList[i][j];
            sortList[i][11] = getJobScore(jobList[i]);
        }

        Arrays.sort(sortList, new Comparator<String[]>() {
            public int compare(String[] a, String[] b) {
                return a[11].compareTo(b[11]);
            }
        });

        for (int i=0; i<jobList.length; i++) {
            for (int j=0; j<11; j++)
                jobList[i][j] = sortList[i][j];
        }

        return jobList;
    }

    private String getJobScore(String[] job) {
        double commute = Double.parseDouble(job[4]);
        double salary = Double.parseDouble(job[5]);
        double bonus = Double.parseDouble(job[6]);
        double retirement = Double.parseDouble(job[7]);
        double leave = Double.parseDouble(job[8]);

        double commute_weight = Double.parseDouble(weightsDB.fetch().getString(0));
        double salary_weight = Double.parseDouble(weightsDB.fetch().getString(1));
        double bonus_weight = Double.parseDouble(weightsDB.fetch().getString(2));
        double retirement_weight = Double.parseDouble(weightsDB.fetch().getString(3));
        double leave_weight = Double.parseDouble(weightsDB.fetch().getString(4));
        double total_weight = commute_weight + salary_weight + bonus_weight + retirement_weight + leave_weight;

        double jobScore = ((salary_weight/total_weight) * salary) +
                            ((bonus_weight/total_weight) * bonus) +
                            ((retirement_weight/total_weight) * (retirement*.01*salary)) +
                            ((leave_weight/total_weight) * (leave*salary/260)) -
                            ((commute_weight/total_weight) * (commute*salary/8));

        return String.valueOf(jobScore);
    }
}