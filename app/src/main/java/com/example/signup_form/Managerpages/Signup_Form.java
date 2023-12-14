package com.example.signup_form.Managerpages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.signup_form.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signup_Form extends AppCompatActivity {
    EditText firstname, lastname, email, password, companyname, phoneno;
    Button signup;


    FirebaseAuth constructionplanning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        companyname = findViewById(R.id.companyname);
        phoneno = findViewById(R.id.phoneno);
        signup = findViewById(R.id.signup);
        constructionplanning = FirebaseAuth.getInstance();

        signup.setOnClickListener(view -> {
            createuser();
        });
    }

    private void createuser() {
        String Firstname = firstname.getText().toString();
        String Lastname = lastname.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Company = companyname.getText().toString();
        String Phoneno = phoneno.getText().toString();

        if (TextUtils.isEmpty(firstname.getText().toString())) {
            firstname.setError("First name cannot be empty!");
            firstname.requestFocus();
        } else if (TextUtils.isEmpty(lastname.getText().toString())) {
            lastname.setError("Last name cannot be empty!");
            lastname.requestFocus();
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Email canot be empty!");
            email.requestFocus();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("password cannot be empty!");
            password.requestFocus();
        } else if (TextUtils.isEmpty(companyname.getText().toString())) {
            companyname.setError("Company name cannot be empty!");
            companyname.requestFocus();
        } else if (TextUtils.isEmpty(phoneno.getText().toString())) {
            phoneno.setError("Phone no cannot be empty!");
            phoneno.requestFocus();
        } else {
            constructionplanning.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Signup_Form.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup_Form.this, MainActivity.class));

                    } else {
                        Toast.makeText(Signup_Form.this, "Sign up error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

    }
}







