package com.example.mealerapp.Adapter;

import static java.lang.Long.getLong;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View detailView = inflater.inflate(R.layout.suspend_dialog, null);
                alertDialog.setView(detailView);
                alertDialog.show();

                EditText suspensionDate = (EditText) detailView.findViewById(R.id.editTextDaysUntil);
                Button btnCommit = (Button) detailView.findViewById(R.id.btnCommit);

                suspensionDate.addTextChangedListener(new TextWatcher() {

                    private String current = "";
                    private String ddmmyyyy = "DDMMYYYY";
                    private Calendar cal = Calendar.getInstance();

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals(current)) {

                            String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                            String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                            int cl = clean.length();
                            int sel = cl;
                            for (int i = 2; i <= cl && i < 6; i += 2) {
                                sel++;
                            }

                            //Fix for pressing delete next to a forward slash
                            if (clean.equals(cleanC)) sel--;

                            if (clean.length() < 8){

                                clean = clean + ddmmyyyy.substring(clean.length());
                            }else{

                                //This part makes sure that when we finish entering numbers
                                //the date is correct, fixing it otherwise
                                int day  = Integer.parseInt(clean.substring(0,2));
                                int mon  = Integer.parseInt(clean.substring(2,4));
                                int year = Integer.parseInt(clean.substring(4,8));

                                mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                                cal.set(Calendar.MONTH, mon-1);
                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);
                                // ^ first set year for the line below to work correctly
                                //with leap years - otherwise, date e.g. 29/02/2012
                                //would be automatically corrected to 28/02/2012

                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            suspensionDate.setText(current);
                            suspensionDate.setSelection(sel < current.length() ? sel : current.length());
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                btnCommit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy");
                        ParsePosition pos = new ParsePosition(0);
                        Date d = simpledateformat.parse(suspensionDate.getText().toString(),pos);

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
