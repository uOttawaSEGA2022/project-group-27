package com.example.mealerapp.Adapter;


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
import com.example.mealerapp.Domain.MealTypeDomain;
import com.example.mealerapp.R;

import java.util.ArrayList;

public class MealTypeAdapter extends RecyclerView.Adapter<MealTypeAdapter.ViewHolder>{
    ArrayList<MealTypeDomain> mealTypeDomains;

    private String iconUrl;

    public MealTypeAdapter(ArrayList<MealTypeDomain> mealTypeDomains){
        this.mealTypeDomains=mealTypeDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_mealtype,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MealTypeAdapter.ViewHolder holder, int position) {

        holder.mealTypeName.setText(mealTypeDomains.get(position).getTitle());
        iconUrl="";

        switch(position){
            case 0:{
                iconUrl="breakfast";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 1:{
                iconUrl="lunch";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 2:{
                iconUrl="dinner";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }

            case 3:{
                iconUrl="dessert";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cuis_background2));
                break;
            }




        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(iconUrl,"drawable",holder.itemView.getContext().getPackageName());

       Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.mealTypeIcon);

       holder.mainLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(holder.itemView.getContext(), SearchResult.class);
               intent.putExtra("query", iconUrl);
               holder.itemView.getContext().startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return mealTypeDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mealTypeName;
        ImageView mealTypeIcon;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealTypeName=itemView.findViewById(R.id.mealTypeName);
            mealTypeIcon=itemView.findViewById(R.id.mealTypeIcon);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }


    }
}
