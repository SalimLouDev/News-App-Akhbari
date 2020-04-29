package com.example.akhbariapp;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView signUp = findViewById(R.id.signUp);
        TextView logIn  = findViewById(R.id.logIn);

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.sign_up_fragment);
                }
            });
    }
}
