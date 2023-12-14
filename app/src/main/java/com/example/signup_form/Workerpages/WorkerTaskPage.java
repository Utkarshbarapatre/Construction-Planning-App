package com.example.signup_form.Workerpages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.signup_form.R;
import com.example.signup_form.TaskPages.TaskAdapter;
import com.example.signup_form.TaskPages.TaskForm;
import com.example.signup_form.TaskPages.TaskModel;
import com.example.signup_form.TaskPages.TaskPage;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerTaskPage extends AppCompatActivity {
    RecyclerView TRecview;
    TaskAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_task_page);
        TRecview = (RecyclerView) findViewById(R.id.TRecview);
        TRecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TaskModel> options =
                new FirebaseRecyclerOptions.Builder<TaskModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Task"), TaskModel.class)
                        .build();

        adapter = new TaskAdapter(options);
        TRecview.setAdapter(adapter);


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

