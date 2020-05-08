package com.example.akhbariapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.akhbariapp.Fragments.AdminPost;
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
import com.example.akhbariapp.RecyclerViewAdapters.PostRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private SharedPreferences admin;
    private SharedPreferences.Editor editor;
    private PostRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home_interface);

        admin = getSharedPreferences("Admin",MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.admin_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new Today()).commit();
            navigationView.setCheckedItem(R.id.today);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.admin_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        //adapter = new PostRecyclerViewAdapter(this,);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if (id==R.id.contact_us){

            Intent intent = new Intent(AdminHomeActivity.this,ContactUs.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {
        Animation animation;

        switch (item.getItemId()){
            case R.id.admin_nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Today()).commit();
                break;
            case R.id.admin_add_post:
                animation = AnimationUtils.loadAnimation(this,R.anim.bounce_animation);
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new AdminPost(animation)).addToBackStack(null).commit();
                break;
            case R.id.admin_nav_msg:
                animation = AnimationUtils.loadAnimation(this,R.anim.bounce_animation);
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Inbox(animation)).addToBackStack(null).commit();
                break;
        }

        return true;
    };

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
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Today()).commit();
                break;
            case R.id.this_week:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new ThisWeek()).commit();
                break;
            case R.id.past:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Past()).commit();
                break;
            case R.id.transport:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Transport()).commit();
                break;
            case R.id.health:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Health()).commit();
                break;
            case R.id.sport:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Sport()).commit();
                break;
            case R.id.politics:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Politics()).commit();
                break;
            case R.id.education:
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new Education()).commit();
                break;

            case R.id.log_out:
                admin = getSharedPreferences("Admin",MODE_PRIVATE);
                editor = admin.edit();
                editor.putString("access","no");
                editor.apply();
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
            editor = admin.edit();
            editor.putString("access","yes");
            editor.apply();
        }
    }
}
