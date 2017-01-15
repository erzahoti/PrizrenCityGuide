package com.erza.prizrencityguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erza.prizrencityguide.Monuments.Monuments;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

public class ContactUs extends AppCompatActivity {

    private EditText mTxtSubject;
    private EditText mTxtMessage;
    private Button mSubmitButton;
    private EditText mEmailFrom;

    private String email="erzahoti96@gmail.com"; //email-it qe do ti dergohet mesazhi, kam fut email-in tim per test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSubmitButton = (Button) findViewById(R.id.submit_contact);
        mTxtMessage = (EditText) findViewById(R.id.message_contact);
        //mEmailFrom = (EditText) findViewById(R.id.email_contact);
        mTxtSubject = (EditText) findViewById(R.id.subject_contact);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = mTxtSubject.getText().toString();
                String message = mTxtMessage.getText().toString();
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                mail.putExtra(Intent.EXTRA_SUBJECT,subject);
                mail.putExtra(Intent.EXTRA_TEXT, message);
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail,"Send email via:"));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
