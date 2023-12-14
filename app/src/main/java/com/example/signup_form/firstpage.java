package com.example.signup_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.signup_form.Managerpages.Signup_Form;
import com.example.signup_form.Managerpages.loginpage;

public class firstpage extends AppCompatActivity {
    Button button,button2;
    TextView fpt1,fpt2,fpt3;
    ImageView fpiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(firstpage.this, Signup_Form.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(firstpage.this, loginpage.class);
                startActivity(intent2);
            }
        });
    }
}