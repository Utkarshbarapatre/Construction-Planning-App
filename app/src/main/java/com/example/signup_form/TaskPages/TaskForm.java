package com.example.signup_form.TaskPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup_form.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TaskForm extends AppCompatActivity {
    TextView assignedworkr, taskdateselected, taskEdateselected;
    ImageView TaskAttachmentpic;
    EditText TaskName, Task_description;
    Button UploadTask;
    String[] statusitems={"Completed","Pending"};
    AutoCompleteTextView Taskstatus;
    ArrayAdapter<String> adapterstatusitems;


    String[] priorityitems={"Low","Medium","High"};
    AutoCompleteTextView Task_priorityET;
    ArrayAdapter<String> adapterpriorityitems;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        assignedworkr = findViewById(R.id.assignedworkr);
        taskdateselected = findViewById(R.id.taskdateselected);
        taskEdateselected = findViewById(R.id.taskEdateselected);
        TaskName = findViewById(R.id.TaskName);
        Task_description = findViewById(R.id.Task_description);
        UploadTask = findViewById(R.id.UploadTask);
        Task_priorityET = findViewById(R.id.Task_priorityET);
        Taskstatus = findViewById(R.id.Taskstatus);
        TaskAttachmentpic=findViewById(R.id.TaskAttachmentpic);

        adapterpriorityitems=new ArrayAdapter<String>(this,R.layout.list_item,priorityitems);
        Task_priorityET.setAdapter(adapterpriorityitems);
        Task_priorityET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        adapterstatusitems=new ArrayAdapter<String>(this,R.layout.list_item,statusitems);
        Taskstatus.setAdapter(adapterstatusitems);
        Taskstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);

        final int year2=calendar.get(Calendar.YEAR);
        final int month2=calendar.get(Calendar.MONTH);
        final int dayOfMonth2=calendar.get(Calendar.DAY_OF_MONTH);

        taskdateselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog
                        (TaskForm.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,dayOfMonth);
                datePickerDialog.show();

            }
        });
        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                taskdateselected.setText(date);

            }
        };

        taskEdateselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog
                        (TaskForm.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener2,year2,month2,dayOfMonth2);
                datePickerDialog.show();

            }
        });
        setListener2=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {
                month2=month2+1;
                String date2=dayOfMonth2+"/"+month2+"/"+year2;
                taskEdateselected.setText(date2);

            }
        };





        UploadTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processincerttask();
            }

            private void processincerttask() {
                Map<String, Object> map = new HashMap<>();
                map.put("TASKNAME", TaskName.getText().toString());
                map.put("TASk_DESCRIPTION", Task_description.getText().toString());
                map.put("AssignedWORKER", assignedworkr.getText().toString());
                map.put("TASKDATESELECTED", taskdateselected.getText().toString());
                map.put("TASKEDATESELECTED", taskEdateselected.getText().toString());
                map.put("Task_priorityET", Task_priorityET.getText().toString());
                map.put("Taskstatus", Taskstatus.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Task").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                TaskName.setText("");
                                Task_description.setText("");
                                assignedworkr.setText("");
                                taskdateselected.setText("");
                                taskEdateselected.setText("");
                                Task_priorityET.setText("");
                                Taskstatus.setText("");
                                Toast.makeText(getApplicationContext(), "Task Uploaded Successfully", Toast.LENGTH_LONG).show();


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Could not Upload Task", Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });


    }

}




