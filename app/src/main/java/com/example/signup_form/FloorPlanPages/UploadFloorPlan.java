package com.example.signup_form.FloorPlanPages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.signup_form.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadFloorPlan extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageView flrplnimageView;
    EditText floorPlanName,floorPlanDate;
    Button floorplanbtn;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_floor_plan);

        flrplnimageView=findViewById(R.id.flrplnimageView);
        floorPlanName=findViewById(R.id.floorPlanName);
        floorPlanDate=findViewById(R.id.floorPlanDate);
        floorplanbtn=findViewById(R.id.floorplanbtn);
        progressDialog=new ProgressDialog(this);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("FloorPlan");
        mStorage=FirebaseStorage.getInstance();


        flrplnimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            flrplnimageView.setImageURI(imageUrl);

        }
        floorplanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn=floorPlanName.getText().toString().trim();
                String fd=floorPlanDate.getText().toString().trim();

                if(!(fn.isEmpty() && fd.isEmpty() && imageUrl!=null))
                {
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath=mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newPost=mRef.push();
                                    newPost.child("FloorPlanName").setValue(fn);
                                    newPost.child("FloorPlanDate").setValue(fd);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();


                                    Intent intent=new Intent(UploadFloorPlan.this, FloorPlanPage.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                }
            }
        });
    }
}