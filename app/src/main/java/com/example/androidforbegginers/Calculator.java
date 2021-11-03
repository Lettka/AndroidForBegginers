package com.example.androidforbegginers;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {
    private double result;
    private String viewResult;
    private String viewCalculate;
    private String number;

    public Calculator() {
        result = 0;
        viewResult = "";
        viewCalculate = "";
        number = "";
    }

    public Calculator(double result, String viewResult, String viewCalculate, String number) {
        this.result = result;
        this.viewResult = viewResult;
        this.viewCalculate = viewCalculate;
        this.number = number;
    }

    public String getViewResult() {
        return viewResult;
    }

    public String getViewCalculate() {
        return viewCalculate;
    }

    public String getNumber() {
        return String.valueOf(number);
    }

    public void setViewResult(String viewResult) {
        this.viewResult = viewResult;
    }

    public void setViewCalculate(String viewCalculate) {
        this.viewCalculate = viewCalculate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumber(double number) {
        this.number = Double.toString(number);
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void calculate(double number) {
        if (viewCalculate.contains("+")) {
            result += number;
        } else if (viewCalculate.contains("-")) {
            result -= number;
        } else if (viewCalculate.contains("*")) {
            result *= number;
        } else {
            result /= number;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
