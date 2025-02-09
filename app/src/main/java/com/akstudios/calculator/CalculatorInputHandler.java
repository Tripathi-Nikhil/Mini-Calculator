package com.akstudios.calculator;

import android.widget.TextView;

public class CalculatorInputHandler {
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";
    private TextView resultTextView;

    public CalculatorInputHandler(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

    public void appendToInput(String value) {
        currentInput += value;
        resultTextView.setText(currentInput);
    }

    public void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            currentInput = "";
            this.operator = operator;
        }
    }

    public void clearInput() {
        currentInput = "";
        previousInput = "";
        operator = "";
        resultTextView.setText("0");
    }

    public void updateCurrentInput(String newInput) {
        currentInput = newInput;
        resultTextView.setText(currentInput);
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public String getPreviousInput() {
        return previousInput;
    }

    public String getOperator() {
        return operator;
    }
}
