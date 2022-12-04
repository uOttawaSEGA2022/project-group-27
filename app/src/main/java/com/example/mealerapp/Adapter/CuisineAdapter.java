package com.example.mealerapp.Adapter;


import android.app.appsearch.SearchResults;
import android.content.Context;
import android.content.Intent;
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
import com.example.mealerapp.Activity.SearchResult;
import com.example.mealerapp.Domain.CuisineDomain;
import com.example.mealerapp.R;

import java.util.ArrayList;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.ViewHolder>{
    ArrayList<CuisineDomain> cuisineDomains;

    private String iconUrl;
    private String title;


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
        title = "";
        iconUrl="";

        switch(position){
            case 0:{
                title = "Italian";
                iconUrl="pizza";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 1:{
                title = "Chinese";
                iconUrl="ramen";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 2:{
                title = "Mexican";
                iconUrl="taco";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 3:{
                title = "Japanese";
                iconUrl="sushi";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 4:{
                title = "Arabic";
                iconUrl="falafel";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }



        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(iconUrl,"drawable",holder.itemView.getContext().getPackageName());

       Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.cuisineIcon);

       holder.mainLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(holder.itemView.getContext(), SearchResult.class);
               intent.putExtra("query", title);
               holder.itemView.getContext().startActivity(intent);
           }
       });
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
