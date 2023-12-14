package com.example.signup_form.Managerpages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;


import android.os.Bundle;
import android.widget.Toast;

import com.example.signup_form.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    EditText e1,e2;
    TextView t1,t2,Mregister;
    Button b1;

    FirebaseAuth constructionplanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        b1=findViewById(R.id.b1);
        Mregister=findViewById(R.id.Mregister);

        constructionplanning=FirebaseAuth.getInstance();

        Mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Signup_Form.class);
                startActivity(intent);
            }
        });


        b1.setOnClickListener(view -> {
            Loginuser();
        });
    }

    private void Loginuser() {
        String Email = e1.getText().toString();
        String Password = e2.getText().toString();

        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Email canot be empty!");
            e1.requestFocus();}
        else if(TextUtils.isEmpty(e2.getText().toString())){
            e2.setError("password cannot be empty!");
            e2.requestFocus();
        }else{
            constructionplanning.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(loginpage.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginpage.this, MainActivity.class));
                    }else{
                        Toast.makeText(loginpage.this, "User logged in error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}