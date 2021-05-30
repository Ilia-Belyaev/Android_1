package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private StringBuilder stringBuilder;
    private final double pi = Math.PI;
    private final double e = Math.E;

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
        negativeNumber();//отрицательные числа
        dark_theme_button();
        getScreenOrientation();//проверка ориентации

    }

    private void getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            number_pi();
            number_e();
            sqrt();
            factorial();//не сделал для дробных чисел, не получилось...
            absolutValue();
        }


    }

    private void absolutValue() {
        MaterialButton materialButton = findViewById(R.id.absolute);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().charAt(0) == '-') {
                    stringBuilder.deleteCharAt(0);
                    editText.setText(stringBuilder);
                }
            }
        });

    }

    private void factorial() {
        MaterialButton factorial = findViewById(R.id.factorial);
        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] str = stringBuilder.toString().split("(?<!\\d)|(?!\\d)");
                String newString = stringBuilder.toString();
                String str1 = str[0];
                if (str[0].equals("-") || newString.contains("/") || newString.contains("*") || newString.contains("+")) {
                    clearStringBuilder();
                    editText.setText(stringBuilder.append("there is no such factorial"));
                } else {
                    if (newString.contains(".")) {
                        for (int i = 1; i < str.length; i++) {
                            String str2 = str[i + 1];
                            for (int j = 1; j <= i; j++) {
                                str1 = str1.concat(str[j]);
                            }
                            for (int k = i + 2; k < str.length; k++) {
                                str2 = str2.concat(str[k]);
                            }
                            str1 = str1.concat(str2);
                            double src = Double.parseDouble(str1);
                            clearStringBuilder();
                            editText.setText(stringBuilder.append(Gamma(src)));
                            break;
                        }
                    } else {
                        for (int z = 1; z < str.length; z++) {
                            str1 = str1.concat(str[z]);
                        }
                        double src = Double.parseDouble(str1);
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(Gamma(src)));

                    }
                }
            }
        });
    }

    private static double Gamma(double z) {
        int res = (int) z; //целая часть
        double res2 = z - res; //дробная часть
        System.out.println((int) Math.pow(res + 1, res2));
        System.out.println(Math.pow(res + 1, res2));
        return getFactorial((int) Math.pow(res + 1, res2)) * getFactorial(res);
    }

    public static int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return f * getFactorial(f - 1);
        }
    }

    private void sqrt() {
        MaterialButton buttonSqrt = findViewById(R.id.sqrt);
        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double myResult = 0;
                String[] str = stringBuilder.toString().split("(?<!\\d)|(?!\\d)");
                String newString = stringBuilder.toString();
                String str1 = str[0];

                if (str[0].equals("-") || newString.contains("/") || newString.contains("*") || newString.contains("+")) {
                    clearStringBuilder();
                    editText.setText(stringBuilder.append("the root of a negative number"));
                } else {
                    if (newString.contains(".")) {
                        for (int i = 1; i < str.length; i++) {
                            String str2 = str[i + 1];
                            for (int j = 1; j <= i; j++) {
                                str1 = str1.concat(str[j]);
                            }
                            for (int k = i + 2; k < str.length; k++) {
                                str2 = str2.concat(str[k]);
                            }
                            str1 = str1.concat(str2);
                            myResult = Double.parseDouble(str1);
                            myResult = Math.sqrt(myResult);
                            clearStringBuilder();
                            editText.setText(stringBuilder.append(round(myResult)));
                            break;
                        }
                    } else {
                        for (int z = 1; z < str.length; z++) {
                            str1 = str1.concat(str[z]);
                        }
                        myResult = Double.parseDouble(str1);
                        myResult = Math.sqrt(myResult);
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(round(myResult)));

                    }
                }
            }
        });
    }

    private void replaceSign(EditText editText, char myChar) {/////////////////////для смены знака
        String myStr = String.valueOf(editText.getText());
        if (myStr.charAt(myStr.length()) == '/' ||
                myStr.charAt(myStr.length()) == '*' ||
                myStr.charAt(myStr.length()) == '+' ||
                myStr.charAt(myStr.length()) == '-') {
            myStr.substring(0, myStr.length() - 1);
        }

    }

    private void number_e() {
        MaterialButton buttonE = findViewById(R.id.e);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(e));
            }
        });
    }

    private void number_pi() {
        MaterialButton buttonPi = findViewById(R.id.pi);
        buttonPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(pi));
            }
        });
    }


    private void dark_theme_button() {
        SwitchCompat switchCompat = findViewById(R.id.switch_compat);
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNightMode = getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK;
                switch (currentNightMode) {
                    case Configuration.UI_MODE_NIGHT_NO: {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    }
                    case Configuration.UI_MODE_NIGHT_YES: {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    }
                    case Configuration.UI_MODE_NIGHT_UNDEFINED: {
                    }
                }
            }
        });
    }

    private void negativeNumber() {
        MaterialButton negativeButton = findViewById(R.id.negative);
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
        MaterialButton comma = findViewById(R.id.comma);
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
        MaterialButton percent_100 = findViewById(R.id.percent);
        percent_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numb = Double.parseDouble(String.valueOf(editText.getText()));
                numb = numb / 100;
                clearStringBuilder();
                editText.setText(stringBuilder.append(numb));
            }
        });
    }

    private void deleteOneNumber() {
        MaterialButton delete = findViewById(R.id.delete);
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
        MaterialButton clearAll = findViewById(R.id.clear);
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
        MaterialButton multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('*'));
                lookAround();
            }
        });
    }

    private void splitNumber() {
        MaterialButton split = findViewById(R.id.split);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('/'));
                lookAround();
            }
        });
    }

    private void subtractionNumber() {
        MaterialButton subtraction = findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('-'));
                lookAround();
            }
        });
    }

    private void additionNumber() {
        MaterialButton addition = findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append('+'));
                lookAround();
            }
        });
    }

    private void result() {
        MaterialButton equal = findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] str = stringBuilder.toString().split("(?<!\\d)|(?!\\d)");
                takeResult(str);
            }
        });


    }

    private void takeResult(String[] array) {
        double myResult;
        for (int i = 1; i < array.length; i++) {
            if (array[0].equals("-")) {
                switch (array[i]) {
                    case "+": {
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

                        break;
                    }
                    case "-": {
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
                        break;
                    }
                    case "/": {
                        String str = array[0];
                        String str2 = array[i + 1];
                        for (int j = 1; j < i; j++) {
                            str = str.concat(array[j]);
                        }
                        for (int k = i + 2; k < array.length; k++) {
                            str2 = str2.concat(array[k]);
                        }
                        if (str2.equals("0") || str2.equals("0.0")) {
                            String divisionByZero = "Division by zero!";
                            clearStringBuilder();
                            editText.setText(stringBuilder.append(divisionByZero));
                        } else {
                            myResult = Double.parseDouble(str) / Double.parseDouble(str2);
                            clearStringBuilder();
                            editText.setText(stringBuilder.append(round(myResult)));
                        }
                        break;
                    }
                    case "*": {
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
                        break;
                    }
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
                    if (str2.equals("0") || str2.equals("0.0")) {
                        String divisionByZero = "Division by zero!";
                        clearStringBuilder();
                        editText.setText(stringBuilder.append(divisionByZero));
                    } else {
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
        MaterialButton buttonZero = findViewById(R.id.zero);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(0));
            }
        });
    }

    private void buttonOneListener() {
        MaterialButton buttonOne = findViewById(R.id.one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(1));
            }
        });
    }

    private void buttonTwoListener() {
        MaterialButton buttonTwo = findViewById(R.id.two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(2));
            }
        });
    }

    private void buttonThreeListener() {
        MaterialButton buttonThree = findViewById(R.id.three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(3));
            }
        });
    }

    private void buttonFourListener() {
        MaterialButton buttonFour = findViewById(R.id.four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(4));
            }
        });
    }

    private void buttonFiveListener() {
        MaterialButton buttonFive = findViewById(R.id.five);
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(5));
            }
        });
    }

    private void buttonSixListener() {
        MaterialButton buttonSix = findViewById(R.id.six);
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(6));
            }
        });
    }

    private void buttonSevenListener() {
        MaterialButton buttonSeven = findViewById(R.id.seven);
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(7));
            }
        });
    }

    private void buttonEightListener() {
        MaterialButton buttonEight = findViewById(R.id.eight);
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(8));
            }
        });
    }

    private void buttonNineListener() {
        MaterialButton buttonNine = findViewById(R.id.nine);
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(stringBuilder.append(9));
            }
        });
    }
}

