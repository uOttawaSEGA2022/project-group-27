package com.example.mealerapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder>{

    private ArrayList<MealDomain> mealDomains;

    public MealAdapter(ArrayList<MealDomain> mealDomains){
        this.mealDomains = mealDomains;
    }

    public MealAdapter(MealDomain mealDomain){
        mealDomains = new ArrayList<>();
        mealDomains.add(mealDomain);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_meal,parent,false);
        return new MealAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
        MealDomain mealDomain = mealDomains.get(position);

        holder.textViewMealName.setText(mealDomain.getName());
        holder.textViewDesc.setText(mealDomain.getDescription());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageMenuActivity.class, android.R.layout.activity_list_item, mealDomain.getIngredients());
        holder.listViewIngredients.setAdapter(
                new ArrayAdapter<String>()
        );

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout mealCard;
        private TextView textViewMealName;
        private TextView textViewDesc;
        private Switch switchOffered;
        private ListView listViewIngredients;
        private ListView listViewAllergens;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealCard = itemView.findViewById(R.id.mealCard);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewDesc = itemView.findViewById(R.id.textViewMealDesc);
            switchOffered = itemView.findViewById(R.id.switchOffered);
            listViewIngredients = itemView.findViewById(R.id.listViewIngredients);
            listViewAllergens = itemView.findViewById(R.id.listViewAllergens);

        }
    }
}
