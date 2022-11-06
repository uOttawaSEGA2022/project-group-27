package com.example.mealerapp;

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

import java.util.List;

public class SuspensionMessageActivity extends AppCompatActivity {

    DatabaseReference databaseSus;
    Button buttonAgreeComplaints;
    ListView listViewProducts;
    List<Complaints> complaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspension_message);
        databaseSus = FirebaseDatabase.getInstance().getReference("complaints");
    }

    protected void onStart() {
        super.onStart();
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

//    private void show_decision_site(final String complaint_id, String cook_name){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
//        dialogBuilder.setView(dialogView);
//    }


    private void accept_complaint(Complaints c, String id){
        c.get_Cook().suspended = true;  //set suspend to cook
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("complaints").child(id);
        dr.removeValue();
        Toast.makeText(getApplicationContext(), "Suspend Successful", Toast.LENGTH_LONG).show();
    }
    private void refuse_complaint(Complaints c, String id){
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("complaints").child(id);
        dr.removeValue();
        Toast.makeText(getApplicationContext(), "Complaint deleted", Toast.LENGTH_LONG).show();
    }


}