package com.firebase.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;
    private EditText name;
    private EditText gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameEditText);
        gender = findViewById(R.id.genderEditText);
        height = findViewById(R.id.heightEditText);
        weight = findViewById(R.id.weightEditText);
        result = findViewById(R.id.resultButton);
    }



    //calc BMI first activity then pass to second the result
    //bmi = weight /(height * height);
    public void calcBMI(View v) {
        String heightS = height.getText().toString();
        String weightS = weight.getText().toString();

        if (heightS != null && !"".equals(heightS) && weightS != null  &&  !"".equals(weightS)) {

            float heightValue = Float.parseFloat(heightS) / 100;
            float weightValue = Float.parseFloat(weightS);

            float bmi = weightValue / (heightValue * heightValue);

            //result number,float
            obtainBMI(bmi);
        }
    }

    //obtain result of body mass index with string: you are normal, you are obese....
    //look for diagnostic table!!->18,19,20 is normal
    public void obtainBMI(float bmi) {
        String bmiCategory;

        if (Float.compare(bmi, 15f) <= 0) {
            bmiCategory = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiCategory = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiCategory = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiCategory = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiCategory = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiCategory = getString(R.string.obese_class_1);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiCategory = getString(R.string.obese_class_2);
        } else {
            bmiCategory = getString(R.string.obese_class_3);
        }

       //Float BMI = bmi;
       //String TAG = bmiCategory;


        showDetailsBMI(bmi, bmiCategory);

    }

    public void showDetailsBMI(float bmi, String bmiCategory){
        //pass calculated bmi and label to detail activity plus name and gender
        String nameS = name.getText().toString();
        String genderS = gender.getText().toString();

        Intent i = new Intent(MainActivity.this, DetailsBmiActivity.class);

        i.putExtra("NAME", nameS );
        i.putExtra("GENDER", genderS );
        i.putExtra("BMI", bmi );
        i.putExtra("BMI CATEGORY", bmiCategory );


        startActivity(i);
    }



}

