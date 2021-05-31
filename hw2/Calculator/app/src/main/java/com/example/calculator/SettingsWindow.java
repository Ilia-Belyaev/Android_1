package com.example.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SettingsWindow extends AppCompatActivity {
    private int numberCode;
    final String TAG = "States";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_window);
        initialization();

    }

    private void initialization() {
        dayMode();
    }

    private void dayMode() {
        MaterialButton button = findViewById(R.id.apply);
        RadioButton radioButtonDay = findViewById(R.id.day_mode);
        RadioButton radioButtonNight = findViewById(R.id.night_mode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                if (radioButtonDay.isChecked()) {
                    numberCode = Configuration.UI_MODE_NIGHT_NO;
                } else if (radioButtonNight.isChecked()) {
                    numberCode = Configuration.UI_MODE_NIGHT_YES;
                } else if(!radioButtonDay.isChecked()&&!radioButtonNight.isChecked()){
                    numberCode = 0;
                }
                intent.putExtra("numberCode", numberCode);
                Log.d(TAG, "SETTINGS activity: " + numberCode);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}