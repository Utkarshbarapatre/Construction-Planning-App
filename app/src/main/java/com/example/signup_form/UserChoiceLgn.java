package com.example.signup_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.signup_form.Managerpages.MainActivity;
import com.example.signup_form.Managerpages.loginpage;

public class UserChoiceLgn extends AppCompatActivity {
    LottieAnimationView LottiAnim2;
    LottieAnimationView LottiAnim3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice_lgn);

        LottiAnim2=findViewById(R.id.LottiAnim2);
        LottiAnim3=findViewById(R.id.LottiAnim3);

        LottiAnim2.animate().setDuration(1000000).setStartDelay(0);;
        LottiAnim3.animate().setDuration(1000000).setStartDelay(0);;



        //autologin code
        SharedPreferences sharedPreference=getSharedPreferences("Worker",MODE_PRIVATE);
        String doctor = sharedPreference.getString("WorkerName","");
        if(doctor.equals("")){

        }
        else {
            Intent intent = new Intent(this, workerMainActivity.class);
            startActivity(intent);
            finish();
        }

        //admin
        SharedPreferences sharedPreference1=getSharedPreferences("Manager",MODE_PRIVATE);
        String admin = sharedPreference1.getString("ManagerName","");
        if(admin.equals("")){

        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void go(View view)
    {
        Intent intent = new Intent(this, loginpage.class);
        startActivity(intent);
    }

    public void go2(View view)
    {
        Intent intent = new Intent(this, workerloginpage.class);
        startActivity(intent);
    }
}

