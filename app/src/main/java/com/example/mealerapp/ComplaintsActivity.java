package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class ComplaintsActivity extends AppCompatActivity {

    DatabaseReference databaseComplaints;
    ListView listViewComplaints;

    //theres no Complaint Class yet
    //List<Complaints> complaints;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_complaints);
//
//        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
//        databaseComplaints = FirebaseDatabase.getInstance().getReference("complaints");

        //complaints = new ArrayList<>();
//    }
}