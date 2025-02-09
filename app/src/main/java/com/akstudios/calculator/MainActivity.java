package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CalculatorInputHandler inputHandler;
    private CalculatorLogic calculatorLogic;
    private TextView resultTextView;

    private List<CalculationHistory> calculationHistoryList = new ArrayList<>();
    private CalculationHistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initCalculator();
        setButtonListeners();

        ListView historyListView = findViewById(R.id.calculationHistoryList);
        historyAdapter = new CalculationHistoryAdapter(this, calculationHistoryList);
        historyListView.setAdapter(historyAdapter);
    }

    private void initControls() {
        resultTextView = findViewById(R.id.result);
    }

    private void initCalculator() {
        inputHandler = new CalculatorInputHandler(resultTextView);
        calculatorLogic = new CalculatorLogic();
    }

    private void setButtonListeners() {
        Button[] numberButtons = new Button[] {
                findViewById(R.id.button0), findViewById(R.id.button1),
                findViewById(R.id.button2), findViewById(R.id.button3),
                findViewById(R.id.button4), findViewById(R.id.button5),
                findViewById(R.id.button6), findViewById(R.id.button7),
                findViewById(R.id.button8), findViewById(R.id.button9)
        };

        for (int i = 0; i < numberButtons.length; i++) {
            int finalI = i;
            numberButtons[i].setOnClickListener(view -> inputHandler.appendToInput(String.valueOf(finalI)));
        }

        findViewById(R.id.buttonAdd).setOnClickListener(view -> inputHandler.setOperator("+"));
        findViewById(R.id.buttonSub).setOnClickListener(view -> inputHandler.setOperator("-"));
        findViewById(R.id.buttonMul).setOnClickListener(view -> inputHandler.setOperator("*"));
        findViewById(R.id.buttonDiv).setOnClickListener(view -> inputHandler.setOperator("/"));
        findViewById(R.id.buttonClear).setOnClickListener(view -> inputHandler.clearInput());
        findViewById(R.id.buttonEqual).setOnClickListener(view -> calculateResult());
    }

    private void calculateResult() {
        double result = calculatorLogic.calculate(inputHandler.getPreviousInput(), inputHandler.getCurrentInput(), inputHandler.getOperator());

        if (Double.isNaN(result)) {
            resultTextView.setText("Error");
        } else {
            String expression = inputHandler.getPreviousInput() + " " + inputHandler.getOperator() + " " + inputHandler.getCurrentInput();
            String resultString = String.valueOf(result);

            // Add to calculation history
            updateHistory(expression, resultString);

            // Update the current input with the result
            inputHandler.updateCurrentInput(resultString);
        }
    }
    private void updateHistory(String expression, String result) {
        // Add the new calculation to the history
        CalculationHistory history = new CalculationHistory(expression, result);
        calculationHistoryList.add(0, history); // Add at the top of the list

        // Limit to the last 5 entries
        if (calculationHistoryList.size() > 5) {
            calculationHistoryList.remove(calculationHistoryList.size() - 1); // Remove the oldest
        }

        // Notify the adapter that the data has changed
        historyAdapter.notifyDataSetChanged();
    }
}
