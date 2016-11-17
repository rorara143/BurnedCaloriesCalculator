package com.example.vitin.burnedcaloriescalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class activity_burned_calories_calculator extends AppCompatActivity {

// define data into widget
    private EditText enter_editText_weight;
    private EditText result_name;
    private TextView total_milesran_textview;
    private TextView total_caloriesburned;
    private TextView total_amount_height;
    private TextView bmi_amount;
    private SeekBar seekbar_miles_ran;
    private Spinner spinner_feet;
    private Spinner spinner_inches;

    //define sharedPreferences object
    private SharedPreferences savedValues;

    //define instance variables that needs to be save



    private String weightString = "";
    private Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_burned_calories_calculator);


        // get refence into widget
        enter_editText_weight = (EditText) findViewById(R.id. enter_editText_weight);
        result_name = (EditText) findViewById(R.id.result_name);
        total_milesran_textview = (TextView) findViewById(R.id.total_milesran_textview);
        total_caloriesburned = (TextView) findViewById(R.id.total_caloriesburned);
        total_amount_height = (TextView) findViewById(R.id.total_amount_height);
        seekbar_miles_ran= (SeekBar) findViewById(R.id.seekbar_miles_ran);
        bmi_amount = (TextView) findViewById(R.id.bmi_amount);
        spinner_feet = (Spinner) findViewById(R.id.spinner_feet);
        spinner_inches = (Spinner) findViewById(R.id.spinner_inches);

        // set array adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.split_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner_feet.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.split_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner_inches.setAdapter(adapter);


        //set listeners
        enter_editText_weight.setOnEditorActionListener(this);


        seekbar_miles_ran.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 1;
            int weight;
            int feet;
            int inches;
            int milesRan;
            int caloriesBurned;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar_miles_ran.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                progress = seekBar.getProgress();

                caloriesBurned = 0.75 * weight * milesRan;

                bmi = (weight * 703) / ((12 * feet + inches) * (12 * feet + inches));

                calculateAndDisplay(progress, bmi_amount);

            }
        });

        //sharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }


    @Override
    protected void onPause() {



        //save instance variables
        Editor edtitor = savedValues.edit();
        edtitor.putString("weightString", weightString);
        editor.putFloat("bmi_amount", bmi_amount);
        editor.putFloat ("miles_ran", total_milesran_textview);
        editor.putFloat ("feet_height", spinner_feet);
        editor.putFloat ("inches_height", spinner_inches);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        weightString = savedValues.getString("weightString", "");
        bmi_amount = savedValues.getFloat("bmi_amount", 0);
        total_milesran_textview = savedValues.getFloat("miles_ran", "");
        spinner_feet = savedValues.getFloat("feet_height", "");
        spinner_inches = savedValues.getFloat("inches_height", "");
        seekbar_miles_ran = savedValues.getFloat("miles_ran", "")

        //set amount on widgets
        weightString.setText(enter_editText_weight);
        bmi_amount.setText(bmi_amount);
        total_milesran_textview.setText(total_milesran_textview);
        spinner_feet.setText(spinner_feet);
        spinner_inches.setText(spinner_inches);
        seekbar_miles_ran.setText(seekbar_miles_ran);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculateAndDisplay(int progress, float tipPercent) {

        // get the bill amount
        String weightString = enter_editText_weight.getText().toString();
        float weight;
        if (weightString.equals("")) {
            weight = 0;
        } else {
            weight = Float.parseFloat(weightString);
        }
        float bmi = 0;
        float milesran = 0;
        float feet = 0;
        float inches = 0;



// display the other results with formatting
        NumberFormat currency = NumberFormat.getInstance();
        weightString.setText(weight.format(enter_editText_weight));
        total_amount_height.setText(currency.format(total_amount_height));
        total_milesran_textview.


        NumberFormat percent = NumberFormat.getPercentInstance();
        total_caloriesburned.setText(percent.format(total_caloriesburned));

    }

    @Override
    public void onClick(View v) {


    }



    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}




}
}
