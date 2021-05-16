package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private EditText something,name,phone,number,pass,mail;
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        something = findViewById(R.id.editText);
        name = findViewById(R.id.editTextPersonName);
        phone = findViewById(R.id.editPhone);
        number = findViewById(R.id.editNumber);
        pass = findViewById(R.id.editPassword);
        mail = findViewById(R.id.editEmail);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Я всё про тебя знаю:" + "\n"
                        +   "Случайный текст: " + something.getText() +"\n"
                        +   "Ваше имя: "+name.getText() + "\n"
                        +   "Ваш телефон: " + phone.getText() + "\n"
                        +   "Ваше число: " + number.getText() + "\n"
                        +   "Ваш пароль: " + pass.getText() + "\n"
                        +   "Ваш Email: " + mail.getText() + "\n");
            }
        });
    }
}