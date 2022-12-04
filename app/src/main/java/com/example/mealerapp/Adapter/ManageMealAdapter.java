package com.example.mealerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Domain.ManageMealDomain;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManageMealAdapter extends RecyclerView.Adapter<ManageMealAdapter.ViewHolder>{

    private ArrayList<ManageMealDomain> mealDomains;
    private Context mContext;

    public ManageMealAdapter(ArrayList<ManageMealDomain> mealDomains, Context mContext){
        this.mealDomains = mealDomains;
        this.mContext = mContext;
    }

    public ManageMealAdapter(ManageMealDomain mealDomain){
        mealDomains = new ArrayList<>();
        mealDomains.add(mealDomain);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_manage_meal,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageMealAdapter.ViewHolder holder, int position) {
        ManageMealDomain mealDomain = mealDomains.get(position);

        holder.textViewMealName.setText(mealDomain.getName());
        holder.textViewDesc.setText(mealDomain.getDescription());
        holder.textViewIngredients.setText(
                String.join(", ", mealDomain.getIngredients())
        );
        holder.textViewAllergens.setText(
                String.join(", ", mealDomain.getAllergens())
        );

        holder.switchOffered.setChecked(mealDomain.getOffered());

        holder.switchOffered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseFirestore.getInstance().collection("meals")
                        .document(mealDomain.getID())
                        .update(
                                "offered", isChecked
                        );

                mealDomain.setOffered(isChecked);
            }
        });

        holder.btnDeleteMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mealDomain.isOffered()){
                    Toast.makeText(mContext, "Cannot delete offered meal.", Toast.LENGTH_LONG).show();
                }else{
                    mealDomains.remove(holder.getAdapterPosition());
                    FirebaseFirestore.getInstance().collection("meals")
                            .document(mealDomain.getID())
                            .delete();
                    notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mealDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout mealCard;
        private TextView textViewMealName;
        private TextView textViewDesc;
        private Switch switchOffered;
        private TextView textViewIngredients;
        private TextView textViewAllergens;
        private Button btnDeleteMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealCard = itemView.findViewById(R.id.search_mealCard);
            textViewMealName = itemView.findViewById(R.id.search_textViewMealName);
            textViewDesc = itemView.findViewById(R.id.search_textViewMealDesc);
            switchOffered = itemView.findViewById(R.id.switchOffered);
            textViewIngredients = itemView.findViewById(R.id.search_textViewIngredients);
            textViewAllergens = itemView.findViewById(R.id.search_textViewAllergens);
            btnDeleteMeal = itemView.findViewById(R.id.btnDeleteMeal);


        }
    }
}
