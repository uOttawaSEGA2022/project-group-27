package com.example.mealerapp.Adapter;

import static java.lang.Long.getLong;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    public ArrayList<ComplaintDomain> complaintDomains;
    private Context mContext;

    private FirebaseFirestore db;



    public ComplaintAdapter(ArrayList<ComplaintDomain> complaintDomains, Context mContext) {
        this.complaintDomains = complaintDomains;
        this.mContext = mContext;
    }

    public ComplaintAdapter(ComplaintDomain complaintDomain) {
        complaintDomains = new ArrayList<>();
        complaintDomains.add(complaintDomain);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_complaint,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {
        ComplaintDomain complaintDomain = complaintDomains.get(position);

        db = FirebaseFirestore.getInstance();

        holder.textViewCookID.setText("Target Cook ID: " + complaintDomain.get_Cook_ID());
        holder.textViewDescription.setText(complaintDomain.getDescription());

        holder.btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintDomain.setActioned(true);
                db.collection("complaints")
                        .document(complaintDomain.getId())
                        .delete();
                complaintDomains.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        holder.btnSuspendCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Method not Implemented!", Toast.LENGTH_SHORT).show();

            // TODO Implement Suspend cook; Must use a dialog or fragment (or some other solution) to allow admin to specify time; Not part of deliverable 3 so I did not finish it

//                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//                final View dialogView = View.inflate(R.layout.suspend_dialog, null);
//                final AlertDialog dialog = dialogBuilder.create();
//
//
//
//                final Button btnCommit = dialog.findViewById(R.id.btnCommit);
//                final TextView textViewDaysUntil = dialog.findViewById(R.id.editTextDaysUntil);
//
//                btnCommit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int time = 0;
//                        if(!textViewDaysUntil.getText().toString().isEmpty()){
//                            try{
//                                time = Integer.parseInt(textViewDaysUntil.getText().toString().trim());
//                            }catch(Exception e){}
//                        }
//                    }
//                });

            }
        });

        holder.btnBanCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintDomain.setActioned(true);

                db.collection("users").document(complaintDomain.get_Cook_ID())
                        .update("suspended", true);
                db.collection("complaints").document(complaintDomain.getId()).delete();
                complaintDomains.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        });
    }
    @Override
    public int getItemCount() {
        return complaintDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout complaintCard;
        TextView textViewCookID;
        TextView textViewDescription;
        Button btnSuspendCook;
        Button btnBanCook;
        Button btnDismiss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewCookID = itemView.findViewById(R.id.textViewCookID);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            btnSuspendCook = itemView.findViewById(R.id.btnSuspendCook);
            btnBanCook = itemView.findViewById(R.id.btnBanCook);
            btnDismiss = itemView.findViewById(R.id.btnDismiss);

        }
    }

}
