package com.example.mealerapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Domain.NotificationDomain;
import com.example.mealerapp.Domain.PurchaseDomain;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Objects.Notification;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * Note: This class is very experimental and should not be touched!
 *
 * */
public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<NotificationDomain> notifs;

    private Context mContext;

    private FirebaseFirestore db;
    Date d = null;


    public NotificationAdapter(ArrayList<NotificationDomain> notifs, Context mContext){
        this.notifs = notifs;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case 0:
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_complaint,parent,false);
                return new ComplaintViewHolder(inflate);

            case 1:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_purchase,parent, false);
                return new PurchaseViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationDomain notification = notifs.get(position);

        db = FirebaseFirestore.getInstance();

        if(notification != null){

            switch(getItemViewType(holder.getAdapterPosition())){
                case 0:
                    ComplaintDomain complaintDomain = (ComplaintDomain) notification;

                    ComplaintViewHolder complaintHolder = (ComplaintViewHolder) holder;
                    db = FirebaseFirestore.getInstance();

                    complaintHolder.textViewCookID.setText("Target Cook ID: " + complaintDomain.get_Cook_ID());
                    complaintHolder.textViewDescription.setText(complaintDomain.getDescription());

                    complaintHolder.btnDismiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            complaintDomain.setActioned(true);
                            db.collection("complaints")
                                    .document(complaintDomain.getId())
                                    .delete();
                            notifs.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    });
                    complaintHolder.btnSuspendCook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                            final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            final View detailView = inflater.inflate(R.layout.suspend_dialog, null);
                            alertDialogBuilder.setView(detailView);
                            final AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            DatePicker dp = (DatePicker) detailView.findViewById(R.id.datePicker);
                            Calendar c = Calendar.getInstance();
                            dp.setMinDate(c.getTimeInMillis() + 86400000);
                            dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener(){
                                @Override
                                public void onDateChanged(DatePicker dp, int year, int month, int day) {
                                    c.set(year, month, day);
                                    d = c.getTime();
                                }
                            });



                            Button btnCommit = (Button) detailView.findViewById(R.id.btnCommit);
//

                            btnCommit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    complaintDomain.setActioned(true);

                                    db.collection("users").document(complaintDomain.get_Cook_ID())
                                            .update("suspended", true);
                                    db.collection("users").document(complaintDomain.get_Cook_ID())
                                            .update("until", d);
                                    db.collection("complaints").document(complaintDomain.getId()).delete();
                                    notifs.remove(holder.getAdapterPosition());
                                    alertDialog.dismiss();
                                    notifyDataSetChanged();
                                }

                            });
                            // TODO: Add input field for date format in dialog and reimplement date suspension time in cook class and wherever else necessary (high priority)

                            //  TODO: style suspend_dialog layout (low priority)


                        }
                    });

                    complaintHolder.btnBanCook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            complaintDomain.setActioned(true);

                            db.collection("users").document(complaintDomain.get_Cook_ID())
                                    .update("suspended", true);
                            db.collection("complaints").document(complaintDomain.getId()).delete();
                            notifs.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();

                        }
                    });

                    // TODO handle complaint adapter settings
                    break;
                case 1:
                    PurchaseDomain purchaseDomain = (PurchaseDomain) notification;

                    PurchaseViewHolder purchaseHolder = (PurchaseViewHolder) holder;


                    purchaseHolder.txtPurchaseName.setText("Target Meal ID: " + purchaseDomain.purchase_meal_id);
                    purchaseHolder.txtStatus.setText(purchaseDomain.getStatus());

                    purchaseHolder.btnDeletePurchase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.collection("purchase").document(purchaseDomain.get_id())
                                    .delete();
                            notifs.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    });

                    // TODO handle purchase adapter settings
                    break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    @Override
    public int getItemViewType(int position){
        return notifs.get(position) instanceof ComplaintDomain ? 0 : 1;
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



    public class ComplaintViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout complaintCard;
        TextView textViewCookID;
        TextView textViewDescription;
        Button btnSuspendCook;
        Button btnBanCook;
        Button btnDismiss;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewCookID = itemView.findViewById(R.id.textViewCookID);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            btnSuspendCook = itemView.findViewById(R.id.btnSuspendCook);
            btnBanCook = itemView.findViewById(R.id.btnBanCook);
            btnDismiss = itemView.findViewById(R.id.btnDismiss);

        }
    }

}
