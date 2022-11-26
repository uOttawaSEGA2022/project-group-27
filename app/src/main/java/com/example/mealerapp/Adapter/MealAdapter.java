package com.example.mealerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Domain.ManageMealDomain;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.R;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {


    private ArrayList<MealDomain> mealDomains;
    private Context mContext;


    public MealAdapter(ArrayList<MealDomain> mealDomains, Context mContext){
        this.mealDomains = mealDomains;
        this.mContext = mContext;
    }

    public MealAdapter(MealDomain mealDomain){
        mealDomains = new ArrayList<>();
        mealDomains.add(mealDomain);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDomain mealDomain = mealDomains.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout mealCard;
        private TextView textViewMealName;
        private TextView textViewRating;
        private TextView textViewDesc;
        private TextView textViewIngredients;
        private TextView textViewAllergens;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealCard = itemView.findViewById(R.id.mealCard);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewDesc = itemView.findViewById(R.id.textViewMealDesc);
            textViewIngredients = itemView.findViewById(R.id.textViewIngredients);
            textViewAllergens = itemView.findViewById(R.id.textViewAllergens);
            textViewRating = itemView.findViewById(R.id.textViewRating);


        }
    }
}
