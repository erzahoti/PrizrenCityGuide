package com.erza.prizrencityguide.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erza.prizrencityguide.MainActivity;
import com.erza.prizrencityguide.R;

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
                Intent intent = new Intent(PreferencesActivity.this, AppIntroduction.class);
                startActivity(intent);
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




    }

}
