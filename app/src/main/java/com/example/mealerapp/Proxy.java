package com.example.mealerapp;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class Proxy {

    private FirebaseFirestore db;

    private FirebaseAuth mAuth;

    public Proxy() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

    }

    public boolean login(String email, String password){
        final boolean[] flag = {false};

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        flag[0] = true;
                    }
                });


        return flag[0];

    }
    public User getUser(String userID){
        final User[] user = {new User()};
        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user[0] = documentSnapshot.toObject(User.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                user[0] = null;
            }
        });

        return user[0];
    }

    public boolean saveUser(User user){

        final boolean[] flag = {false};

        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        flag[0] = true;
                    }
                });

        return flag[0];
    }


    public boolean registerUser(String email, String password){
        final boolean[] flag = {false};

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        flag[0] = true;
                    }
                });

        return flag[0];

    }

    public String getCurrentUserUID(){

        return mAuth.getCurrentUser().getUid();
    }

    public User getCurrentUser(){
        final User[] user = {new User()};
        db.collection("users").document(mAuth.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user[0] = documentSnapshot.toObject(User.class);
                    }
                });
        return user[0];
    }
    public boolean addCreditCard(String UID, CreditCard CC){

        final boolean[] flag = {false};

        db.collection("users").document(UID)
                .collection("creditCards").add(CC)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        flag[0] = true;
                    }
                });

        return flag[0];


    }

    public boolean deleteComplaint(String complaint_id) {
        final boolean[] flag = {false};

        db.collection("complaints").document(complaint_id)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        flag[0] = true;
                    }
                });

        return flag[0];
    }

    public void updateCookSuspension(String cook_id, boolean suspended) {
        db.collection("users").document(cook_id)
                .update("suspended", suspended);
    }

    public List<Complaints> getComplaints(){
        List<Complaints> complaints = new ArrayList<>();

        db.collection("complaints")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                complaints.add(
                                        document.toObject(Complaints.class)
                                );
                            }
                        }
                    }
                });

        return complaints;
    }
}
