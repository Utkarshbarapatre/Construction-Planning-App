package com.example.signup_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.signup_form.FloorPlanPages.FloorPlanPage;
import com.example.signup_form.Inventory.inventorypage;
import com.example.signup_form.Managerpages.loginpage;
import com.example.signup_form.Settingspages.Setting;
import com.example.signup_form.Workerpages.WorkerTaskPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class workerMainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth constructionplanning;
    CardView Wtaskcardview,Winventarygrid,WorkerViewPlanCardview,WSettingcardview,WPhotoscardview,WUpldPhotoscardview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);
        constructionplanning = FirebaseAuth.getInstance();

        Toolbar menu=findViewById(R.id.menu);
        setSupportActionBar(menu);
        Wtaskcardview=(CardView) findViewById(R.id.Wtaskcardview);
        WUpldPhotoscardview=(CardView)findViewById(R.id.WUpldPhotoscardview);
        WPhotoscardview=(CardView)findViewById(R.id.WPhotoscardview);
        Winventarygrid=(CardView) findViewById(R.id.Winventarygrid);
        WorkerViewPlanCardview=(CardView) findViewById(R.id.WorkerViewPlanCardview);
        WPhotoscardview=(CardView) findViewById(R.id.WPhotoscardview);
        WSettingcardview=(CardView) findViewById(R.id.WSettingcardview);
        Wtaskcardview.setOnClickListener(this);
        Winventarygrid.setOnClickListener(this);
        WorkerViewPlanCardview.setOnClickListener(this);
        WPhotoscardview.setOnClickListener(this);
        WSettingcardview.setOnClickListener(this);
        WUpldPhotoscardview.setOnClickListener(this);
        WPhotoscardview.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = constructionplanning.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(workerMainActivity.this, loginpage.class));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout:
                startActivity(new Intent(workerMainActivity.this, firstpage.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.Wtaskcardview:
                i=new Intent(this, WorkerTaskPage.class);
                startActivity(i);
            case R.id.Winventarygrid:
                i=new Intent(this, inventorypage.class);
                startActivity(i);
                break;
            case R.id.WSettingcardview:
                i=new Intent(this, Setting.class);
                startActivity(i);
                break;
            case R.id.WorkerViewPlanCardview:
                i=new Intent(this, FloorPlanPage.class);
                startActivity(i);
                break;
            case R.id.WPhotoscardview:
                i=new Intent(this, PhotosPage.class);
                startActivity(i);
                break;
            case R.id.WUpldPhotoscardview:
                i=new Intent(this, UploadPhotos.class);
                startActivity(i);
                break;


        }

    }



}