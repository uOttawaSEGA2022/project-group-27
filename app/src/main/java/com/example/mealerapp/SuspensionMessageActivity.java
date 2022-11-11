package com.example.mealerapp;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SuspensionMessageActivity extends AppCompatActivity {

    private DatabaseReference databaseSus;
    private Button buttonAgreeComplaints, btn_Delete, btn_TmpSus, btn_InfSus;
    private ListView listViewComplaints;
    private List<Complaints> complaints;

    private Proxy proxy;

    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspension_message);

        proxy = new Proxy();

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaints complaint = complaints.get(i);
                show_decision_site(complaint.getId(), complaint);
                return true;
            }
        });

    }

    protected void onStart() {
        super.onStart();

        //TODO Create Proxy method for event listener

        databaseSus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Complaints complaint = postSnapshot.getValue(Complaints.class);
                    complaints.add(complaint);
                }
//                ProductList productsAdapter = new ProductList(MainActivity.this, products);
//                listViewProducts.setAdapter(productsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void show_decision_site(final String complaint_id, Complaints cook_name){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_sus_decision, null);
        dialogBuilder.setView(dialogView);
        btn_Delete = (Button) findViewById(R.id.btnDelete);
        btn_TmpSus = (Button) findViewById(R.id.btnTmpSus);
        btn_InfSus = (Button) findViewById(R.id.btnInfSus);


        final AlertDialog b =dialogBuilder.create();
        b.show();
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComplaint(complaint_id);
                b.dismiss();
            }
        });
        btn_TmpSus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept_complaint(complaint_id, cook_name);
                deleteComplaint(complaint_id);
                b.dismiss();
            }
        });
        btn_InfSus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept_complaint(complaint_id, cook_name);
                deleteComplaint(complaint_id);
                b.dismiss();
            }
        });

    }


    private void accept_complaint(String id, Complaints c){
        proxy.updateCookSuspension(c.get_Cook_ID(), true);
        deleteComplaint(id);

//        FirebaseDatabase.getInstance().getReference("users").child(c.get_Cook_ID()).child("suspended").setValue(true);
//        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("complaints").child(id);
//
//        dr.removeValue();

        Toast.makeText(getApplicationContext(), "Suspend Successful", Toast.LENGTH_LONG).show();
    }

    private  void deleteComplaint(String id){

        proxy.deleteComplaint(id);

//        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("complaints").child(id);
//        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Complaint Deleted", Toast.LENGTH_LONG).show();
    }



}