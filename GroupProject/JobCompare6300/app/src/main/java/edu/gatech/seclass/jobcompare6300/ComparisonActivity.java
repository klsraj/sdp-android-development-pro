package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ComparisonActivity extends AppCompatActivity {

    private Button button_cancel_comparison;
    private Button button_ranking_comparison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        loadComparison();

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

    public void loadComparison() {
        TableLayout table_job_comparison = (TableLayout) findViewById(R.id.table_job_comparison);

        // Example filler right now for visualization. Pass an array in containing the jobs sorted
        // by rank
        String[] headers = {"Metric", "Job", "Job"};
        String[] labels = {"Job Title", "Company", "Location", "Commute Time", "Yearly Salary", "Yearly Bonus", "Retirement Benefits", "Leave Time"};
        String[] example_job1 = {"Software Developer I", "Amazon", "New York, NY", "30", "100000", "20000", "3", "15"};
        String[] example_job2 = {"Test Engineer", "Georgia Tech", "Atlanta, GA", "10", "80000", "10000", "8", "20"};

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

}