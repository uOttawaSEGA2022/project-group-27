package com.example.mealerapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mealerapp.Objects.Meal;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kotlinx.coroutines.channels.ProduceKt;

public class SearchResults extends AppCompatActivity {

    private TextView title;
    private ListView meal_list;
    private ArrayList<Meal> meals;
    String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        meals = new ArrayList<>();

        title = findViewById(R.id.txtTitle);
        meal_list = findViewById(R.id.meal_list);

        Intent i = getIntent();
        type = i.getStringExtra("title");

        title.setText(type);


        FirebaseFirestore.getInstance()
                .collection("meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Meal target_meal = documentSnapshot.toObject(Meal.class);
//                                if (target_meal.getCuisine() == "") //TODO make filter for different type of meals

                                meals.add(target_meal);

                            }
                        }


                        createList();
                    }
                });

        meal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView txtCookName, txtCookAddress, txtCookDescription, txtMealDetailName, txtMealPrice, txtMealCourse, txtMealCuisine,
//                        txtMealIngredient, txtMealAllergens, txtMealDescription;
//                String cookName, cookAddress, cookDescription, mealDetailName, mealPrice, mealCourse, mealCuisine, mealIngredient,
//                        mealAllergens, mealDescription;
//                txtCookName = findViewById(R.id.txtCookName);
//                txtCookAddress = (TextView) findViewById(R.id.txtCookAddress);
//                txtCookDescription = (TextView) view.findViewById(R.id.txtCookDescription);
//                txtMealDetailName = view.findViewById(R.id.txtMealDetailName);
//                txtMealPrice = view.findViewById(R.id.txtMealPrice);
//                txtMealCourse = view.findViewById(R.id.txtMealCourse);
//                txtMealCuisine = view.findViewById(R.id.txtMealCuisine);
//                txtMealIngredient = view.findViewById(R.id.txtMealIngredient);
//                txtMealAllergens = view.findViewById(R.id.txtMealAllergens);
//                txtMealDescription = view.findViewById(R.id.txtMealDescription);
                HashMap<String, String> map = (HashMap) parent.getAdapter().getItem(position);
                String name = map.get("name");
//                cookName = new String();
//                cookAddress = new String();
//                cookDescription = new String();
//                mealDetailName = new String();
//                mealPrice = new String();
//                mealCourse = new String();
//                mealCuisine = new String();
//                mealIngredient = new String();
//                mealAllergens = new String();
//                mealDescription = new String();

//                cookName = txtCookName.getText().toString();
//                cookAddress = txtCookAddress.getText().toString();
//                cookDescription = txtCookDescription.getText().toString();
//                mealDetailName = txtMealDetailName.getText().toString();
//                mealPrice = txtMealPrice.getText().toString();
//                mealCourse = txtMealCourse.getText().toString();
//                mealCuisine = txtMealCuisine.getText().toString();
//                mealIngredient = txtMealIngredient.getText().toString();
//                mealAllergens = txtMealAllergens.getText().toString();
//                mealDescription = txtMealDescription.getText().toString();

                Intent intent = new Intent(SearchResults.this, meal_detail.class);
                intent.putExtra("name", name);
//                intent.putExtra("cookName", cookName);
//                intent.putExtra("cookAddress", cookAddress);
//                intent.putExtra("cookDescription", cookDescription);
//                intent.putExtra("mealDetailName", mealDetailName);
//                intent.putExtra("mealPrice", mealPrice);
//                intent.putExtra("mealCourse", mealCourse);
//                intent.putExtra("mealCuisine", mealCuisine);
//                intent.putExtra("mealIngredient", mealIngredient);
//                intent.putExtra("mealAllergens", mealAllergens);
//                intent.putExtra("mealDescription", mealDescription);
                startActivity(intent);

            }
        });


    }

    private void createList(){
        List<Map<String, String>> meal_detail = new LinkedList<>();
        for(Meal meal: meals){
            Map<String, String> tmp_map = new HashMap<>();
            tmp_map.put("name", meal.getName());
            meal_detail.add(tmp_map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), meal_detail, R.layout.sorted_meals,
                new String[]{"name"}, new int[]{R.id.txtMealName});
        meal_list.setAdapter(adapter);
    }



}