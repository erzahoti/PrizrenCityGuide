package com.erza.prizrencityguide.Preferences;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.erza.prizrencityguide.R;


/**
 * Created by thaqibsm on 1/18/2017.
 */

public class PrivacyPolicy extends AppCompatActivity {

    Button btnPolicies;
    TextSwitcher text;
    int nowText = -1;

    String[] txt = {"What personal information do we collect from the people that visit our app?\n" + "Your name, email address or other details to help you with your experience","When do we collect information?\n" +
            "We collect information from you when you fill out a form or enter information on our site.", "\n" +
            "How do we use your information?\n" + "To personalize your experience and to allow us to deliver the type of content and product offerings in which you are most interested.", "How do we protect your information?\n" + "We do not use vulnerability scanning and/or scanning to PCI standards.\n" +
            "We only provide articles and information. We never ask for credit card numbers.\n" +
            "We do not use Malware Scanning."};

    int textNow = txt.length;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy_settings);

        btnPolicies = (Button) findViewById(R.id.btnNext);
        text = (TextSwitcher) findViewById(R.id.txtsPP);

        text.setFactory(new ViewSwitcher.ViewFactory(){
            public View makeView(){
                TextView txt = new TextView(PrivacyPolicy.this);
                txt.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                txt.setTextSize(18);
                txt.setTextColor(Color.BLACK);
                return txt;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        text.setAnimation(in);
        text.setAnimation(out);

        btnPolicies.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                nowText++;
                if(nowText == textNow)
                    nowText = 0; //kur arrihet limiti kthehet ne fillim
                text.setText(txt[nowText]);
            }

        });
    }
}
