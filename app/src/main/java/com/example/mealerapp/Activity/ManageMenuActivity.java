package com.example.mealerapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ManageMenuActivity extends AppCompatActivity {

    private List<Meal> menu;

    private Cook cook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        String cook_uid = getIntent().getStringExtra("Cook_UID");


        Log.d(TAG, cook_uid);

//        getCook(cook_uid);


    }

    private void getCook(String UID) {

        FirebaseFirestore.getInstance().
            collection("users")
                    .document(
                        UID
                    ).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        cook = documentSnapshot.toObject(Cook.class);
                    }
                });
                // TODO retrieve cook from Intent or some other means from landing page activity / profile fragment; currently considering passing the userID as an extra in the Intent and fetching using the ID


    }

    private void getMenu() {

        FirebaseFirestore.getInstance()
                .collection("meals").whereEqualTo("Cook", cook.getUID())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                menu.add(document.toObject(Meal.class));
                            }
                        }else
                            Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

    }

}