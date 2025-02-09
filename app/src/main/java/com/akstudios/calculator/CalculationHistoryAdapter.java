package com.akstudios.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CalculationHistoryAdapter extends ArrayAdapter<CalculationHistory> {
    private final Context context;
    private final List<CalculationHistory> historyList;

    public CalculationHistoryAdapter(Context context, List<CalculationHistory> historyList) {
        super(context, 0, historyList);
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_history, parent, false);
        }

        CalculationHistory history = getItem(position);

        TextView operationText = convertView.findViewById(R.id.operationText);
        TextView resultText = convertView.findViewById(R.id.resultText);

        if (history != null) {
            operationText.setText(history.getExpression());
            resultText.setText(history.getResult());
        }

        return convertView;
    }
}
