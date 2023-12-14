package com.example.signup_form.Inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signup_form.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class inventoryform extends AppCompatActivity {
    String[] items={"bags","numbers","box","catridge","each","pack","Dozen","meter","milimeter","centimeter","feet","running","feat","inch","yard","kilometer","kilogram","gram","liter","quintal"};
    AutoCompleteTextView txtunit;
    ArrayAdapter<String> adapteritems;
    EditText txtName,txtspecification,txtstock,txtamount;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventoryform);

        txtName=(EditText) findViewById(R.id.txtName);
        txtspecification=(EditText) findViewById(R.id.txtspecification);
        txtstock=(EditText) findViewById(R.id.txtstock);
        txtamount=(EditText)findViewById(R.id.txtamount);
        txtunit=findViewById(R.id.txtunit);
        adapteritems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        txtunit.setAdapter(adapteritems);
        txtunit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), inventorypage.class));
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processincert();
            }

        });
    }
    private void processincert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Name",txtName.getText().toString());
        map.put("Specifications",txtspecification.getText().toString());
        map.put("Stock",txtstock.getText().toString());
        map.put("Unit",txtunit.getText().toString());
        map.put("Amount",txtamount.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Inventory").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        txtName.setText("");
                        txtspecification.setText("");
                        txtstock.setText("");
                        txtunit.setText("");
                        txtamount.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not Insert",Toast.LENGTH_LONG).show();

                    }
                });

    }
}