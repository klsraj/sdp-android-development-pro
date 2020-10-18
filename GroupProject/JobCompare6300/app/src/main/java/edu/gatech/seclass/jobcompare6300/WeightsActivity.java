package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import edu.gatech.seclass.jobcomparestorage.WeightsDBHelper;
import edu.gatech.seclass.jobcomparestorage.WeightsDBManager;

public class WeightsActivity extends AppCompatActivity {

    private Button button_cancel_weight;
    private Button button_save_weight;
    private EditText commute_time;
    private EditText yearly_salary;
    private EditText yearly_bonus;
    private EditText retirement;
    private EditText leave;

    private WeightsDBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { WeightsDBHelper._ID,
            WeightsDBHelper.commute_weight, WeightsDBHelper.salary_weight,
            WeightsDBHelper.retirement_weight, WeightsDBHelper.leave_weight,
            WeightsDBHelper.bonus_weight};

//    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        dbManager = new WeightsDBManager(this);
        dbManager.open();

        Log.v("Text","Trying To Fetch");
        Cursor cursor = dbManager.fetch();

        commute_time = (EditText) findViewById(R.id.text_commute_weight);
        yearly_salary = (EditText) findViewById(R.id.text_salary_weight);
        yearly_bonus = (EditText) findViewById(R.id.text_bonus_weight);
        retirement = (EditText) findViewById(R.id.text_retirement_weight);
        leave = (EditText) findViewById(R.id.text_leave_weight);

        if(cursor.getCount() > 0){
            Log.v("Text","failing inside if");
            Log.v("Text",cursor.getString(cursor.getColumnIndex(WeightsDBHelper.commute_weight)));
            Log.v("Text",cursor.getString(cursor.getColumnIndex(WeightsDBHelper.salary_weight)));
            Log.v("Text",cursor.getString(cursor.getColumnIndex(WeightsDBHelper.retirement_weight)));
            Log.v("Text",cursor.getString(cursor.getColumnIndex(WeightsDBHelper.bonus_weight)));
            Log.v("Text",cursor.getString(cursor.getColumnIndex(WeightsDBHelper.leave_weight)));

            commute_time.setText(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.commute_weight)));
            yearly_salary.setText(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.salary_weight)));
            retirement.setText(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.retirement_weight)));
            yearly_bonus.setText(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.bonus_weight)));
            leave.setText(cursor.getString(cursor.getColumnIndex(WeightsDBHelper.leave_weight)));
        }

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
                //saveWeight(view);
                saveInDB(view);
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
        int commute_timeText = Integer.parseInt(commute_time.getText().toString());
        int salary = Integer.parseInt(yearly_salary.getText().toString());
        int bonus = Integer.parseInt(yearly_bonus.getText().toString());
        int retirementValue = Integer.parseInt(retirement.getText().toString());
        int leaveValue = Integer.parseInt(leave.getText().toString());

        Weights.setCommute_weight(commute_timeText);
        Weights.setSalary_weight(salary);
        Weights.setBonus_weight(bonus);
        Weights.setRetirement_weight(retirementValue);
        Weights.setLeave_weight(leaveValue);

        Log.v("Text",Weights.printWeights());
    }

    public void saveInDB(View view){
        int commute_timeText = Integer.parseInt(commute_time.getText().toString());
        int salary = Integer.parseInt(yearly_salary.getText().toString());
        int bonus = Integer.parseInt(yearly_bonus.getText().toString());
        int retirementValue = Integer.parseInt(retirement.getText().toString());
        int leaveValue = Integer.parseInt(leave.getText().toString());

        Log.v("Text","Trying to Save in DB");
        Cursor cursor = dbManager.fetch();
        if(cursor.getCount() > 0) {
            dbManager.update(100,commute_timeText,salary,bonus,retirementValue,leaveValue);
            Log.v("Text","Update in DB Complete");
            Toast.makeText(getApplicationContext(),
                    "SAVED",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            dbManager.insert(100, commute_timeText, salary, bonus, retirementValue, leaveValue);
            Log.v("Text","Insert in DB Complete");
            Toast.makeText(getApplicationContext(),
                    "INSERTED",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}