package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextValue;
    private TextView textViewResult;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        buttonConvert = findViewById(R.id.buttonConvert);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        spinnerFromUnit.setOnItemSelectedListener(this);
        spinnerToUnit.setOnItemSelectedListener(this);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        convertUnits();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Not used in this example
    }

    private void convertUnits() {
        String valueText = editTextValue.getText().toString().trim();
        if (!valueText.isEmpty()) {
            double value = Double.parseDouble(valueText);
            String fromUnit = spinnerFromUnit.getSelectedItem().toString();
            String toUnit = spinnerToUnit.getSelectedItem().toString();
            double result = convert(value, fromUnit, toUnit);
            textViewResult.setText(value + " " + fromUnit + " = " + result + " " + toUnit);
        } else {
            textViewResult.setText("Please enter a value");
        }
    }

    private double convert(double value, String fromUnit, String toUnit) {
        double result = 0.0;

        if (fromUnit.equals("Meter") && toUnit.equals("Centimeter"))
            result = value * 100;
        else if (fromUnit.equals("Centimeter") && toUnit.equals("Meter"))
            result = value / 100;
        else if (fromUnit.equals("Kilogram") && toUnit.equals("Gram"))
            result = value * 1000;
        else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram"))
            result = value / 1000;
        else if (fromUnit.equals("Inch") && toUnit.equals("Feet"))
            result = value / 12;
        else if (fromUnit.equals("Feet") && toUnit.equals("Inch"))
            result = value * 12;
        else if (fromUnit.equals("Liter") && toUnit.equals("Milliliter"))
            result = value * 1000;
        else if (fromUnit.equals("Milliliter") && toUnit.equals("Liter"))
            result = value / 1000;
            // Add more conversion cases as needed
        else
            result = value; // Default case: no conversion

        return result;
    }
}
