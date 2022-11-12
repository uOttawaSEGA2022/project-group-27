package com.example.mealerapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.ViewHolder>{
    ArrayList<CuisineDomain> cuisineDomains;

    public CuisineAdapter(ArrayList<CuisineDomain> cuisineDomains){
        this.cuisineDomains=cuisineDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cuisine,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineAdapter.ViewHolder holder, int position) {
        holder.cuisineName.setText(cuisineDomains.get(position).getTitle());
        String iconUrl="";

        switch(position){
            case 0:{
                iconUrl="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background1));
                break;
            }

            case 1:{
                iconUrl="cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 2:{
                iconUrl="cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background3));
                break;
            }

            case 3:{
                iconUrl="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background4));
                break;
            }

            case 4:{
                iconUrl="cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background5));
                break;
            }



        }

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(iconUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.cuisineIcon);

    }

    @Override
    public int getItemCount() {
        return cuisineDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cuisineName;
        ImageView cuisineIcon;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cuisineName=itemView.findViewById(R.id.cuisineName);
            cuisineIcon=itemView.findViewById(R.id.cuisineIcon);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
