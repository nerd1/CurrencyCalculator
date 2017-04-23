package com.androidatc.currencycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCurrency;
    EditText editAmount;
    TextView labelResult, textResult;
    String selectedCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add items to the spinner
        Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinner_currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        final EditText editAmount = (EditText) findViewById(R.id.edit_amount);

        final TextView labelResult = (TextView) findViewById(R.id.label_result);
        final TextView textResult = (TextView) findViewById(R.id.text_result);

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCurrency = parent.getItemAtPosition(position).toString();

                labelResult.setText("Amount in "+ selectedCurrency +":");

                String input = editAmount.getText().toString();
                Double amount = 0.0;

                Log.i("input", input);

                if (!input.isEmpty()) {
                    amount = Double.parseDouble(input);
                }

                textResult.setText(String.valueOf(calculate(amount, selectedCurrency)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editAmount.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = editAmount.getText().toString();
                Double amount = 0.0;

                Log.i("input", input);

                if (!input.isEmpty()) {
                    amount = Double.parseDouble(input);
                }

                textResult.setText(String.valueOf(calculate(amount, selectedCurrency)));
            }

        });
    }

    public Double calculate(Double amount, String currency) {
        Double rate = 1.0;
        switch (currency) {
            case "USD":
                rate = 0.98;
                break;
            case "EUR":
                rate = 1.08;
                break;
            case "GBP":
                rate = 1.44;
                break;
        }
        return amount * (1 / rate);
    }

}
