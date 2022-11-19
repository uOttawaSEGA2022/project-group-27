package com.example.mealerapp.Adapter;

import static java.lang.Long.getLong;

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

        holder.textViewCookID.setText(complaintDomain.get_Cook_ID());
        holder.textViewDescription.setText(complaintDomain.getDescription());

        holder.btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintDomain.setActioned(true);
                FirebaseFirestore.getInstance().collection("complaints")
                        .document(complaintDomain.getId())
                        .delete();
                notifyDataSetChanged();
            }
        });
        holder.btnSuspendCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintDomain.setActioned(true);
                FirebaseFirestore.getInstance().collection("users")
                        .document(complaintDomain.get_Cook_ID())
                        .update("suspended", true);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                FirebaseFirestore.getInstance().collection("users")
                        .document(complaintDomain.get_Cook_ID())
                        .update("until", new Date(calendar.getTimeInMillis() + 604800000L));
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
