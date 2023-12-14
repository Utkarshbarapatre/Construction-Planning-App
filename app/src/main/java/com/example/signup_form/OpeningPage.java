package com.example.signup_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class OpeningPage extends AppCompatActivity {
    TextView TitleName;
    LottieAnimationView LottiAnim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);
        TitleName=findViewById(R.id.TitleName);
        LottiAnim1=findViewById(R.id.LottiAnim1);

        //TitleName.animate().translationY(1400).setDuration(2700).setStartDelay(0);
        //LottiAnim1.animate().translationX(2000).setDuration(2000).setDuration(2000);
        TitleName.animate().translationY(700).setDuration(2700).setStartDelay(0);;
        LottiAnim1.animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),UserChoiceLgn.class);
                startActivity(i);
            }
        },5000);
    }
}