package com.example.mealerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Domain.PurchaseDomain;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder>{

    public ArrayList<PurchaseDomain> purchaseDomains;
    private Context mContext;
    private FirebaseFirestore db;

    public PurchaseAdapter(ArrayList<PurchaseDomain> purchaseDomains, Context mcontext){
        this.purchaseDomains = purchaseDomains;
        this.mContext = mcontext;
    }
    public PurchaseAdapter(PurchaseDomain purchaseDomain){
        purchaseDomains = new ArrayList<>();
        purchaseDomains.add(purchaseDomain);
    }


    @NonNull
    @Override
    public PurchaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_purchase, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.ViewHolder holder, int position) {
        PurchaseDomain purchaseDomain = purchaseDomains.get(position);
        db = FirebaseFirestore.getInstance();

        holder.txtPurchaseName.setText("Target Meal ID: " + purchaseDomain.purchase_meal_id);
        holder.txtStatus.setText(purchaseDomain.getStatus());

        holder.btnDeletePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("purchase").document(purchaseDomain.get_id())
                        .delete();
                purchaseDomains.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return purchaseDomains.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtPurchaseName;
        TextView txtStatus;
        Button btnDeletePurchase;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPurchaseName = itemView.findViewById(R.id.txtPurchaseName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnDeletePurchase = itemView.findViewById(R.id.btnDeletePurchase);
        }
    }

}
