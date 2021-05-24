package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
        editTextPlace();
        stringBuilderPlace();
        result();//=
        buttonZeroListener();//0
        buttonOneListener();//1
        buttonTwoListener();//2
        buttonThreeListener();
        buttonFourListener();
        buttonFiveListener();
        buttonSixListener();
        buttonSevenListener();
        buttonEightListener();
        buttonNineListener();
        additionNumber();//+
        splitNumber();//  /
        subtractionNumber();// -
        multiplyNumber();// *
        allClear();// AC
        deleteOneNumber();// <-
        percent();// %
        commaInDouble();// .
        negativeNumber();

    }

    private void negativeNumber() {
        Button negativeButton = findViewById(R.id.negative);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().charAt(0) != '-') {
                    stringBuilder.reverse();
                    stringBuilder.append('-');
                    editText.setText(stringBuilder.reverse());
                } else {
                    stringBuilder.deleteCharAt(0);
                    editText.setText(stringBuilder);
                }
            }
        });
    }

    private void lookAround() {
        String number = editText.getText().toString();
        if ((stringBuilder.indexOf("..") != -1) || (stringBuilder.indexOf("--") != -1) || (stringBuilder.indexOf("//") != -1) || (stringBuilder.indexOf("++") != -1) || (stringBuilder.indexOf("**") != -1)) {
            number = number.substring(0, number.length() - 1);
            clearStringBuilder();
            editText.setText(stringBuilder.append(number));
        }//потом понял, что можно ввести типо + и - сразу, пока не понял, как этого избежать

    }

    private void commaInDouble() {
        Button comma = findViewById(R.id.comma);
        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('.'));
                if (editText.getText().length() > 1) {
                    lookAround(); // метод должен проверять, чтобы не было двух точек подряд, двух знаков деления и т.д.
                }
            }
        });
    }

    private void percent() {
        Button percent_100 = findViewById(R.id.percent);
        percent_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numb = Double.parseDouble(String.valueOf(editText.getText()));
                numb = numb / 100;
                System.out.println(numb);
                clearStringBuilder();
                editText.setText(stringBuilder.append(numb));
            }
        });
    }

    private void deleteOneNumber() {
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                if (value.length() <= 1) {
                    value = "0";
                } else {
                    if (value.charAt(value.length() - 2) == '.') {
                        value = value.substring(0, value.length() - 1);
                        value = value.substring(0, value.length() - 1);
                    } else {
                        value = value.substring(0, value.length() - 1);
                    }
                }
                clearStringBuilder();
                editText.setText(stringBuilder.append(value));

            }
        });
    }

    private void allClear() {
        int zero = 0;
        Button clearAll = findViewById(R.id.clear);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStringBuilder();
                editText.setText(String.valueOf(0));
            }
        });
    }

    private void clearStringBuilder() {
        editText.setText(stringBuilder.delete(0, stringBuilder.length()));
    }

    private void stringBuilderPlace() {
        stringBuilder = new StringBuilder();
    }

    private void multiplyNumber() {
        Button multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('*'));
                lookAround();
            }
        });
    }

    private void splitNumber() {
        Button split = findViewById(R.id.split);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('/'));
                lookAround();
            }
        });
    }

    private void subtractionNumber() {
        Button subtraction = findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('-'));
                lookAround();
            }
        });
    }

    private void additionNumber() {
        Button addition = findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('+'));
                lookAround();
            }
        });
    }

    private void result() {
        Button equal = findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] str = stringBuilder.toString().split("(?<!\\d)|(?!\\d)");
                System.out.println(Arrays.toString(str));
                takeResult(str);
            }
        });


    }

    private void takeResult(String[] array) {
        double myResult;
        for (int i = 1; i < array.length; i++) {
            if (array[0].equals("-")) {
                if (array[i].equals("+")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) + Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));

                } else if (array[i].equals("-")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) - Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));
                } else if (array[i].equals("/") && !array[0].equals("/")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    if(str2.equals("0")||str2.equals("0.0")){
                        String divisionByZero = "Так нельзя!";
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(divisionByZero));
                    }else {
                        myResult = Double.parseDouble(str) / Double.parseDouble(str2);
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(round(myResult)));
                    }
                } else if (array[i].equals("*") && !array[0].equals("*")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) * Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));
                }
            } else {
                if (array[i].equals("+")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) + Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));

                } else if (array[i].equals("-")) {
                    String str = array[0];
                    String str2 = array[i + 1];

                    for (int j = 1; j < i; j++) {

                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {

                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) - Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));
                } else if (array[i].equals("/") && !array[0].equals("/")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    if(str2.equals("0")||str2.equals("0.0")){
                        String divisionByZero = "Так нельзя!";
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(divisionByZero));
                    }else {
                        myResult = Double.parseDouble(str) / Double.parseDouble(str2);
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(round(myResult)));
                    }
                } else if (array[i].equals("*") && !array[0].equals("*")) {
                    String str = array[0];
                    String str2 = array[i + 1];
                    for (int j = 1; j < i; j++) {
                        str = str.concat(array[j]);
                    }
                    for (int k = i + 2; k < array.length; k++) {
                        str2 = str2.concat(array[k]);
                    }
                    myResult = Double.parseDouble(str) * Double.parseDouble(str2);
                    clearStringBuilder();
                    editText.setText(stringBuilder.append(round(myResult)));
                }
            }


