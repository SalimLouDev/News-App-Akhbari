package com.example.akhbariapp.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.akhbariapp.Fragments.Education;
import com.example.akhbariapp.Fragments.Past;
import com.example.akhbariapp.Fragments.Politics;
import com.example.akhbariapp.Fragments.ThisWeek;
import com.example.akhbariapp.Fragments.Today;
import com.example.akhbariapp.Fragments.Transport;
import com.example.akhbariapp.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_interface);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);
        return true;
    }

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

            case R.id.transport:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Transport()).commit();
                break;

            case R.id.politics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Politics()).commit();
                break;

            case R.id.education:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Education()).commit();
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
        }
    }
}
