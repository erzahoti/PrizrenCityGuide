package com.erza.prizrencityguide;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.erza.prizrencityguide.R.layout.content_main;

public class PreferencesActivity extends AppCompatActivity {

    Button btnSaveUsername;
    Button btnOurApp;
    EditText etUsername;
    Button btnTheme1;
    Button btnTheme2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //PreferencesActivity.this.setTheme(R.style.CustomTheme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        etUsername = (EditText) findViewById(R.id.editText_Username);

        btnOurApp = (Button) findViewById(R.id.btn_OurApp);
        btnOurApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.introduction_settings);
            }
        });

        btnSaveUsername = (Button) findViewById(R.id.btn_SaveUsername);
        btnSaveUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
                intent.putExtra("username", etUsername.getText().toString());
                startActivity(intent);
            }
        });

        btnTheme1 = (Button) findViewById(R.id.btn_Theme1);
        btnTheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getApplication().setTheme(R.style.CustomTheme);
                Toast.makeText(PreferencesActivity.this, "Theme 1", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
