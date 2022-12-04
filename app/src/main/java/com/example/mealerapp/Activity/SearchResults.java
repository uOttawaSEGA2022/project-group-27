package com.example.mealerapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.Adapter.ComplaintAdapter;
import com.example.mealerapp.Adapter.MealAdapter;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kotlinx.coroutines.channels.ProduceKt;

public class SearchResults extends AppCompatActivity {


    private SearchView searchView;
    private RecyclerView mealResults;

    private ArrayList<Meal> meals;
    private ArrayList<MealDomain> mealDomains;
    private MealAdapter adapter;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

//        searchView = (SearchView) findViewById(R.id.searchBar);
        mealResults = (RecyclerView) findViewById(R.id.mealResults);

        db = FirebaseFirestore.getInstance();

        getMeals();

        String query = getIntent().getStringExtra("query");


//        searchView.setQuery(query, false);
//        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return true;
//            }
//        });


    }

    public void getMeals(){
        ArrayList<Meal> tempMeals = new ArrayList<>();
        db
                .collection("meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Meal meal = documentSnapshot.toObject(Meal.class);
                                tempMeals.add(meal);
                            }

                            meals = tempMeals;

                            initializeMeals();

                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void initializeMeals(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mealResults.setLayoutManager(linearLayoutManager);

        mealDomains = new ArrayList<>();

        for(Meal meal: meals){
            mealDomains.add(new MealDomain(meal));
        }

        Toast.makeText(SearchResults.this, "Length of List: " + mealDomains.size(), Toast.LENGTH_SHORT).show();
        adapter = new MealAdapter(mealDomains, this);

        mealResults.setAdapter(adapter);

    }


//    public void queryMeals(String query){
//
//        List<MealDomain> filteredMeals = new ArrayList<>();
//
//
//        for(MealDomain filter: mealDomains){
//            if(filter.getName().toLowerCase().contains(query)){
//                filteredMeals.add(filter);
//                break;
//            }else if(filter.getDescription().toLowerCase().contains(query)) {
//                filteredMeals.add(filter);
//                break;
//            }else{
//                for(String allergens: filter.getAllergens()){
//                    if(allergens.toLowerCase().contains(query)){
//                        filteredMeals.add(filter);
//                        break;
//                    }
//                }
//                for(String ingredient: filter.getIngredients()){
//                    if(ingredient.toLowerCase().contains(query)){
//                        filteredMeals.add(filter);
//                        break;
//                    }
//                }
//            }
//
//        }
//
//        if(filteredMeals.isEmpty())
//            Toast.makeText(SearchResults.this, "No Meals Found!", Toast.LENGTH_SHORT).show();
//        else
//            adapter.setFilteredList(filteredMeals);
//
//
//    }

//    public void initializeMeals(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchResults.this,LinearLayoutManager.VERTICAL,false);
//
//        mealResults.setLayoutManager(linearLayoutManager);
//
//        mealDomains = new ArrayList<>();
//
//        db.collection("meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
//                        Meal meal = documentSnapshot.toObject(Meal.class);
//
//                        db.collection("users").document(meal.getCookID()).get()
//                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                                Cook cook = task.getResult().toObject(Cook.class);
//                                                mealDomains.add(
//                                                        new MealDomain(documentSnapshot.toObject(Meal.class), cook.getRating())
//                                                );
//
//                                            }
//                                        });
//
//
//                    }
//                }else{
//                    Toast.makeText(SearchResults.this, "Failed to Get Search Results", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        adapter = new MealAdapter(mealDomains, this);
//
//        mealResults.setAdapter(adapter);
//
//    }




}