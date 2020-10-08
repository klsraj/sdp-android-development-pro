package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_job_current;
    private Button button_job;
    private Button button_weights;
    private Button button_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_job_current = findViewById(R.id.button_job_current);
        button_job_current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JobCurrent.class);
                startActivity(intent);
            }
        });

        button_job = findViewById(R.id.button_job);
        button_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JobActivity.class);
                startActivity(intent);
            }
        });

        button_weights = findViewById(R.id.button_weights);
        button_weights.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeightsActivity.class);
                startActivity(intent);
            }
        });

        button_ranking = findViewById(R.id.button_ranking);
        button_ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });
    }
}