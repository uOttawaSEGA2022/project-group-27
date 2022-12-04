package com.example.mealerapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mealerapp.Adapter.ManageMealAdapter;
import com.example.mealerapp.Domain.ManageMealDomain;
import com.example.mealerapp.Domain.MealDomain;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageMenuActivity extends AppCompatActivity {

    private List<Meal> menu;

    private Cook cook;
    private ListView listView_meal;
    private ArrayList<Meal> list_meal;
    private Button btnAdd;

    private RecyclerView recycleViewMeals;
    private ManageMealAdapter adapter;
    private ArrayList<ManageMealDomain> mealDomains;

    private String cookID;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;



    /*
    * Currently, the add meal method works and users can select add meal and fill in the boxes and it will push it to the database succesfully
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recycleViewMeals = (RecyclerView) findViewById(R.id.recyclerViewMeals);

        cookID = getIntent().getStringExtra("Cook_UID");

        btnAdd = (Button) findViewById(R.id.btnAdd);

        getCook(cookID);

        getMeals();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeals();
            }
        });



    }

    private void getMeals(){

        ArrayList<Meal> meals = new ArrayList<>();



        db.collection("meals")
                .whereEqualTo("cookID", cookID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                Meal meal = documentSnapshot.toObject(Meal.class);

                                meals.add(meal);


                            }
                            list_meal = meals;
                            createInitialRecycler();

                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



    }

    private void getCook(String UID) {


        db.
                collection("users")
                .document(
                        UID
                ).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        cook = documentSnapshot.toObject(Cook.class);

                    }
                });


    }

    private void addMeals(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View detailView = inflater.inflate(R.layout.editmeal_dialog, null);
        dialogBuilder.setView(detailView);

        final EditText editName = (EditText) detailView.findViewById(R.id.txtName);
        final EditText editCourse = (EditText) detailView.findViewById(R.id.txtCourse);
        final EditText editCuisine = (EditText) detailView.findViewById(R.id.txtCuisine);
        final EditText editIngredients = (EditText) detailView.findViewById(R.id.txtIngredients);
        final EditText editAllergens = (EditText) detailView.findViewById(R.id.txtAllergens);
        final EditText editPrice = (EditText) detailView.findViewById(R.id.txtPrice);
        final EditText editDescription = (EditText) detailView.findViewById(R.id.txtDescription);
        final Button btn_delete = (Button) detailView.findViewById(R.id.btnDelete);
        final Button btn_update = (Button) detailView.findViewById(R.id.btnUpdate);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String course = editCourse.getText().toString().trim();
                String cuisine = editCuisine.getText().toString().trim();
                ArrayList<String> Ingredients = new ArrayList<>(Arrays.asList(editIngredients.getText().toString().trim().split(",")));
                ArrayList<String> Allergens = new ArrayList<>(Arrays.asList(editAllergens.getText().toString().split(",")));
                String description = editDescription.getText().toString().trim();

                if(name == null || name.isEmpty()){
                    editName.setError("Name is Required");
                    editName.requestFocus();
                    return;
                }

                if(course == null || course.isEmpty()){
                    editCourse.setError("Course is Required");
                    editCourse.requestFocus();
                    return;
                }
                if(cuisine == null || cuisine.isEmpty()){
                    editCuisine.setError("Cuisine is Required");
                    editCuisine.requestFocus();
                    return;
                }
                if(editIngredients.getText().toString().isEmpty()){
                    editIngredients.setError("Ingredients are Required");
                    editIngredients.requestFocus();
                    return;
                }
                if(editAllergens.getText().toString().isEmpty()){
                    editAllergens.setError("Allergens must be specified; or enter None");
                    editAllergens.requestFocus();
                    return;
                }

                if(description == null || description.isEmpty()){
                    editDescription.setError("Description is Required");
                    editDescription.requestFocus();
                    return;
                }
                Double price = 0.0;
                try{
                    price = Double.valueOf(Double.valueOf(editPrice.getText().toString().trim()));
                }catch (Exception e){
                    editPrice.setError("Invalid Price");
                    editPrice.requestFocus();
                    return;
                }








                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(course) && !TextUtils.isEmpty(cuisine) && !TextUtils.isEmpty(description)){
//                    ArrayList<Meal> meals = cook.get_mealList();
                    Meal meal = new Meal(name, course, cuisine, Ingredients, Allergens, price, description, cookID);
                    list_meal.add(meal);
                    db.collection("meals").document(meal.getID()).set(meal);
                    b.dismiss();

                    mealDomains.add(
                            new ManageMealDomain(meal)
                    );

                    adapter.notifyDataSetChanged();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });



    }


    private void showUpdateMealDialog(Meal meal){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View detailView =inflater.inflate(R.layout.editmeal_dialog, null);
        dialogBuilder.setView(detailView);

        final EditText editName = (EditText) detailView.findViewById(R.id.txtName);
        final EditText editCourse = (EditText) detailView.findViewById(R.id.txtCourse);
        final EditText editCuisine = (EditText) detailView.findViewById(R.id.txtCuisine);
        final EditText editIngredients = (EditText) detailView.findViewById(R.id.txtIngredients);
        final EditText editAllergens = (EditText) detailView.findViewById(R.id.txtAllergens);
        final EditText editPrice = (EditText) detailView.findViewById(R.id.txtPrice);
        final EditText editDescription = (EditText) detailView.findViewById(R.id.txtDescription);
        final Button btn_delete = (Button) detailView.findViewById(R.id.btnDelete);
        final Button btn_update = (Button) detailView.findViewById(R.id.btnUpdate);

        String tmpIngredients = meal.getIngredients().toString().trim();
        tmpIngredients.replace("[", "");
        tmpIngredients.replace("]", "");
        String tmpAllergens = meal.getAllergens().toString().trim();
        tmpAllergens.replace("[", "");
        tmpAllergens.replace("]", "");

        editName.setText(meal.getName());
        editCourse.setText(meal.getCourse());
        editCuisine.setText(meal.getCuisine());
        editIngredients.setText(tmpIngredients);
        editAllergens.setText(tmpAllergens);
        editPrice.setText(meal.getPrice().toString());
        editDescription.setText(meal.getDescription());

        dialogBuilder.setTitle(meal.getName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String course = editCourse.getText().toString().trim();
                String cuisine = editCuisine.getText().toString().trim();
                ArrayList<String> Ingredients = new ArrayList<String>(Arrays.asList(editIngredients.getText().toString().trim().split(",")));
                ArrayList<String> Allergens = new ArrayList<String>(Arrays.asList(editAllergens.getText().toString().trim().split(",")));
                Double price = Double.valueOf(Double.valueOf(editPrice.getText().toString().trim()));
                String description = editDescription.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(course) && !TextUtils.isEmpty(cuisine) && !TextUtils.isEmpty(description)){
                    updateMeal(meal, name, course, cuisine, Ingredients, Allergens, price, description);
                    b.dismiss();
                }

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeal(meal);
                b.dismiss();
            }
        });


    }

    private void updateAdapter(Meal meal){




    }
    private void updateMeal(Meal meal, String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description){
//        ArrayList<Meal> meals = cook.get_mealList();

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("course", course);
        data.put("cuisine", cuisine);
        data.put("ingredients", ingredients);
        data.put("allergens", allergens);
        data.put("price", price);
        data.put("description", description);


        db.collection("meals").document(meal.getID()).set(data, SetOptions.merge());


//        meals.set(meals.indexOf(meal), new Meal(name, course, cuisine, ingredients, allergens, price, description));
//        cook.setMeals(meals);
    }
    private void deleteMeal(Meal meal){
//        ArrayList<Meal> meals = cook.get_mealList();
        list_meal.remove(meal);

        db.collection("meals").document(meal.getID()).delete();

//        cook.setMeals(meals);
    }





    private void createInitialRecycler() {



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recycleViewMeals.setLayoutManager(linearLayoutManager);

        mealDomains = new ArrayList<>();

        for(Meal meal: list_meal){
            mealDomains.add(new ManageMealDomain(meal));
        }

        adapter = new ManageMealAdapter(mealDomains, this);

        recycleViewMeals.setAdapter(adapter);

    }

}