package com.example.myksvit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HouseActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        //initialize recyclerview and FIrebase objects


        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();



    }
    @Override
    public void onBackPressed(){

        Intent intent =new Intent(this,HouseActivity.class);
        startActivity(intent);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                            break;
                        case R.id.nav_Library:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LibraryFragment()).commit();
                            break;
                        case R.id.nav_Events:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit();
                            break;
                        case R.id.nav_Timetable:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TimetableFragment()).commit();
                    }

                    return true;
                }
            };

    public void four(){


        Intent intent1 = new Intent(HouseActivity.this,LoginActivity.class);
        startActivity(intent1);
    }


}