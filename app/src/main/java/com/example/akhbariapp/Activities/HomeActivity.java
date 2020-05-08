package com.example.akhbariapp.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.akhbariapp.Fragments.Education;
import com.example.akhbariapp.Fragments.Health;
import com.example.akhbariapp.Fragments.Inbox;
import com.example.akhbariapp.Fragments.Past;
import com.example.akhbariapp.Fragments.Politics;
import com.example.akhbariapp.Fragments.Sport;
import com.example.akhbariapp.Fragments.ThisWeek;
import com.example.akhbariapp.Fragments.Today;
import com.example.akhbariapp.Fragments.Transport;
import com.example.akhbariapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private SharedPreferences.Editor admin_editor;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_interface);

        SharedPreferences admin = getSharedPreferences("Admin", MODE_PRIVATE);
        admin_editor = admin.edit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Today()).commit();
            navigationView.setCheckedItem(R.id.today);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if (id==R.id.contact_us){

            Intent intent = new Intent(HomeActivity.this,ContactUs.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {

        switch (item.getItemId()){

            case R.id.admin_nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Today()).commit();
                break;
            case R.id.admin_nav_msg:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Inbox()).addToBackStack(null).commit();
                break;

        }

        return true;
    };
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.today :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Today()).commit();
                break;
            case R.id.this_week:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ThisWeek()).commit();
                break;
            case R.id.past:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Past()).commit();
                break;
            case R.id.health:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Health()).commit();
                break;
            case R.id.sport:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sport()).commit();
                break;
            case R.id.transport:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Transport()).commit();
                break;
            case R.id.politics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Politics()).commit();
                break;
            case R.id.education:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Education()).commit();
                break;

            case R.id.log_out:
                admin_editor.putString("access","no");
                admin_editor.apply();
                Intent intent = new Intent(this, SignUpSignInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            admin_editor.putString("access","yes");
            admin_editor.apply();
        }
    }
}
