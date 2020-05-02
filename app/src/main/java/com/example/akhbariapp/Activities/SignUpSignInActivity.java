package com.example.akhbariapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.akhbariapp.Fragments.SignIn;
import com.example.akhbariapp.R;

public class SignUpSignInActivity extends AppCompatActivity {

    private SharedPreferences admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);
        admin = getSharedPreferences("Admin",MODE_PRIVATE);
        save_admin_code();

       if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_sign, new SignIn()).commit();
        }
    }

    public void save_admin_code(){
        SharedPreferences.Editor last_fragment_editor = admin.edit();
        last_fragment_editor.putInt("admin_code",1234);
        last_fragment_editor.apply();
    }
}
