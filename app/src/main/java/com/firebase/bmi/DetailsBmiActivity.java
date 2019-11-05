package com.firebase.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsBmiActivity extends AppCompatActivity {

    private TextView BmiCategoryResult;
    private TextView BMIResult;
    String name,gender,category;
    Float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        BMIResult = findViewById(R.id.Bmi);
        BmiCategoryResult = findViewById(R.id.BmiCategoryResult);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                bmi=null;
                category= null;

            } else {
                name = extras.getString("NAME");
                gender= extras.getString("GENDER");
                bmi = extras.getFloat("BMI");
                category= extras.getString("BMI CATEGORY");

                BMIResult.setText(String.valueOf(bmi));

               // String message = "Hello Manuel, you are " + "\n\n" + category;
                String message = "Hello "+ name + ", you are "+ category;
                BmiCategoryResult.setText(message);


            }
        }
    }

}