package com.demo.atmosphericpressurecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ETLength, ETWidth, ETAtm;
    String StrLength, StrWidth, StrAtm, resultMessage;

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = findViewById(R.id.button);

        btnCompute.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            Toast.makeText(this, "Calculating....", Toast.LENGTH_SHORT).show();
            ComputeResult();
        }

    public void ComputeResult(){
        ETLength =findViewById(R.id.ETLength);
        ETWidth =findViewById(R.id.ETWidth);
        ETAtm =findViewById(R.id.ETAtm);

        if(ETLength.getText().toString().isEmpty() || ETWidth.getText().toString().isEmpty() || ETAtm.getText().toString().isEmpty()){
            StrLength = "0";
            StrWidth = "0";
            StrAtm = "0";
        }
        else {
            StrLength = ETLength.getText().toString();
            StrWidth = ETWidth.getText().toString();
            StrAtm = ETAtm.getText().toString();
        }

            double rawLength = Double.parseDouble(StrLength);
            double rawWidth = Double.parseDouble(StrWidth);
            int rawAtm = Integer.parseInt(StrAtm);
            int ETAtm = Integer.parseInt(StrAtm);

        switch(rawAtm){
            case 1:
                rawAtm = 101325;
                break;
            case 2:
                rawAtm = 202650;
                break;
            case 3:
                rawAtm = 303975;
                break;
            case 4:
                rawAtm = 405300;
                break;
            case 5:
                rawAtm = 506625;
                break;
        }
            double Area = rawLength * rawWidth;
            double Force = (rawAtm * Area);
            double Kilograms = Force * 0.1019716213;

            resultMessage = (int)Force + " Newton or " + (int)Kilograms +
                    " kg Force is the force exerted by " + StrAtm +
                    " atmosphere/s over an area of " + StrLength +
                    "m x " + StrWidth + "m.";

            Bundle args = new Bundle();
            args.putString("result", resultMessage);

        Intent intent;
        
        if(ETAtm == 1) {
            intent = new Intent(MainActivity.this, Result1ATM.class);
        }
        else if (ETAtm == 2) {
            intent = new Intent(MainActivity.this, Result2ATM.class);
        }
        else if (ETAtm == 3){
            intent = new Intent(MainActivity.this, Result3ATM.class);
        }
        else if (ETAtm == 4){
            intent = new Intent(MainActivity.this, Result4ATM.class);
        }
        else {
            intent = new Intent(MainActivity.this, Result5ATM.class);
        }
        intent.putExtras(args);
        startActivity(intent);
            clearEditText();
    }

        public void clearEditText() {
            ETLength.getText().clear();
            ETWidth.getText().clear();
            ETAtm.getText().clear();
            ETAtm.requestFocus();
        }
    }