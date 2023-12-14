package com.example.signup_form.FloorPlanPages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.signup_form.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class FloorPlanPage extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView floorplanRV;
    FloorPlanAdapter floorPlanAdapter;
    List<FloorplanModel> floorplanModelList;
    FloatingActionButton FloorPlanAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan_page);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("FloorPlan");
        mStorage=FirebaseStorage.getInstance();
        floorplanRV=findViewById(R.id.floorplanRV);
        floorplanRV.setHasFixedSize(true);
        floorplanRV.setLayoutManager(new LinearLayoutManager(this));


        floorplanModelList=new ArrayList<FloorplanModel>();
        floorPlanAdapter=new FloorPlanAdapter(FloorPlanPage.this,floorplanModelList);
        floorplanRV.setAdapter(floorPlanAdapter);

        FloorPlanAddButton=findViewById(R.id.FloorPlanAddButton);
        FloorPlanAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FloorPlanPage.this, UploadFloorPlan.class));
            }
        });


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                FloorplanModel floorplanModel=snapshot.getValue(FloorplanModel.class);
                floorplanModelList.add(floorplanModel);
                floorPlanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}