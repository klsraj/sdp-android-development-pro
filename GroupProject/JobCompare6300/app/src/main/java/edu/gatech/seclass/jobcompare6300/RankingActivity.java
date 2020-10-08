package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class RankingActivity extends AppCompatActivity {

    private Button button_cancel_ranking;
    private Button button_compare_ranking;
    private TableLayout table_job;
    private boolean[] selected_jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        loadRanking();

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
                    startActivity(intent);
                }
                // If more or less than 2 jobs were selected to compare, provide error message
                else {
                    Toast.makeText(getApplicationContext(), "Please select only two jobs to compare", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loadRanking() {
        TableLayout table_job_ranking = (TableLayout) findViewById(R.id.table_job_ranking);

        // Example filler right now for visualization. Pass an array in containing the jobs sorted
        // by rank

        String[] example_jobs = {"Software Developer", "Software Engineer", "Test Engineer"};
        String[] example_companies = {"Amazon", "IBM", "Bloomberg"};

        selected_jobs = new boolean[example_jobs.length];

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

        for (int i = 0; i < example_jobs.length; i++) {

            row = new TableRow(this);
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            checkBox = new CheckBox(this);
            job = new TextView(this);
            company = new TextView(this);

            job.setText(example_jobs[i]);
            company.setText(example_companies[i]);

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
}