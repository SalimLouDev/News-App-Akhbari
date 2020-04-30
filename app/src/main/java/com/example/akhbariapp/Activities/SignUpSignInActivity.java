package com.example.akhbariapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.akhbariapp.Fragments.SignIn;
import com.example.akhbariapp.Fragments.Today;
import com.example.akhbariapp.R;

public class SignUpSignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_sign, new SignIn()).commit();
        }
    }
}
