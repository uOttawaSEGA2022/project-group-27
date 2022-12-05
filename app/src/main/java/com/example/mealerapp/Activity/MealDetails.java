package com.example.mealerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mealerapp.Objects.CartItem;
import com.example.mealerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.UUID;

public class MealDetails extends AppCompatActivity {

    private TextView textViewCookName;
    private TextView textViewRating;
    private TextView textViewIngredients;
    private TextView textViewAllergens;
    private TextView textViewPrice;
    private TextView textViewMealName;
    private TextView textViewSubtotal;
    private TextView textViewDescription;
    private EditText editTextQty;
    private Button btnIncreaseQty;
    private Button btnDecreaseQty;
    private Button btnAddToCart;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private int qty;
    private Double subtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        textViewMealName = (TextView) findViewById(R.id.textViewMealName);
        textViewSubtotal = (TextView) findViewById(R.id.textViewSubtotal);
        textViewCookName = (TextView) findViewById(R.id.textViewCookName);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        textViewIngredients = (TextView) findViewById(R.id.textViewIngredients);
        textViewAllergens = (TextView) findViewById(R.id.textViewAllergens);
        textViewPrice = (TextView) findViewById(R.id.textViewMealPrice);
        textViewDescription = (TextView) findViewById(R.id.textViewDesc);
        editTextQty = (EditText) findViewById(R.id.editTextQty);
        btnIncreaseQty = (Button) findViewById(R.id.btnIncreaseQty);
        btnDecreaseQty = (Button) findViewById(R.id.btnDecreaseQty);
        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);


        Intent intent = getIntent();

        Map<String, String> data = (Map) intent.getSerializableExtra("data");

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        String cookName = db.collection("users").document(data.get("cookID")).get().toString();

        qty = 1;

        editTextQty.setText("" + qty);

        subtotal = Double.parseDouble(data.get("price")) * qty;

        textViewSubtotal.setText( "Subtotal: " + subtotal);

        textViewMealName.setText(data.get("name"));

        textViewRating.setText(data.get("rating"));

        textViewDescription.setText(data.get("description"));

        textViewIngredients.setText(data.get("ingredients"));

        textViewAllergens.setText(data.get("allergens"));

        textViewCookName.setText(cookName);

        textViewPrice.setText("Price: " + data.get("price"));

        btnIncreaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty += 1;
                editTextQty.setText("" + qty);
                subtotal = Double.parseDouble(data.get("price")) * qty;
                textViewSubtotal.setText("Subtotal: "+ subtotal);
            }
        });

        btnDecreaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(qty == 1)
                    return;

                qty -= 1;
                editTextQty.setText("" + qty);
                subtotal = Double.parseDouble(data.get("price")) * qty;
                textViewSubtotal.setText("Subtotal: "+ subtotal);
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = UUID.randomUUID().toString();
                CartItem item = new CartItem(
                        Double.parseDouble(data.get("price")),
                        qty,
                        data.get("name"),
                        subtotal,
                        uid,
                        data.get("ID")

                );

                db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("cart")
                                .document(uid).set(item);
                finish();
            }
        });





    }
}