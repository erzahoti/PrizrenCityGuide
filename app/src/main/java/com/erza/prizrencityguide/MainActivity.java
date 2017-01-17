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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.erza.prizrencityguide.AboutUs.AboutUs;
import com.erza.prizrencityguide.Accommodation.Accommodation;
import com.erza.prizrencityguide.Busses.Busses;
import com.erza.prizrencityguide.Entertainment.Entertainment;
import com.erza.prizrencityguide.Events.Cultural_Events;
import com.erza.prizrencityguide.Events.Sport_Events;
import com.erza.prizrencityguide.FoodDrink.Food_Drink;
import com.erza.prizrencityguide.Fragments.Home;
import com.erza.prizrencityguide.Monuments.Monuments;
import com.erza.prizrencityguide.PracticalInformation.PracticalInformation;
import com.erza.prizrencityguide.Preferences.PreferencesActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    ViewFlipper viewFlipper;
    ImageButton next;
    ImageButton previous;
    Button culturalButton;
    TextView tvUsername;
    Button sportButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername = (TextView) findViewById(R.id.tv_Username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if(username == null){
            tvUsername.setText("Prizren Tourist");
        }else {
            tvUsername.setText(username);
        }
        viewFlipper= (ViewFlipper) findViewById(R.id.viewFlipper);
        next=(ImageButton) findViewById(R.id.next);
        previous=(ImageButton) findViewById(R.id.prev);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        culturalButton= (Button) findViewById(R.id.eventet_kulturore);
        culturalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Cultural Events", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Cultural_Events.class);
                startActivity(i);
            }
        });
        sportButton= (Button) findViewById(R.id.eventet_sportive);
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Sport Events", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Sport_Events.class);
                startActivity(i);
            }
        });

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
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
            return true;
        }

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
            Toast.makeText(MainActivity.this, "Cultural Events", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Cultural_Events.class);
            startActivity(i);
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

            Toast.makeText(this, "Places for food and drinks", Toast.LENGTH_SHORT).show();
            Food_Drink foodDrink = new Food_Drink();
            /*FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, foodDrink, foodDrink.getTag()).commit();*/
            Intent intent = new Intent(MainActivity.this, Food_Drink.class);
            startActivity(intent);

        } else if (id == R.id.nav_accommodation) {
            Toast.makeText(this, "Accommodation", Toast.LENGTH_SHORT).show();
            Accommodation accommodation = new Accommodation();
            /*FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, accommodation, accommodation.getTag()).commit();*/
            Intent i = new Intent(MainActivity.this, Accommodation.class);
            startActivity(i);

        } else if (id == R.id.nav_busses) {

            Toast.makeText(this, "Busses", Toast.LENGTH_SHORT).show();
            Busses busses = new Busses();
            //FragmentManager manager = getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.content_frame, busses, busses.getTag()).commit();
            Intent i = new Intent(MainActivity.this, Busses.class);
            startActivity(i);

        } else if(id == R.id.nav_practical_information) {

            Toast.makeText(this, "Practical Information", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, PracticalInformation.class);
            startActivity(i);

        } else if (id == R.id.nav_about_us) {

            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
            AboutUs aboutus = new AboutUs();
            //FragmentManager manager = getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.content_frame, busses, busses.getTag()).commit();
            Intent i = new Intent(MainActivity.this, AboutUs.class);
            startActivity(i);

            return true;
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(MainActivity.this, ContactUs.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {

        if (view == next) {
            viewFlipper.showNext();
        } else if (view == previous) {
            viewFlipper.showPrevious();

        }
    }}
