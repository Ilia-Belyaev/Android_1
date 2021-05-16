package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TextView textView;
    private Editable emptyString;
    private String emptyString2;
    private Switch aSwitch;
    private CheckBox checkBox;
    private ToggleButton toggleButton;
    private LinearLayout linearLayout;
    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyString = editText.getText();
                editText.setText(textView.getText());
                textView.setText(emptyString);
            }
        });
        checkBox = findViewById(R.id.checkbox);
        aSwitch = findViewById(R.id.switch1);
        emptyString2 = (String) checkBox.getHint();
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    aSwitch.setBackgroundColor(5);
                    checkBox.setText("Забыть помыть посуду и получить люлей");
                    checkBox.setChecked(true);
                } else {
                    checkBox.setText(emptyString2);
                    checkBox.setChecked(false);
                }

            }
        });
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        calendarView = findViewById(R.id.calendar);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    toggleButton.setBackgroundColor(Color.BLACK);
                    toggleButton.setTextColor(Color.WHITE);

                }else {
                    toggleButton.setBackgroundColor(Color.WHITE);
                    toggleButton.setTextColor(Color.BLACK);

                }
            }
        });


    }


}