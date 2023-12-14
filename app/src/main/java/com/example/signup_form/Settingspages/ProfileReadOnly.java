package com.example.signup_form.Settingspages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.signup_form.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileReadOnly extends AppCompatActivity {
    ListView li;
    ArrayAdapter<String> adapter;
    DatabaseReference constructionplanning;
    FirebaseUser user;

    List<String> itemList;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_read_only);
        li=(ListView) findViewById(R.id.listview);
        uid=user.getUid();
        itemList=new ArrayList<>();

        constructionplanning= FirebaseDatabase.getInstance().getReference();

        constructionplanning.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();

                    String user_name=dataSnapshot.child(uid).child("Email").getValue(String.class);
                    String user_pass=dataSnapshot.child(uid).child("Password").getValue(String.class);

                    itemList.add(user_name);
                    itemList.add(user_pass);
                adapter = new ArrayAdapter<>(ProfileReadOnly.this, android.R.layout.simple_list_item_1,itemList);
                li.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileReadOnly.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        }));
    }
}






