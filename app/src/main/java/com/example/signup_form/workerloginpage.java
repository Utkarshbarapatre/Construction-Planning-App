package com.example.signup_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class workerloginpage extends AppCompatActivity {
    TextView WLt1, WLt2,register;
    EditText WLe1, WLe2;
    Button WLb1;

    FirebaseAuth constructionplanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerloginpage);
        WLt1 = findViewById(R.id.WLt1);
        WLt2 = findViewById(R.id.WLt2);
        WLe1 = findViewById(R.id.WLe1);
        WLe2 = findViewById(R.id.WLe2);
        register = findViewById(R.id.register);
        WLb1 = findViewById(R.id.WLb1);

        constructionplanning = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Workersignupform.class);
                startActivity(intent);
            }
        });


        WLb1.setOnClickListener(view -> {
            Loginuser();
        });
    }

    private void Loginuser() {
        String Email = WLe1.getText().toString();
        String Password = WLe2.getText().toString();

        if (TextUtils.isEmpty(WLe1.getText().toString())) {
            WLe1.setError("Email canot be empty!");
            WLe1.requestFocus();
        } else if (TextUtils.isEmpty(WLe2.getText().toString())) {
            WLe2.setError("password cannot be empty!");
            WLe2.requestFocus();
        } else {
            constructionplanning.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(workerloginpage.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(workerloginpage.this,workerMainActivity.class));
                    } else {
                        Toast.makeText(workerloginpage.this, "User logged in error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}
