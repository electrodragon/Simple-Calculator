package com.hammad.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class CalculatorActivity extends AppCompatActivity {
    private boolean isOperationPressed = false;

    private double firstNumber = 0;

    private  int secondNumberIndex = 0;

    private char currentOperation = 0;

    private boolean isPoint = false;

    private TextView calculatorScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        calculatorScreen = findViewById(R.id.calculatorScreen);
        final Button n0 = findViewById(R.id.n0);
        final Button n1 = findViewById(R.id.n1);
        final Button n2 = findViewById(R.id.n2);
        final Button n3 = findViewById(R.id.n3);
        final Button n4 = findViewById(R.id.n4);
        final Button n5 = findViewById(R.id.n5);
        final Button n6 = findViewById(R.id.n6);
        final Button n7 = findViewById(R.id.n7);
        final Button n8 = findViewById(R.id.n8);
        final Button n9 = findViewById(R.id.n9);
        final Button point = findViewById(R.id.point);
        final Button equal = findViewById(R.id.equal);
        final Button addition = findViewById(R.id.add);
        final Button subtraction = findViewById(R.id.subtraction);
        final Button multiplication = findViewById(R.id.multiplication);
        final Button division = findViewById(R.id.division);


        final  View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = view.getId();
                switch (id){
                        case R.id.n0:
                        calculatorScreen.append("0");
                        break;

                        case R.id.n1:
                        calculatorScreen.append("1");
                        break;

                        case R.id.n2:
                        calculatorScreen.append("2");
                        break;

                        case R.id.n3:
                        calculatorScreen.append("3");
                        break;

                        case R.id.n4:
                        calculatorScreen.append("4");
                        break;

                        case R.id.n5:
                        calculatorScreen.append("5");
                        break;

                        case R.id.n6:
                        calculatorScreen.append("6");
                        break;

                        case R.id.n7:
                        calculatorScreen.append("7");
                        break;


                        case R.id.n8:
                        calculatorScreen.append("8");
                        break;

                        case R.id.n9:
                        calculatorScreen.append("9");
                        break;


                        case R.id.point:
                        if (!isPoint){
                            String screenContent = calculatorScreen.getText().toString();
                            final int screenContentLength = screenContent.length();
                            if (screenContentLength < 1){
                                return;
                            }
                            char lastChar = screenContent.charAt(screenContentLength -1);
                            if(lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/'){
                                return;
                            }
                            calculatorScreen.append(".");
                            isPoint = true;
                        }
                        break;

                        case R.id.equal:
                        if(isOperationPressed){
                            String screenContent = calculatorScreen.getText().toString();
                            char lastChar = screenContent.charAt(screenContent.length()-1);
                            if(lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/'){
                                return;
                            }

                            String secondNumberString = screenContent.substring(secondNumberIndex,screenContent.length());
                            double secondNumber = Double.parseDouble(secondNumberString);

                            if (currentOperation=='+'){

                                secondNumber += firstNumber;

                            } else if (currentOperation == '-'){

                                secondNumber = firstNumber - secondNumber;

                            }else if (currentOperation == '*'){

                                secondNumber *= firstNumber;

                            }else if (currentOperation == '/'){

                                if (secondNumber == 0)
                                    return;
                                secondNumber = firstNumber / secondNumber;

                            }
                            String result = String.valueOf(secondNumber);
                            if (result.endsWith(".0")){
                                result = result.substring(0,result.length()-2);
                            }
                            calculatorScreen.setText(result);
                            isOperationPressed = false;
                        }
                        break;

                        case R.id.add:
                            operationPressed('+');
                            break;

                        case R.id.subtraction:
                            operationPressed('-');
                            break;

                        case R.id.multiplication:
                            operationPressed('*');
                            break;

                        case R.id.division:
                            operationPressed('/');
                            break;



                }
            }
        };
        n0.setOnClickListener(calculatorListener);
        n1.setOnClickListener(calculatorListener);
        n2.setOnClickListener(calculatorListener);
        n3.setOnClickListener(calculatorListener);
        n4.setOnClickListener(calculatorListener);
        n5.setOnClickListener(calculatorListener);
        n6.setOnClickListener(calculatorListener);
        n7.setOnClickListener(calculatorListener);
        n8.setOnClickListener(calculatorListener);
        n9.setOnClickListener(calculatorListener);
        point.setOnClickListener(calculatorListener);
        equal.setOnClickListener(calculatorListener);
        addition.setOnClickListener(calculatorListener);
        subtraction.setOnClickListener(calculatorListener);
        multiplication.setOnClickListener(calculatorListener);
        division.setOnClickListener(calculatorListener);



        final Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayedElements = calculatorScreen.getText().toString();
                int length = displayedElements.length();
                if (length>0) {
                    displayedElements = displayedElements.substring(0,length-1);
                    calculatorScreen.setText(displayedElements);
                }
            }
        });

        final  Button clearEveryThing = findViewById(R.id.cleareverything);
        clearEveryThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorScreen.setText("");
                isOperationPressed = false;
                isPoint = false;
            }
        });
    }
    private void operationPressed(char operation){
        if (isOperationPressed){
            return;
        }
        String screenContent = calculatorScreen.getText().toString();
        final int screenContentLength = screenContent.length();

        if (screenContentLength < 1){
            return;
        }

        firstNumber = Double.parseDouble(screenContent);
        secondNumberIndex = screenContent.length() + 1;
        calculatorScreen.append(String.valueOf(operation));
        isOperationPressed = true;
        isPoint = false;
        currentOperation = operation;
    }

}