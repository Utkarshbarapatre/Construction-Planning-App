package com.example.signup_form;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class PhotosPage extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView photoRV;
    PhotosAdapter photosAdapter;
    List<PhotosModel> photosModelList;
    FloatingActionButton photoAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_page);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Photos");
        mStorage=FirebaseStorage.getInstance();

        photoRV=findViewById(R.id.photoRV);
        photoRV.setHasFixedSize(true);
        photoRV.setLayoutManager(new LinearLayoutManager(this));

        photosModelList=new ArrayList<PhotosModel>();
        photosAdapter=new PhotosAdapter(PhotosPage.this,photosModelList);
        photoRV.setAdapter(photosAdapter);

        photoAddButton=findViewById(R.id.photoAddButton);
        photoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhotosPage.this,UploadPhotos.class));
            }
        });

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PhotosModel photosModel=snapshot.getValue(PhotosModel.class);
                photosModelList.add(photosModel);
                photosAdapter.notifyDataSetChanged();
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