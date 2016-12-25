package com.erza.prizrencityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.erza.prizrencityguide.FoodDrink.FoodDrink;
import com.erza.prizrencityguide.Accommodation.Accommodation;
import com.erza.prizrencityguide.Entertainment.Entertainment;
import com.erza.prizrencityguide.Fragments.Home;
import com.erza.prizrencityguide.Monuments.Monuments;
import com.erza.prizrencityguide.Busses.Busses;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(
        R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Home home = new Home();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, home, home.getTag()).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //if (id == R.id.nav_events) {
            //Intent intent = new Intent(this, EventsActivity.class);
            //startActivity(intent);
            //return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            Home home = new Home();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, home, home.getTag()).commit();
        } else if (id == R.id.nav_events) {
            return true;
        } else if (id == R.id.nav_monuments) {
            Toast.makeText(MainActivity.this, "Monuments", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Monuments.class);
            startActivity(i);

        } else if (id == R.id.nav_entertainment) {
            Toast.makeText(MainActivity.this, "Entertainment", Toast.LENGTH_SHORT).show();
           Entertainment entertainment = new Entertainment();
            /*FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, entertainment, entertainment.getTag()).commit();*/
            Intent in = new Intent(MainActivity.this, Entertainment.class);
            startActivity(in);


        } else if (id == R.id.nav_food_and_drink) {

            Toast.makeText(this, "Food and drink", Toast.LENGTH_SHORT).show();
            FoodDrink foodDrink = new FoodDrink();
            /*FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, foodDrink, foodDrink.getTag()).commit();*/
            Intent intent = new Intent(MainActivity.this, FoodDrink.class);
            startActivity(intent);

        } else if (id == R.id.nav_accommodation) {
            Toast.makeText(this, "Accommodation", Toast.LENGTH_SHORT).show();
            Accommodation accommodation = new Accommodation();
            /*FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, accommodation, accommodation.getTag()).commit();*/
            Intent i = new Intent(MainActivity.this, Accommodation.class);
            startActivity(i);

        }else if (id == R.id.nav_busses) {

            Toast.makeText(this, "Busses", Toast.LENGTH_SHORT).show();
            Busses busses = new Busses();
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.content_frame, busses, busses.getTag()).commit();
            Intent i = new Intent(MainActivity.this, Busses.class);
            startActivity(i);

        } else if (id == R.id.nav_about_us) {

            return true;
        } else if (id == R.id.nav_contact) {

            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
