package com.example.androidforbegginers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivitySettings extends AppCompatActivity {

    public static final String PREFERENCES_NAME = "preferences";
    public static final int MyThemePear = 0;
    public static final int MyThemeBlueberry = 1;
    private static final String THEME_NAME = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(loadAppTheme());
        setContentView(R.layout.activity_main_settings);

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

        findViewById(R.id.saveGOBack).setOnClickListener(v -> {
            Intent intentResult = new Intent();
            int themeCode = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
                    .getInt(THEME_NAME, MyThemeBlueberry);
            intentResult.putExtra(THEME_NAME, themeCode);
            setResult(RESULT_OK, intentResult);
            finish();
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
}