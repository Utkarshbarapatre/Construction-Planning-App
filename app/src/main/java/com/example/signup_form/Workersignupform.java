package com.example.signup_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Workersignupform extends AppCompatActivity {
    EditText Wfirstname, Wlastname, Wemail, Wpassword, Wcompanyname, Wphoneno;
    Button Wsignup;
    FirebaseAuth constructionplanning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workersignupform);
        Wfirstname = findViewById(R.id.Wfirstname);
        Wlastname = findViewById(R.id.Wlastname);
        Wemail = findViewById(R.id.Wemail);
        Wpassword = findViewById(R.id.Wpassword);
        Wcompanyname = findViewById(R.id.Wcompanyname);
        Wphoneno = findViewById(R.id.Wphoneno);
        Wsignup = findViewById(R.id.Wsignup);
        constructionplanning = FirebaseAuth.getInstance();

        Wsignup.setOnClickListener(view -> {
            createuser();
        });
    }

    private void createuser() {
        String Firstname = Wfirstname.getText().toString();
        String Lastname = Wlastname.getText().toString();
        String Email = Wemail.getText().toString();
        String Password = Wpassword.getText().toString();
        String Company = Wcompanyname.getText().toString();
        String Phoneno = Wphoneno.getText().toString();

        if (TextUtils.isEmpty(Wfirstname.getText().toString())) {
            Wfirstname.setError("First name cannot be empty!");
            Wfirstname.requestFocus();
        } else if (TextUtils.isEmpty(Wlastname.getText().toString())) {
            Wlastname.setError("Last name cannot be empty!");
            Wlastname.requestFocus();
        } else if (TextUtils.isEmpty(Wemail.getText().toString())) {
            Wemail.setError("Email canot be empty!");
            Wemail.requestFocus();
        } else if (TextUtils.isEmpty(Wpassword.getText().toString())) {
            Wpassword.setError("password cannot be empty!");
            Wpassword.requestFocus();
        } else if (TextUtils.isEmpty(Wcompanyname.getText().toString())) {
            Wcompanyname.setError("Company name cannot be empty!");
            Wcompanyname.requestFocus();
        } else if (TextUtils.isEmpty(Wphoneno.getText().toString())) {
            Wphoneno.setError("Phone no cannot be empty!");
            Wphoneno.requestFocus();
        } else {
            constructionplanning.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Workersignupform.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Workersignupform.this, workerMainActivity.class));

                    } else {
                        Toast.makeText(Workersignupform.this, "Sign up error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

    }
}
