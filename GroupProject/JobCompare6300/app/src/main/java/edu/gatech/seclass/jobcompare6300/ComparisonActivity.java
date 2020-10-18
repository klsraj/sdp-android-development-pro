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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        String[] j1 = getIntent().getStringArrayExtra("test1");
        String[] j2 = getIntent().getStringArrayExtra("test2");

        loadComparison(j1, j2);

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

    private void loadComparison(String[] job1, String[] job2) {

        // Example filler right now for visualization. Pass an array in containing the jobs sorted
        // by rank
        String[] headers = {"Metric", "Job", "Job"};
        String[] labels = {"Job Title", "Company", "Location", "Commute Time", "Adj. Yearly Salary", "Adj. Yearly Bonus", "Retirement Benefits", "Leave Time"};

        TableLayout table_job_comparison = (TableLayout) findViewById(R.id.table_job_comparison);
        table_job_comparison.setStretchAllColumns(true);
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        String[] job1_compare = {job1[1], job1[2], job1[3], job1[5], job1[6], job1[7], job1[8], job1[9], job1[11]};
        String[] job2_compare = {job2[1], job2[2], job2[3], job2[5], job2[6], job2[7], job2[8], job2[9], job2[11]};

        for (int i = 0; i < headers.length; i++) {
            TextView textview = new TextView(this);
            textview.setTypeface(null, Typeface.BOLD);
            textview.setText(headers[i]);
            row.addView(textview);
        }
        table_job_comparison.addView(row, 0);

        for (int i=0; i < labels.length; i++) {
            row = new TableRow(this);

            TextView textview = new TextView(this);
            textview.setText(labels[i]);
            row.addView(textview);

            textview = new TextView(this);
            textview.setText(job1_compare[i]);
            row.addView(textview);

            textview = new TextView(this);
            textview.setText(job2_compare[i]);
            row.addView(textview);

            table_job_comparison.addView(row, i+1);
        }
    }
}