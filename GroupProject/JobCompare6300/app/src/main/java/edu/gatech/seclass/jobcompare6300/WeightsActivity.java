package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightsActivity extends AppCompatActivity {

    private Button button_cancel_weight;
    private Button button_save_weight;
    private EditText text_commute_time;
    private EditText text_yearly_salary;
    private EditText text_yearly_bonus;
    private EditText text_retirement;
    private EditText text_leave;

    public static final String SHARED_PREFS_COMMUTE = "spcommute";
    public static final String SHARED_PREFS_SALARY = "spsalary";
    public static final String SHARED_PREFS_BONUS = "spbonus";
    public static final String SHARED_PREFS_RETIREMENT = "spretirement";
    public static final String SHARED_PREFS_LEAVE = "spleave";

    public static final String shared_commute_time = "text";
    public static final String shared_yearly_salary = "text";
    public static final String shared_yearly_bonus = "text";
    public static final String shared_retirement = "text";
    public static final String shared_leave = "text";

    private String saved_commute_time;
    private String saved_yearly_salary;
    private String saved_yearly_bonus;
    private String saved_retirement;
    private String saved_leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        text_commute_time = (EditText) findViewById(R.id.text_commute_weight);
        text_yearly_salary = (EditText) findViewById(R.id.text_salary_weight);
        text_yearly_bonus = (EditText) findViewById(R.id.text_bonus_weight);
        text_retirement = (EditText) findViewById(R.id.text_retirement_weight);
        text_leave = (EditText) findViewById(R.id.text_leave_weight);

        button_cancel_weight = findViewById(R.id.button_cancel_weight);
        button_cancel_weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WeightsActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Entered weights cancelled",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        button_save_weight = findViewById(R.id.button_save_weight);
        button_save_weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code that involves saving the user-entered weights here
                //
                //saveWeight(view);
                saveData();
                Intent intent = new Intent(WeightsActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Entered weights saved",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        loadData();
        updateViews();
    }

//    public void saveWeight(View view){
//        text_commute_time = (EditText) findViewById(R.id.text_commute_weight);
//        int commute_timeText = Integer.parseInt(text_commute_time.getText().toString());
//
//        text_yearly_salary = (EditText) findViewById(R.id.text_salary_weight);
//        int salary = Integer.parseInt(text_yearly_salary.getText().toString());
//
//        text_yearly_bonus = (EditText) findViewById(R.id.text_bonus_weight);
//        int bonus = Integer.parseInt(text_yearly_bonus.getText().toString());
//
//        text_retirement = (EditText) findViewById(R.id.text_retirement_weight);
//        int retirementValue = Integer.parseInt(text_retirement.getText().toString());
//
//        text_leave = (EditText) findViewById(R.id.text_leave_weight);
//        int leaveValue = Integer.parseInt(text_leave.getText().toString());
//
//        Weights.setCommute_weight(commute_timeText);
//        Weights.setSalary_weight(salary);
//        Weights.setBonus_weight(bonus);
//        Weights.setRetirement_weight(retirementValue);
//        Weights.setLeave_weight(leaveValue);
//
//        Log.v("Text",Weights.printWeights());
//    }

    public void saveData() {
        SharedPreferences sp;
        SharedPreferences.Editor ed;

        sp = getSharedPreferences(SHARED_PREFS_COMMUTE, MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(shared_commute_time, text_commute_time.getText().toString());
        ed.commit();

        sp = getSharedPreferences(SHARED_PREFS_SALARY, MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(shared_yearly_salary, text_yearly_salary.getText().toString());
        ed.commit();

        sp = getSharedPreferences(SHARED_PREFS_BONUS, MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(shared_yearly_bonus, text_yearly_bonus.getText().toString());
        ed.commit();

        sp = getSharedPreferences(SHARED_PREFS_RETIREMENT, MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(shared_retirement, text_retirement.getText().toString());
        ed.commit();

        sp = getSharedPreferences(SHARED_PREFS_LEAVE, MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(shared_leave, text_leave.getText().toString());
        ed.commit();
    }

    public void loadData() {
        SharedPreferences sp;

        sp = getSharedPreferences(SHARED_PREFS_COMMUTE, MODE_PRIVATE);
        saved_commute_time = sp.getString(shared_commute_time, "");
        text_commute_time.setText(saved_commute_time);

        sp = getSharedPreferences(SHARED_PREFS_SALARY, MODE_PRIVATE);
        saved_yearly_salary = sp.getString(shared_yearly_salary, "");
        text_yearly_salary.setText(saved_yearly_salary);

        sp = getSharedPreferences(SHARED_PREFS_BONUS, MODE_PRIVATE);
        saved_yearly_bonus = sp.getString(shared_yearly_bonus, "");
        text_yearly_bonus.setText(saved_yearly_bonus);

        sp = getSharedPreferences(SHARED_PREFS_RETIREMENT, MODE_PRIVATE);
        saved_retirement = sp.getString(shared_retirement, "");
        text_retirement.setText(saved_retirement);

        sp = getSharedPreferences(SHARED_PREFS_LEAVE, MODE_PRIVATE);
        saved_leave = sp.getString(shared_leave, "");
        text_leave.setText(saved_leave);
    }

    public void updateViews() {
        text_commute_time.setText(saved_commute_time);
        text_yearly_salary.setText(saved_yearly_salary);
        text_yearly_bonus.setText(saved_yearly_bonus);
        text_retirement.setText(saved_retirement);
        text_leave.setText(saved_leave);;
    }
}