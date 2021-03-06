package com.example.androidforbegginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String CALCULATOR = "CALCULATOR";
    public static final String PREFERENCES_NAME = "preferences";
    public static final int MyThemePear = 0;
    public static final int MyThemeBlueberry = 1;
    private static final String THEME_NAME = "theme";


    private TextView textView;
    private TextView textViewResult;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(loadAppTheme());
        setContentView(R.layout.activity_main);

        int currentTheme = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
                .getInt(THEME_NAME, MyThemeBlueberry);
        if (currentTheme == MyThemeBlueberry) {
            ((RadioButton) findViewById(R.id.radioButtonBlueberry)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.radioButtonPear)).setChecked(true);
        }

        findViewById(R.id.radioButtonBlueberry).setOnClickListener(v -> {
            saveAppTheme(MyThemeBlueberry);
            recreate();
        });
        findViewById(R.id.radioButtonPear).setOnClickListener(v -> {
            saveAppTheme(MyThemePear);
            recreate();
        });

        textView = findViewById(R.id.textView);
        textViewResult = findViewById(R.id.textViewResult);
        if (calculator == null) {
            calculator = new Calculator();
        }
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(view -> {
            updateView("1");
        });
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> {
            updateView("2");
        });
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(view -> {
            updateView("3");
        });
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(view -> {
            updateView("4");
        });
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(view -> {
            updateView("5");
        });
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(view -> {
            updateView("6");
        });
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(view -> {
            updateView("7");
        });
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(view -> {
            updateView("8");
        });
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(view -> {
            updateView("9");
        });
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(view -> {
            updateView("0");
        });
        Button buttonClean = findViewById(R.id.button_clean);
        buttonClean.setOnClickListener(view -> {
            calculator.setNumber("");
            calculator.setViewCalculate("");
            calculator.setViewResult("");
            calculator.setResult(0);
            textView.setText(calculator.getViewCalculate());
            textViewResult.setText(calculator.getViewResult());
        });
        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(view -> {
            makeOperation(" + ");
        });
        Button buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(view -> {
            makeOperation(" - ");
        });
        Button buttonMul = findViewById(R.id.button_mul);
        buttonMul.setOnClickListener(view -> {
            makeOperation(" * ");
        });
        Button buttonDiv = findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(view -> {
            makeOperation(" / ");
        });
        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(view -> {
            if ("0".equals(calculator.getNumber())) {
                calculator.setViewResult(textView.getText() + "\nmustn't divide by zero");
                calculator.setViewCalculate("");
                calculator.setNumber("");
                calculator.setResult(0);
            } else {
                calculator.calculate(Double.parseDouble(calculator.getNumber()));
                calculator.setViewResult(calculator.getViewCalculate() + " = " + calculator.getResult());
                calculator.setViewCalculate(String.valueOf(calculator.getResult()));
                calculator.setNumber(calculator.getResult());
            }
            textViewResult.setText(calculator.getViewResult());
            textView.setText(calculator.getViewCalculate());
        });
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case MyThemePear:
                return R.style.MyThemePear;
            case MyThemeBlueberry:
            default:
                return R.style.MyThemeBlueberry;
        }
    }

    private void saveAppTheme(int code) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        sharedPreferences.edit()
                .putInt(THEME_NAME, code)
                .apply();
    }

    private int loadAppTheme() {
        int codeTheme = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
                .getInt(THEME_NAME, MyThemeBlueberry);
        return codeStyleToStyleId(codeTheme);
    }

    private void makeOperation(String operation) {
        calculator.setViewCalculate(textView.getText() + operation);
        calculator.setResult(Double.parseDouble(calculator.getNumber()));
        calculator.setNumber("");
        textView.setText(calculator.getViewCalculate());
    }

    private void updateView(String number) {
        calculator.setNumber(calculator.getNumber() + number);
        calculator.setViewCalculate(calculator.getViewCalculate() + number);
        textView.setText(calculator.getViewCalculate());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getParcelable(CALCULATOR);
        if (calculator == null) {
            calculator = new Calculator();
        }
        textViewResult.setText(calculator.getViewResult());
        textView.setText(calculator.getViewCalculate());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CALCULATOR, calculator);
    }
}