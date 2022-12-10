package com.example.mealerapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Activity.MealDetails;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {


    private List<MealDomain> mealDomains;
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




        holder.textViewMealName.setText(mealDomain.getName());
        holder.textViewRating.setText("" + mealDomain.getRating() + "/5");
        holder.textViewDesc.setText(mealDomain.getDescription());
        holder.textViewIngredients.setText(
                String.join(", ", mealDomain.getIngredients())
        );
        holder.textViewAllergens.setText(
                String.join(", ", mealDomain.getAllergens())
        );
        holder.textViewPrice.setText("$" + mealDomain.getPrice());

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> data = mealDomain.toHashMap();
                Intent intent = new Intent(holder.itemView.getContext(), MealDetails.class);
                intent.putExtra("data", (Serializable) data);
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mealDomains.size();
    }

    public void setFilteredList(List<MealDomain> filteredMeals) {
        mealDomains = filteredMeals;
        notifyDataSetChanged();
    }

    public void setData(List<MealDomain> data){
        mealDomains = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout mealCard;
        private TextView textViewMealName;
        private TextView textViewRating;
        private TextView textViewDesc;
        private TextView textViewIngredients;
        private TextView textViewAllergens;
        private TextView textViewPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealCard = itemView.findViewById(R.id.search_mealCard);
            textViewMealName = itemView.findViewById(R.id.textViewOrderMealName);
            textViewDesc = itemView.findViewById(R.id.search_textViewMealDesc);
            textViewIngredients = itemView.findViewById(R.id.search_textViewIngredients);
            textViewAllergens = itemView.findViewById(R.id.search_textViewAllergens);
            textViewRating = itemView.findViewById(R.id.search_textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewTotalOrderPrice);


        }
    }
}
