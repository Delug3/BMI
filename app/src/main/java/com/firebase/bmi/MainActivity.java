package com.firebase.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText gender;
    private String weightSpinnerValue,heightSpinnerValue,genderSpinnerValue;
    Spinner customSpinnerW,customSpinnerH,customSpinnerG;

    List<String> weightList;
    List<String> heightList;
    List<String> genderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.nameEditText);

        /**SPINNERS // WEIGHT //HEIGHT //GENDER
        /**List Ready For Spinner/Drop Down**/
        prepareWeightList();
        prepareHeightList();
        prepareGenderList();

        /**Custom Spinner for Weight**/
        //spinner for weight
        customSpinnerW = findViewById(R.id.customSpinnerWeight);
        ArrayAdapter<String> customSpinnerWeight = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, weightList);
        customSpinnerWeight.setDropDownViewResource(R.layout.custom_dropdown);
        customSpinnerW.setAdapter(customSpinnerWeight);
        customSpinnerW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //store weight value for calcBMI
                weightSpinnerValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});

        /**Custom Spinner for Height**/
        //spinner for height
        customSpinnerH = findViewById(R.id.customSpinnerHeight);
        ArrayAdapter<String> customSpinnerHeight = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, heightList);
        customSpinnerHeight.setDropDownViewResource(R.layout.custom_dropdown);
        customSpinnerH.setAdapter(customSpinnerHeight);
        customSpinnerH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //store height value for calcBMI
                heightSpinnerValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});

        /**Custom Spinner for Gender**/
        //spinner for gender

        customSpinnerG = findViewById(R.id.customSpinnerGender);
        ArrayAdapter<String> customSpinnerGender = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, genderList);
        customSpinnerGender.setDropDownViewResource(R.layout.custom_dropdown);
        customSpinnerG.setAdapter(customSpinnerGender);
        customSpinnerG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //store height value for calcBMI
                genderSpinnerValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});


}


    private void prepareWeightList() {
        weightList = new ArrayList<>();
        /**Add Items in Spinner List**/
        weightList.add("72");
        weightList.add("80");

    }

    private void prepareHeightList() {
        heightList = new ArrayList<>();
        /**Add Items in Spinner List**/
        heightList.add("160");
        heightList.add("182");

    }

    private void prepareGenderList() {
        genderList = new ArrayList<>();
        /**Add Items in Spinner List**/
        genderList.add("Male");
        genderList.add("Female");

    }


    //calc BMI first activity then pass to second the result
    //bmi = weight /(height * height);
    public void calcBMI(View v) {
        String heightS = heightSpinnerValue;
        String weightS = weightSpinnerValue;


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
        String genderS = genderSpinnerValue;

        Intent i = new Intent(MainActivity.this, DetailsBmiActivity.class);

        i.putExtra("NAME", nameS );
        i.putExtra("GENDER", genderS );
        i.putExtra("BMI", bmi );
        i.putExtra("BMI CATEGORY", bmiCategory );


        startActivity(i);
    }



}

