package com.example.signup_form.Settingspages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.signup_form.Managerpages.MainActivity;
import com.example.signup_form.R;
import com.example.signup_form.firstpage;

public class Setting extends AppCompatActivity {
    ImageView faqIcon, logOutIcon, securityIcon,aboutUsIcon,btnBack,messageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        faqIcon=findViewById(R.id.faqIcon);
        messageIcon=findViewById(R.id.messageIcon);
        securityIcon=findViewById(R.id.securityIcon);

        logOutIcon=findViewById(R.id.logOutIcon);
        aboutUsIcon=findViewById(R.id.aboutUsIcon);
        btnBack=findViewById(R.id.btnBack);





        aboutUsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting.this,AboutUs.class));

            }
        });
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting.this,Sendusmessage.class));

            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting.this, MainActivity.class));
            }
        });
        faqIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting.this,FAQs.class));
            }
        });
        logOutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting.this, firstpage.class));
            }
        });


    }

    public void Web (View view){
        Intent Webintent=new Intent(Intent.ACTION_VIEW,(Uri.parse("https://firebase.google.com/docs/rules")));
        startActivity(Webintent);

    }
}