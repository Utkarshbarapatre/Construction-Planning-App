package com.example.signup_form.TaskPages;

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

public class TaskPage extends AppCompatActivity {
    RecyclerView TRecview;
    FloatingActionButton TADDBTN;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);

        TADDBTN = (FloatingActionButton) findViewById(R.id.TADDBTN);

        TRecview = (RecyclerView) findViewById(R.id.TRecview);
        TRecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TaskModel> options =
                new FirebaseRecyclerOptions.Builder<TaskModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Task"), TaskModel.class)
                        .build();

        adapter = new TaskAdapter(options);
        TRecview.setAdapter(adapter);

        TADDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskPage.this, TaskForm.class));
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
