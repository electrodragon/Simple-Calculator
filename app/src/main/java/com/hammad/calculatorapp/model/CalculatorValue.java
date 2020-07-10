package com.hammad.calculatorapp.model;

/**
 * This Class Holds a Single Value for instance
 */

public class CalculatorValue {

    public static final String EMPTY = "";

    private String value;

    public CalculatorValue(String value) {
        this.value = value;
    }

    public boolean isNotEmpty() {
        return !value.isEmpty();
    }

    public double getValue() {
        return Double.parseDouble(value);
    }

    public boolean isPointSet() {
        return value.contains(".");
    }

    public void append(String value) {
        if (value.equals(".")) { // If Pressed Was Point
            if (isNotEmpty()) { // If Value is Not Already Empty
                if(!isPointSet()) { // If Point Is Already Not Present
                    this.value += value; // Add Point
                }
            }
        } else {
            this.value += value;
        }
    }

    public void removeLastItem() {
        if (isNotEmpty()) {
            value = value.substring(0, value.length() - 1);
        }
    }
}
