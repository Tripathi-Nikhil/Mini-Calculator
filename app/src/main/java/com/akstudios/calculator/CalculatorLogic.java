package com.akstudios.calculator;

public class CalculatorLogic {

    public double calculate(String previousInput, String currentInput, String operator) {
        if (previousInput.isEmpty() || currentInput.isEmpty() || operator.isEmpty()) {
            return Double.NaN;  // Error condition
        }

        double num1 = Double.parseDouble(previousInput);
        double num2 = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    return Double.NaN; // Error: division by zero
                }
                break;
            default:
                return Double.NaN; // Invalid operator
        }
        return result;
    }
}
