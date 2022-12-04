package com.example.mealerapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.appsearch.SearchResults;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mealerapp.Adapter.MealAdapter;
import com.example.mealerapp.Domain.ManageMealDomain;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Locale;

public class SearchResult extends AppCompatActivity {

    private FirebaseFirestore db;

    private ArrayList<Meal> meals;

    private RecyclerView recyclerViewMeals;
    private SearchView searchBar;

    private MealAdapter adapter;
    private ArrayList<MealDomain> mealDomains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        db = FirebaseFirestore.getInstance();

        recyclerViewMeals = (RecyclerView) findViewById(R.id.recycleViewMeals);
        searchBar = (SearchView) findViewById(R.id.searchBar);

        getMeals();

        String query = getIntent().getStringExtra("query");

        searchBar.setQuery(query, false);
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                queryMeals(s);
                return true;
            }
        });

    }

    private void getMeals() {
        ArrayList<Meal> tempMeals = new ArrayList<>();

        db.collection("meals").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                Meal meal = documentSnapshot.toObject(Meal.class);

                                tempMeals.add(meal);
                            }
                            meals = tempMeals;
                            createInitialRecycler();
                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void createInitialRecycler() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerViewMeals.setLayoutManager(linearLayoutManager);

        mealDomains = new ArrayList<>();

        for(Meal meal: meals){
            mealDomains.add(new MealDomain(meal));
        }

        adapter = new MealAdapter(mealDomains, this);

        recyclerViewMeals.setAdapter(adapter);

    }


    public void queryMeals(String query){

        ArrayList<MealDomain> filteredMeals = new ArrayList<>();

        if(query.isEmpty())
            return;

        query = query.toLowerCase();

        for(MealDomain filter: mealDomains){
            if(filter.getName().toLowerCase().contains(query)){
                filteredMeals.add(filter);
                break;
            }else if(filter.getDescription().toLowerCase().contains(query)) {
                filteredMeals.add(filter);
                break;
            }else{
                for(String allergens: filter.getAllergens()){
                    if(allergens.toLowerCase().contains(query)){
                        filteredMeals.add(filter);
                        break;
                    }
                }
                for(String ingredient: filter.getIngredients()){
                    if(ingredient.toLowerCase().contains(query)){
                        filteredMeals.add(filter);
                        break;
                    }
                }
            }

        }

        if(filteredMeals.isEmpty())
            Toast.makeText(this, "No Meals Found!", Toast.LENGTH_SHORT).show();
        else
            adapter.setFilteredList(filteredMeals);


    }
}