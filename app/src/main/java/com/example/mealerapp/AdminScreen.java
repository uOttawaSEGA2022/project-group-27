package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminScreen extends AppCompatActivity {

    private ListView listViewComplaints;
    private DatabaseReference dataBaseComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        dataBaseComplaints = FirebaseDatabase.getInstance().getReference("complaints");

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint product = complaints.get(i);
                showUpdateDeleteDialog(product.getId(), product.getProductName());
                return true;
            }
        });
    }
}