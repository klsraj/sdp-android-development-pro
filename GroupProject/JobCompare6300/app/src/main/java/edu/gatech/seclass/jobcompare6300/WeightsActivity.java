package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightsActivity extends AppCompatActivity {

    private Button button_cancel_weight;
    private Button button_save_weight;
    private EditText commute_time;
    private EditText yearly_salary;
    private EditText yearly_bonus;
    private EditText retirement;
    private EditText leave;

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
                saveWeight(view);
                Intent intent = new Intent(WeightsActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Entered weights saved",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void saveWeight(View view){
        commute_time = (EditText) findViewById(R.id.text_commute_weight);
        int commute_timeText = Integer.parseInt(commute_time.getText().toString());

        yearly_salary = (EditText) findViewById(R.id.text_salary_weight);
        int salary = Integer.parseInt(yearly_salary.getText().toString());

        yearly_bonus = (EditText) findViewById(R.id.text_bonus_weight);
        int bonus = Integer.parseInt(yearly_bonus.getText().toString());

        retirement = (EditText) findViewById(R.id.text_retirement_weight);
        int retirementValue = Integer.parseInt(retirement.getText().toString());

        leave = (EditText) findViewById(R.id.text_leave_weight);
        int leaveValue = Integer.parseInt(leave.getText().toString());

        Weights.setCommute_weight(commute_timeText);
        Weights.setSalary_weight(salary);
        Weights.setBonus_weight(bonus);
        Weights.setRetirement_weight(retirementValue);
        Weights.setLeave_weight(leaveValue);

        Log.v("Text",Weights.printWeights());
    }
}