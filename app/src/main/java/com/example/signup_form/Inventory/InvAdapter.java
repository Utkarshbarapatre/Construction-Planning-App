package com.example.signup_form.Inventory;

import android.app.AlertDialog;
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

public class InvAdapter extends FirebaseRecyclerAdapter<Inventorymodel,InvAdapter.InvViewHolder>
{
    public InvAdapter(@NonNull FirebaseRecyclerOptions<Inventorymodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InvViewHolder holder, final int position, @NonNull Inventorymodel model)
    {
        holder.INVname.setText("Material Name: "+model.getName());
        holder.INVspecification.setText("Specification: "+model.getSpecifications());
        holder.INVstock.setText("Stock: "+model.getStock());
        holder.INVUnit.setText("Unit: "+model.getUnit());
        holder.INVamount.setText("Amount Spent: "+model.getAmount());

                        holder.btnedit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.INVname.getContext())
                                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                        .setExpanded(true,1300)
                                        .create();

                                View myview=dialogPlus.getHolderView();
                                EditText INVname=myview.findViewById(R.id.udtName);
                                EditText INVspecification=myview.findViewById(R.id.udtspecification);
                                EditText INVstock=myview.findViewById(R.id.udtstock);
                                EditText INVamount=myview.findViewById(R.id.udtAmount);
                                Button Update=myview.findViewById(R.id.udtUpdate);

                                INVname.setText(model.getName());
                                INVspecification.setText(model.getSpecifications());
                                INVstock.setText(model.getStock());
                                INVamount.setText(model.getAmount());

                                dialogPlus.show();


                                        Update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Map<String,Object> map=new HashMap<>();
                                                map.put("Name",INVname.getText().toString());
                                                map.put("Specifications",INVspecification.getText().toString());
                                                map.put("Stock",INVstock.getText().toString());
                                                map.put("Amount",INVamount.getText().toString());

                                                FirebaseDatabase.getInstance().getReference().child("Inventory")
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

                        holder.btndelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder =new AlertDialog.Builder(holder.INVname.getContext());
                                builder.setTitle("Delete panel");
                                builder.setMessage("Delete...?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseDatabase.getInstance().getReference().child("Inventory")
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
    public InvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new InvViewHolder(view);
    }

    class InvViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView img1;
        TextView INVname,INVspecification,INVstock,INVUnit,INVamount;
        Button btnedit,btndelete;

        public InvViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=(CircleImageView)itemView.findViewById(R.id.img1);
            INVname=(TextView) itemView.findViewById(R.id.INVname);
            INVspecification=(TextView) itemView.findViewById(R.id.INVspecification);
            INVstock=(TextView) itemView.findViewById(R.id.INVstock);
            INVamount=(TextView)itemView.findViewById(R.id.INVamount);
            INVUnit=(TextView) itemView.findViewById(R.id.INVUnit);
            btnedit=(Button) itemView.findViewById(R.id.btnedit);
            btndelete=(Button) itemView.findViewById(R.id.btndelete);



        }
    }
}
