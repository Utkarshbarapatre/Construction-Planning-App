package com.example.signup_form;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    Context PContext;
    List<PhotosModel> photosModelList;

    public PhotosAdapter(Context PContext, List<PhotosModel> photosModelList) {
        this.PContext = PContext;
        this.photosModelList = photosModelList;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.photositem,parent,false);
        return new PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {

        PhotosModel photosModel=photosModelList.get(position);


        String imageURI=null;
        imageURI=photosModel.getPhoto();
        Picasso.get().load(imageURI).into(holder.Photoitemimage);

    }

    @Override
    public int getItemCount() {
        return photosModelList.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        ImageView Photoitemimage;


        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);

            Photoitemimage=itemView.findViewById(R.id.Photoitemimage);



        }
    }
}
