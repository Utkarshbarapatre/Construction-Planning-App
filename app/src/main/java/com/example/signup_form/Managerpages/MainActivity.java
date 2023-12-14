package com.example.signup_form.Managerpages;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.signup_form.FloorPlanPages.FloorPlanPage;
import com.example.signup_form.PhotosPage;
import com.example.signup_form.Profile;
import com.example.signup_form.R;
import com.example.signup_form.Settingspages.Setting;
import com.example.signup_form.TaskPages.TaskPage;
import com.example.signup_form.UploadPhotos;
import com.example.signup_form.firstpage;
import com.example.signup_form.Inventory.inventorypage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth constructionplanning;
    CardView taskcardview,inventarygrid,CreatePlanCV,Settingcardview,ViewPlanCardview,UpldPhotoscardview,Photocardview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constructionplanning = FirebaseAuth.getInstance();

        Toolbar menu=findViewById(R.id.menu);
        setSupportActionBar(menu);
        taskcardview=(CardView) findViewById(R.id.taskcardview);
        inventarygrid=(CardView) findViewById(R.id.inventarygrid);
        CreatePlanCV=(CardView) findViewById(R.id.CreatePlanCV);
        ViewPlanCardview=(CardView) findViewById(R.id.ViewPlanCardview);
        Settingcardview=(CardView) findViewById(R.id.Settingcardview);
        UpldPhotoscardview=(CardView)findViewById(R.id.UpldPhotoscardview);
        Photocardview=(CardView)findViewById(R.id.Photocardview);

        taskcardview.setOnClickListener(this);
        inventarygrid.setOnClickListener(this);
        CreatePlanCV.setOnClickListener(this);
        ViewPlanCardview.setOnClickListener(this);
        Settingcardview.setOnClickListener(this);
        UpldPhotoscardview.setOnClickListener(this);
        Photocardview.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = constructionplanning.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, loginpage.class));
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
                startActivity(new Intent(MainActivity.this, firstpage.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.taskcardview:
                i=new Intent(this, TaskPage.class);
                startActivity(i);
                break;

            case R.id.inventarygrid:
                i=new Intent(this, inventorypage.class);
                startActivity(i);
                break;
//            case R.id.Profilecardview:
//                i=new Intent(this, Profile.class);
//                startActivity(i);
//                break;
            case R.id.Settingcardview:
                i=new Intent(this, Setting.class);
                startActivity(i);
                break;
            case R.id.CreatePlanCV:
                Intent Webintent=new Intent(Intent.ACTION_VIEW,(Uri.parse("https://www.smartdraw.com/floor-plan/floor-plan-designer.htm")));
                startActivity(Webintent);

                break;
            case R.id.ViewPlanCardview:
                i=new Intent(this, FloorPlanPage.class);
                startActivity(i);
                break;
            case R.id.UpldPhotoscardview:
                i=new Intent(this, UploadPhotos.class);
                startActivity(i);
                break;
            case R.id.Photocardview:
                i=new Intent(this, PhotosPage.class);
                startActivity(i);
                break;




        }

    }
}