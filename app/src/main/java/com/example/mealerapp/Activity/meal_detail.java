package com.example.mealerapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.Objects.Purchase;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class meal_detail extends AppCompatActivity {

    private FirebaseFirestore db;
    private Intent i;
    private Meal target_meal;
    private Cook target_cook;
    private String cookName, cookAddress, cookDescription, mealDetailName, mealPrice, mealCourse, mealCuisine, mealIngredient,
            mealAllergens, mealDescription, clientID;

    private TextView txtCookName, txtCookAddress, txtCookDescription, txtMealDetailName, txtMealPrice, txtMealCourse, txtMealCuisine,
                     txtMealIngredient, txtMealAllergens, txtMealDescription;
    private Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        txtCookName = findViewById(R.id.txtCookName);
        txtCookAddress = findViewById(R.id.txtCookAddress);
        txtCookDescription = findViewById(R.id.txtCookDescription);
        txtMealDetailName = findViewById(R.id.txtMealDetailName);
        txtMealPrice = findViewById(R.id.txtMealPrice);
        txtMealCourse = findViewById(R.id.txtMealCourse);
        txtMealCuisine = findViewById(R.id.txtMealCuisine);
        txtMealIngredient = findViewById(R.id.txtMealIngredient);
        txtMealAllergens = findViewById(R.id.txtMealAllergens);
        txtMealDescription = findViewById(R.id.txtMealDescription);
        btnPurchase = findViewById(R.id.btnPurchase);

        i = getIntent();

        mealDetailName = i.getStringExtra("name");
        clientID = i.getStringExtra("clientID");

        db = FirebaseFirestore.getInstance();
        Meal tmp = i.getParcelableExtra("meal");


        db  //TODO bugs program doesn't run into line 68 so it cannot get the document from database
                .collection("meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Meal tmp_meal = documentSnapshot.toObject(Meal.class);
                                if (tmp_meal.getName().equals(mealDetailName)){
                                    target_meal = tmp_meal;
                                }

                            }
                        } else{
                            System.out.println(12334);
                        }
                    }
                });
        db
                .collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                User tmp_user = documentSnapshot.toObject(User.class);
                                if (tmp_user.getuid().equals(target_meal.getCookID())){
                                    target_cook = documentSnapshot.toObject(Cook.class);
                                }
                            }
                        } else{
                            System.out.println(22222);
                        }
                    }
                });

        cookName = target_cook.getFirstName() + " " + target_cook.getLastName();
        cookAddress = target_cook.getAddress();
        cookDescription = target_cook.getDescription();
        mealPrice = target_meal.getPrice().toString();
        mealCourse = target_meal.getCourse();
        mealCuisine = target_meal.getCuisine();
        mealIngredient = target_meal.getIngredients().toString();
        mealAllergens = target_meal.getAllergens().toString();
        mealDescription = target_meal.getDescription();

        txtCookName.setText(cookName);
        txtCookAddress.setText(cookAddress);
        txtCookDescription.setText(cookDescription);
        txtMealDetailName.setText(mealDetailName);
        txtMealPrice.setText(mealPrice);
        txtMealCourse.setText(mealCourse);
        txtMealCuisine.setText(mealCuisine);
        txtMealIngredient.setText(mealIngredient);
        txtMealAllergens.setText(mealAllergens);
        txtMealDescription.setText(mealDescription);









        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Purchase new_purchase = new Purchase(target_meal.getID(), target_meal.getCookID(), clientID);
                db.collection("purchase").document(new_purchase.getID()).set(new_purchase);
            }
        });
    }
}