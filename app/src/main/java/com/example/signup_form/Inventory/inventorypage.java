package com.example.signup_form.Inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.signup_form.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class inventorypage extends AppCompatActivity {
    RecyclerView Recview;
    FloatingActionButton ADDButton;
    InvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventorypage);

        ADDButton=(FloatingActionButton)findViewById(R.id.ADDButton);

        Recview = (RecyclerView) findViewById(R.id.Recview);
        Recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Inventorymodel> options =
                new FirebaseRecyclerOptions.Builder<Inventorymodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Inventory"), Inventorymodel.class)
                        .build();

        adapter = new InvAdapter(options);
        Recview.setAdapter(adapter);

        ADDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(inventorypage.this, inventoryform.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}




