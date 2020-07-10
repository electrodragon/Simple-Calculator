package com.hammad.calculatorapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdRequest;
import com.hammad.calculatorapp.model.CalculatorValue;
import com.hammad.calculatorapp.R;
import com.hammad.calculatorapp.databinding.ActivityCalculatorBinding;

import java.util.Calendar;

public class CalculatorActivity extends AppCompatActivity {

    ActivityCalculatorBinding bind;

    String operator = null;

    CalculatorValue calcVal1, calcVal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_calculator);

        // Loads Google Test Ads
        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        if (bind.adView != null) bind.adView.loadAd(adRequest);

        calcVal1 = new CalculatorValue(CalculatorValue.EMPTY);
        calcVal2 = new CalculatorValue(CalculatorValue.EMPTY);

        // Writes Button in Calculator Screen
        for (final Button numButton : new Button[] {bind.n0, bind.n1, bind.n2, bind.n3, bind.n4, bind.n5, bind.n6, bind.n7, bind.n8, bind.n9, bind.point}) {
            numButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (containsOperator(bind.calculatorScreen.getText().toString())) {
                        calcVal2.append(numButton.getText().toString());
                    } else {
                        calcVal1.append(numButton.getText().toString());
                    }
                    updateDisplay();
                }
            });
        }

        bind.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containsOperator(bind.calculatorScreen.getText().toString())) {
                    if (calcVal1.isNotEmpty() && calcVal2.isNotEmpty()) {
                        calcVal1 = new CalculatorValue(String.valueOf(calcVal1.getValue() + calcVal2.getValue()));
                        operator = "+";
                        calcVal2 = new CalculatorValue(CalculatorValue.EMPTY);
                    }
                } else {
                    operator = "+";
                }
                updateDisplay();
            }
        });

        bind.division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containsOperator(bind.calculatorScreen.getText().toString())) {
                    if (calcVal1.isNotEmpty() && calcVal2.isNotEmpty()) {
                        // Perform Calculations
                    }
                } else {
                    operator = "/";
                }
            }
        });

        bind.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bind.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calcVal2.isNotEmpty()) {
                    calcVal2.removeLastItem();
                } else if (operator != null) {
                    operator = null;
                } else {
                    if (calcVal1.isNotEmpty()) {
                        calcVal1.removeLastItem();
                    }
                }
                updateDisplay();
            }
        });

        // Clears Calculator Screen
        bind.cleareverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcVal1 = new CalculatorValue(CalculatorValue.EMPTY);
                calcVal2 = new CalculatorValue(CalculatorValue.EMPTY);
                operator = null;
                updateDisplay();
            }
        });
    }

    public boolean containsOperator(String str) {
        for (String operator : new String[] {"+","-","/", "*"}) {
            if (str.contains(operator)) {
                return true;
            }
        }
        return false;
    }

    public void updateDisplay() {
        String finalResult = "";
        if (calcVal1.isNotEmpty()) {
            finalResult += calcVal1.getValue();
        }
        if (operator != null) {
            finalResult += operator;
            if (calcVal2.isNotEmpty()) {
                finalResult += calcVal2.getValue();
            }
        }
    }
}