package com.example.mealerapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageMenuActivity extends AppCompatActivity {

    private List<Meal> menu;

    private Cook cook;
    private ListView listView_meal;
    private ArrayList<Meal> list_meal;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);


        String cook_uid = getIntent().getStringExtra("Cook_UID");

//
//
        getCook(cook_uid);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        listView_meal = (ListView) findViewById(R.id.meal_list);

        getCook(cook_uid);
        list_meal = cook.get_mealList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeals();
            }
        });

        ArrayAdapter ad = new ArrayAdapter(this, R.layout.activity_manage_menu, list_meal);
        listView_meal.setAdapter(ad);
        listView_meal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal target_meal =list_meal.get(i);
                showUpdateMealDialog(target_meal);
                return true;
            }
        });

    }

    private void addMeals(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View detailView = inflater.inflate(R.layout.activity_meal_detail, null);
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
                ArrayList<String> Ingredients = new ArrayList<String>(Arrays.asList(editIngredients.getText().toString().trim().split(", ")));
                ArrayList<String> Allergens = new ArrayList<String>(Arrays.asList(editAllergens.getText().toString().split(", ")));
                Double price = Double.valueOf(Double.valueOf(editPrice.getText().toString().trim()));
                String description = editDescription.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(course) && !TextUtils.isEmpty(cuisine) && !TextUtils.isEmpty(description)){
                    ArrayList<Meal> meals = cook.get_mealList();
                    meals.add(new Meal(name, course, cuisine, Ingredients, Allergens, price, description));
                    b.dismiss();
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
        final View detailView =inflater.inflate(R.layout.activity_meal_detail, null);
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
                ArrayList<String> Ingredients = new ArrayList<String>(Arrays.asList(editIngredients.getText().toString().trim().split(", ")));
                ArrayList<String> Allergens = new ArrayList<String>(Arrays.asList(editAllergens.getText().toString().split(", ")));
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
    private void updateMeal(Meal meal, String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description){
        ArrayList<Meal> meals = cook.get_mealList();
        meals.set(meals.indexOf(meal), new Meal(name, course, cuisine, ingredients, allergens, price, description));
        cook.setMeals(meals);
    }
    private void deleteMeal(Meal meal){
        ArrayList<Meal> meals = cook.get_mealList();
        meals.remove(meal);
        cook.setMeals(meals);
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
                        }
//                            Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

    }

}