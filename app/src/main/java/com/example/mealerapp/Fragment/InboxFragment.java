package com.example.mealerapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Adapter.ComplaintAdapter;
import com.example.mealerapp.Adapter.CuisineAdapter;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Domain.CuisineDomain;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Objects.Notification;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.Objects.UserRole;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class InboxFragment extends Fragment {

    private ArrayList<Complaint> complaints;

    private ComplaintAdapter adapter;

    private RecyclerView recyclerViewInbox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        recyclerViewInbox = (RecyclerView) view.findViewById(R.id.recyclerViewInbox);
        Bundle bundle = this.getArguments();

        String userType = bundle.getString("userType");

        switch (userType){
            case "Admin":
                getComplaints();
                break;

        }



        return view;
    }

    private void getComplaints(){
        ArrayList<Complaint> tempComplaints = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("complaints").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Complaint complaint = documentSnapshot.toObject(Complaint.class);
                                Toast.makeText(getActivity(), "Complaint ID: " + complaint.get_id(), Toast.LENGTH_SHORT).show();
                                tempComplaints.add(complaint);
                            }

                            complaints = tempComplaints;


                        }else{
                            complaints = new ArrayList<>();
                        }
                        createAdminInbox();
                    }
                });

    }
    private void createAdminInbox(){



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerViewInbox.setLayoutManager(linearLayoutManager);

        ArrayList<ComplaintDomain> complaintDomains = new ArrayList<>();

        for(Complaint complaint: complaints){
            complaintDomains.add(new ComplaintDomain(complaint));
        }


        adapter = new ComplaintAdapter(complaintDomains, getActivity());

        recyclerViewInbox.setAdapter(adapter);


    }



}