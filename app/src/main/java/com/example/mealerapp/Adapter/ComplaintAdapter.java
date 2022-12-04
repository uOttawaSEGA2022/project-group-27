package com.example.mealerapp.Adapter;

import static java.lang.Long.getLong;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Fragment.DatePickerFragment;
import com.example.mealerapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> implements DatePickerDialog.OnDateSetListener {

    public ArrayList<ComplaintDomain> complaintDomains;
    private Context mContext;

    private FirebaseFirestore db;
    Date d = null;


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

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View detailView = inflater.inflate(R.layout.suspend_dialog, null);
                alertDialog.setView(detailView);
                alertDialog.show();
                DatePicker dp = (DatePicker) detailView.findViewById(R.id.datePicker);
                Calendar c = Calendar.getInstance();
                dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker dp, int year, int month, int day) {
                        c.set(year, month+1, day);
                        d = c.getTime();
                    }
                });



                Button btnCommit = (Button) detailView.findViewById(R.id.btnCommit);
//                Button btnSelect = (Button) detailView.findViewById(R.id.btnSelectDate);
//                TextView displaySelected = (TextView) detailView.findViewById(R.id.dateDisplay);

//                btnSelect.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DialogFragment datePicker = new DatePickerFragment();
//                        //If this doesnt work try getChildFragmentManager
//                        datePicker.show(datePicker.getFragmentManager(), "datePicker");
//                    }
//                });

                btnCommit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        complaintDomain.setActioned(true);

                        db.collection("users").document(complaintDomain.get_Cook_ID())
                                .update("suspended", true);
                        db.collection("users").document(complaintDomain.get_Cook_ID())
                                .update("until", d);
                        db.collection("complaints").document(complaintDomain.getId()).delete();
                        complaintDomains.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();

                    }
                });
                // TODO: Add input field for date format in dialog and reimplement date suspension time in cook class and wherever else necessary (high priority)

                //  TODO: style suspend_dialog layout (low priority)


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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        //TODO update the textview 'displaySelected' here somehow (low prio)
        d = c.getTime();
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
