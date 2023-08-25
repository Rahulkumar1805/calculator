package com.example.calc_calculator_app1;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.String;

public class MainActivity extends AppCompatActivity {


    TextView resultView, solutionView;
    Boolean lastNumeric = false;
    Boolean lastDot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.resultView);
        solutionView = findViewById(R.id.solutionView);
        //String solution= solutionView.getText().toString();
        String result = resultView.getText().toString();

    }

    public void onDigit(View view) {
        AppCompatButton btn = (AppCompatButton) view;
        solutionView.append(((AppCompatButton) view).getText().toString());
        lastNumeric = true;

    }

    public void onEqual(View view) {
        //Log.d("rahul",solutionView.getText().toString());
        if (!solutionView.getText().toString().equals(""))
            if (!isOperatorAtLast(solutionView.getText().toString()) && !isOperatorAtFirst(solutionView.getText().toString())) {
                double result = StringExp.evaluate(solutionView.getText().toString());
                String Final_result = String.valueOf(result);
//                if (Final_result.endsWith(".0")) {
//                    Final_result = Final_result.substring(0, Final_result.length() - 2);
//                    resultView.setText(Final_result);
//                } else {
//                    resultView.setText(Final_result);
//                }
                resultView.setText(Final_result);

            }

    }

    public void onDecimalPoint(View view) {
        if (lastNumeric && !lastDot) {
            lastDot = true;
            lastNumeric = false;
            solutionView.append(".");
        }
    }

    public void onClear(View view) {
        lastNumeric = false;
        lastDot = false;
        solutionView.setText("");
        resultView.setText("0");

    }

    public void onBack(View view) {
        if (!solutionView.getText().toString().equals("")) {
            String str = solutionView.getText().toString();
            if (str.charAt(str.length() - 1) == '.') {
                lastDot = false;
                lastNumeric = true;
            }
            str = str.substring(0, str.length() - 1);
            solutionView.setText(str);
        }


    }

    public void onOperator(View view) {
        if (!solutionView.getText().toString().equals("")) {
            if (!isOperatorAtLast(solutionView.getText().toString()))
                solutionView.append(((AppCompatButton) view).getText().toString());
            lastDot = false;
            lastNumeric = false;
        }
    }

    Boolean isOperatorAtLast(String exp) {
        return exp.charAt(exp.length() - 1) == '+' || exp.charAt(exp.length() - 1) == '-' || exp.charAt(exp.length() - 1) == '*' || exp.charAt(exp.length() - 1) == '/' || exp.charAt(exp.length() - 1) == '%';
    }

    Boolean isOperatorAtFirst(String exp) {
        return exp.charAt(0) == '+' || exp.charAt(0) == '-' || exp.charAt(0) == '*' || exp.charAt(0) == '/' || exp.charAt(0) == '%';
    }


}




