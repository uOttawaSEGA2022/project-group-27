package com.example.mealerapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Adapter.ComplaintAdapter;
import com.example.mealerapp.Adapter.CuisineAdapter;
import com.example.mealerapp.Adapter.NotificationAdapter;
import com.example.mealerapp.Adapter.PurchaseAdapter;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Domain.CuisineDomain;
import com.example.mealerapp.Domain.NotificationDomain;
import com.example.mealerapp.Domain.PurchaseCookDomain;
import com.example.mealerapp.Domain.PurchaseDomain;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Objects.Notification;
import com.example.mealerapp.Objects.Purchase;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.Objects.UserRole;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


public class InboxFragment extends Fragment {

    private Button btnGenerateComplaint;

    private ArrayList<ComplaintDomain> complaintDomains;

    private ArrayList<PurchaseDomain> purchaseDomains;

    private ArrayList<Complaint> complaints;

    private ArrayList<Purchase> purchases;

    private ArrayList<Purchase> purchaseCook;

    private ComplaintAdapter adapter;

    private NotificationAdapter testAdapter;

    private PurchaseAdapter purchaseAdapter;

    private String userType, userID;

    private RecyclerView recyclerViewInbox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        recyclerViewInbox = (RecyclerView) view.findViewById(R.id.recyclerViewInbox);
        Bundle bundle = this.getArguments();

        userType = bundle.getString("userType");
        userID = bundle.getString("userID");

        btnGenerateComplaint = (Button) view.findViewById(R.id.btnGenerateComplaint);

        btnGenerateComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] UID = {"t6P5gjP47GOaWaaTfeCHw5rAye33", "tVjN4cQldyYpZGhtCll4WrxClnh1", "rA7h3SZ3paSw0DTogKDfe4Rs8Pv2", "iMfhDteYWxdTFJrbcN6xDDtEKXm2","I4IRP8Ubc7aMwapMDzgkYayhjcX2"};

                int random = new Random().nextInt(UID.length);
                Complaint complaint = new Complaint(UID[random], "Test Description", UUID.randomUUID().toString());

                FirebaseFirestore.getInstance().collection("complaints").document(complaint.getID()).set(complaint)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                if(userType.equals("Admin")){
                                    Toast.makeText(getActivity(), "Created Example Complaint",Toast.LENGTH_LONG).show();
                                    complaintDomains.add(new ComplaintDomain(complaint));
                                    testAdapter.notifyDataSetChanged();
                                }

                            }
                        });


            }
        });



        switch (userType){
            case "Admin":
                getPurchase();
                getComplaints();
                break;
            case "Client":
                getPurchase();
                break;
            case "Cook":
                getPurchaseCook();
                break;

        }



        return view;
    }

    private void getPurchaseCook(){

        ArrayList<Purchase> tmpPurchases = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("purchases")
                .whereEqualTo("cookID", userID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Purchase purchase = documentSnapshot.toObject(Purchase.class);
                                    tmpPurchases.add(purchase);

                            }
                            purchaseCook = tmpPurchases;
                        } else {
                            purchaseCook = new ArrayList<>();
                        }
                        createInbox();
                    }
                });



    }
    private void getPurchase(){
        ArrayList<Purchase> tmpPurchases = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("purchases")
                .whereEqualTo("clientID", userID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Purchase purchase = documentSnapshot.toObject(Purchase.class);
                                if (purchase.getClientID().equals(userID)) {
                                    tmpPurchases.add(purchase);
                                }
                            }
                            purchases = tmpPurchases;
                        } else {
                            purchases = new ArrayList<>();
                        }
                        createInbox();
                    }
                });

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
                                tempComplaints.add(complaint);
                            }

                            complaints = tempComplaints;


                        }else{
                            complaints = new ArrayList<>();
                        }
                        getPurchase();
                    }
                });

    }

    private void createInbox(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerViewInbox.setLayoutManager(linearLayoutManager);

        complaintDomains = new ArrayList<>();

        ArrayList<NotificationDomain> notificationDomains = new ArrayList<>();

        if(purchaseCook != null){
            for(Purchase purchase: purchaseCook){
                notificationDomains.add(new PurchaseCookDomain(purchase));
            }
        }

        if(complaints != null){
            for(Complaint complaint: complaints){
                complaintDomains.add(new ComplaintDomain(complaint));
                notificationDomains.add(new ComplaintDomain(complaint));
            }
        }

        if(purchases != null){
            for(Purchase purchase: purchases){
                notificationDomains.add(new PurchaseDomain(purchase));
            }
        }


//        adapter = new ComplaintAdapter(complaintDomains, getActivity());

        testAdapter = new NotificationAdapter(notificationDomains, getActivity());

        recyclerViewInbox.setAdapter(testAdapter);
    }




    private void createClientInbox(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewInbox.setLayoutManager(linearLayoutManager);

        purchaseDomains = new ArrayList<>();
        for(Purchase purchase: purchases){
            purchaseDomains.add(new PurchaseDomain(purchase));
        }

        purchaseAdapter = new PurchaseAdapter(purchaseDomains, getActivity());
        recyclerViewInbox.setAdapter(purchaseAdapter);
    }

    private void createAdminInbox(){



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerViewInbox.setLayoutManager(linearLayoutManager);

        complaintDomains = new ArrayList<>();

        ArrayList<NotificationDomain> notificationDomains = new ArrayList<>();

        for(Complaint complaint: complaints){
            complaintDomains.add(new ComplaintDomain(complaint));
            notificationDomains.add(new ComplaintDomain(complaint));
        }

        for(Purchase purchase: purchases){
            notificationDomains.add(new PurchaseDomain(purchase));
        }



//        adapter = new ComplaintAdapter(complaintDomains, getActivity());

        testAdapter = new NotificationAdapter(notificationDomains, getActivity());

        recyclerViewInbox.setAdapter(testAdapter);


    }



}