package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WeightsActivity extends AppCompatActivity {

    private Button button_cancel_weight;
    private Button button_save_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        button_cancel_weight = findViewById(R.id.button_cancel_weight);
        button_cancel_weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WeightsActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Entered weights cancelled",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        button_save_weight = findViewById(R.id.button_save_weight);
        button_save_weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the user-entered weights here
                //
                Intent intent = new Intent(WeightsActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Entered weights saved",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}