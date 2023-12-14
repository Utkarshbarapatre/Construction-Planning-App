package com.example.signup_form.TaskPages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup_form.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends  FirebaseRecyclerAdapter <TaskModel,TaskAdapter.TaskViewHolder>
{
    public TaskAdapter(@NonNull FirebaseRecyclerOptions<TaskModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull TaskModel model)
    {
        holder.TASKName.setText("Task Name: "+model.getTASKNAME());
        holder.TASKSdate.setText("Task Start Date: "+model.getTASKDATESELECTED());
        holder.TASKSEdate.setText("Task End Date: "+model.getTASKEDATESELECTED());
        holder.TASKpriority.setText("Task's Priority: "+model.getTask_priorityET());
        holder.ASSIGNEDwrkr.setText("Assigned Worker: "+model.getAssignedWORKER());
        holder.TASKStatus.setText("Task's Status: "+model.getTaskstatus());

        holder.Taskbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.TASKName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.taskdialoguecontetnt))
                        .setExpanded(true,1600)
                        .create();
                View myview=dialogPlus.getHolderView();
                TextView TASKName=myview.findViewById(R.id.UDTaskName);
                TextView TASKSdate=myview.findViewById(R.id.UDtaskdateselected);
                TextView  TASKSEdate=myview.findViewById(R.id.UDtaskEdateselected);
                EditText TASKStatus=myview.findViewById(R.id.UDTaskstatus);
                Button UDUploadTask=myview.findViewById(R.id.UDUploadTask);

                TASKName.setText(model.getTASKNAME());
                TASKSdate.setText(model.getTASKDATESELECTED());
                TASKSEdate.setText(model.getTASKDATESELECTED());
                TASKStatus.setText(model.getTaskstatus());

                dialogPlus.show();
                UDUploadTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map=new HashMap<>();
                        map.put("TASKNAME",TASKName.getText().toString());
                        map.put("TASKDATESELECTED",TASKSdate.getText().toString());
                        map.put("TASKEDATESELECTED",TASKSEdate.getText().toString());
                        map.put("TASKStatus",TASKStatus.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Task")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });



            }
        });


        holder.Taskbtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(holder.TASKName.getContext());
                builder.setTitle("Delete panel");
                builder.setMessage("Delete Task...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Task")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tasksingleitem,parent,false);
        return new TaskViewHolder(view);

    }

    class TaskViewHolder extends RecyclerView.ViewHolder
    {
        //CircleImageView Timg1;
        TextView New_task_added,TASKName,TASKSdate,TASKSEdate,TASKpriority,TASKStatus,ASSIGNEDwrkr;;
        Button Taskbtnupdate,Taskbtndelete;

        //TextView TASKNAME,TASk_DESCRIPTION,AssignedWORKER,TASKDATESELECTED,TASKEDATESELECTED,Task_priorityET;
        public TaskViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Timg1=(CircleImageView)itemView.findViewById(R.id.Timg1);
            TASKName=(TextView)itemView.findViewById(R.id.TASKName);
            TASKSdate=(TextView)itemView.findViewById(R.id.TASKSdate);
            TASKSEdate=(TextView)itemView.findViewById(R.id.TASKSEdate);
            TASKpriority=(TextView)itemView.findViewById(R.id.TASKpriority);
            TASKStatus=(TextView)itemView.findViewById(R.id.TASKStatus);
            Taskbtnupdate=(Button)itemView.findViewById(R.id.Taskbtnupdate);
            Taskbtndelete=(Button)itemView.findViewById(R.id.Taskbtndelete);
            ASSIGNEDwrkr=itemView.findViewById(R.id.ASSIGNEDwrkr);




        }
    }



}
