package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

public class AdminScreen extends AppCompatActivity {

    private ListView listViewComplaints;
    private DatabaseReference dataBaseComplaints;
    private FirebaseFirestore db;
    private List<Complaints> c;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        db.collection("complaints")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            c.add(
                                    document.toObject(Complaints.class)
                            );
                        }
                    }
                });

        c = new ArrayList<>();

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaints complaints = c.get(i);
                showManageActivity(complaints.getId(), complaints.get_Cook_ID());
                return true;
            }
        });
    }

    private void showManageActivity(final String complaintId, String cookID) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.manage_complaint, null);
        dialogBuilder.setView(dialogView);

        final Button _susButton = (Button) dialogView.findViewById(R.id.susButton);
        final Button _deleteButton = (Button) dialogView.findViewById(R.id.deleteButton);
        final EditText _textUntil = (EditText) dialogView.findViewById(R.id.textUntil);

        _deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                deleteComplaint(complaintId);
            }
        });
        final AlertDialog b = dialogBuilder.create();
        b.show();

        _susButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("users").child(cookID);
                dR.child("suspended").setValue(true);

                if(_textUntil.toString() == ""){

                }else{

                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DAY_OF_YEAR,Integer.parseInt(_textUntil.toString()));
                    dR.child("until").setValue(c.getTime());
                }
            }
        });
    }

    private void deleteComplaint(String id) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("complaints").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Complaint Deleted", Toast.LENGTH_LONG).show();
    }
}