//        for (int i = 0; i < array.length; i++) {
//            if (array[i].equals("+") && !array[0].equals("+")) {
//                String str = array[0];
//                String str2 = array[i + 1];
//                for (int j = 1; j < i; j++) {
//                    str = str.concat(array[j]);
//                }
//                for (int k = i + 2; k < array.length; k++) {
//                    str2 = str2.concat(array[k]);
//                }
//                myResult = Double.parseDouble(str) + Double.parseDouble(str2);
//                clearStringBuilder();
//                editText.setText(stringBuilder.append(round(myResult)));
//
//            } else if (array[i].equals("-")) {
//                String str = array[0];
//                String str2 = array[negIndex[negIndex.length] + 1];
//                if (negIndex.length == 2) {
//                    for (int j = 1; j < i; j++) {
//                        str = str.concat(array[j]);
//                    }
//                    for (int k = i + 2; k < array.length; k++) {
//                        str2 = str2.concat(array[k]);
//                    }
//                } else {
//                    for (int j = 1; j < i; j++) {
//                        str = str.concat(array[j]);
//                    }
//                    for (int k = i + 2; k < array.length; k++) {
//                        str2 = str2.concat(array[k]);
//                    }
//                }
//                myResult = Double.parseDouble(str) - Double.parseDouble(str2);
//                clearStringBuilder();
//                editText.setText(stringBuilder.append(round(myResult)));
//            } else if (array[i].equals("/") && !array[0].equals("/")) {
//                String str = array[0];
//                String str2 = array[i + 1];
//                for (int j = 1; j < i; j++) {
//                    str = str.concat(array[j]);
//                }
//                for (int k = i + 2; k < array.length; k++) {
//                    str2 = str2.concat(array[k]);
//                }
//                myResult = Double.parseDouble(str) / Double.parseDouble(str2);
//                clearStringBuilder();
//                editText.setText(stringBuilder.append(round(myResult)));
//            } else if (array[i].equals("*") && !array[0].equals("*")) {
//                String str = array[0];
//                String str2 = array[i + 1];
//                for (int j = 1; j < i; j++) {
//                    str = str.concat(array[j]);
//                }
//                for (int k = i + 2; k < array.length; k++) {
//                    str2 = str2.concat(array[k]);
//                }
//                myResult = Double.parseDouble(str) * Double.parseDouble(str2);
//                clearStringBuilder();
//                editText.setText(stringBuilder.append(round(myResult)));
//            }
//        }
        }
    }

    private double round(double value) {


        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void editTextPlace() {
        editText = findViewById(R.id.edit_text);
    }

    private void buttonZeroListener() {
        Button buttonZero = findViewById(R.id.zero);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(0));
                String firstNumber = editText.getText().toString();
                System.out.println(editText.length());
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonOneListener() {
        Button buttonOne = findViewById(R.id.one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(1));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonTwoListener() {
        Button buttonTwo = findViewById(R.id.two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(2));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonThreeListener() {
        Button buttonThree = findViewById(R.id.three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(3));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonFourListener() {
        Button buttonFour = findViewById(R.id.four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(4));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonFiveListener() {
        Button buttonFive = findViewById(R.id.five);
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(5));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonSixListener() {
        Button buttonSix = findViewById(R.id.six);
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(6));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonSevenListener() {
        Button buttonSeven = findViewById(R.id.seven);
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(7));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonEightListener() {
        Button buttonEight = findViewById(R.id.eight);
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(8));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }

    private void buttonNineListener() {
        Button buttonNine = findViewById(R.id.nine);
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(9));
                //editText.setText(stringBuilder.append(1));
                String firstNumber = editText.getText().toString();
                System.out.println(firstNumber);
            }
        });
    }
}

