package com.example.signup_form.FloorPlanPages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup_form.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FloorPlanAdapter extends RecyclerView.Adapter<FloorPlanAdapter.ViewHolder> {
    Context context;
    List<FloorplanModel> floorplanModelList;

    public FloorPlanAdapter(Context context, List<FloorplanModel> floorplanModelList) {
        this.context = context;
        this.floorplanModelList = floorplanModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.floorplanitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FloorplanModel floorplanModel=floorplanModelList.get(position);
        holder.planitemT1.setText("Floor Plan Name: "+floorplanModel.getFloorPlanName());
        holder.planitemT2.setText("Floor Plan Date: "+floorplanModel.getFloorPlanDate());

        String imageUri=null;
        imageUri=floorplanModel.getImage();
        Picasso.get().load(imageUri).into(holder.planitemimage);
    }

    @Override
    public int getItemCount() {
        return floorplanModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView planitemimage;
        TextView planitemT1,planitemT2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            planitemimage=itemView.findViewById(R.id.planitemimage);
            planitemT1=itemView.findViewById(R.id.planitemT1);
            planitemT2=itemView.findViewById(R.id.planitemT2);



        }
    }
}
