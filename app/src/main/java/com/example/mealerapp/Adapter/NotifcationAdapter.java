package com.example.mealerapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.R;

public class NotifcationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PurchaseViewHolder extends  RecyclerView.ViewHolder {
        TextView txtPurchaseName;
        TextView txtStatus;
        Button btnDeletePurchase;


        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPurchaseName = itemView.findViewById(R.id.txtPurchaseName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnDeletePurchase = itemView.findViewById(R.id.btnDeletePurchase);
        }
    }

}
