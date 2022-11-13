package com.example.mealerapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.Fragment.AdminProfile;
import com.example.mealerapp.Fragment.CartFragment;
import com.example.mealerapp.Fragment.ClientProfile;
import com.example.mealerapp.Fragment.CookProfile;
import com.example.mealerapp.Objects.Administrator;
import com.example.mealerapp.Objects.Client;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Fragment.HomeFragment;
import com.example.mealerapp.Fragment.InboxFragment;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.R;
import com.example.mealerapp.Objects.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.List;

public class landingPage extends AppCompatActivity implements View.OnClickListener {

//    private DrawerLayout drawer;
//    private NavigationView navigationView;
//    private Toolbar toolbar;

    private Button btnLogout;
    private TextView textViewWelcome;
    private RecyclerView.Adapter adapter;
    private RecyclerView cuisineList;

    BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private CartFragment cartFragment;
    private Fragment profileFragment;


    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private User user;

    private Cook cook;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        btnLogout = (Button) findViewById(R.id.btnLogout2);
        btnLogout.setOnClickListener(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        homeFragment = new HomeFragment();
        inboxFragment = new InboxFragment(); //TODO Implement logic for dynamically creating user inbox
        cartFragment = new CartFragment();
        profileFragment = new CookProfile();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();




//        initializeUser();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Fragment selectFragment = null;
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.home:
                        selectFragment = homeFragment;
                        break;
                    case R.id.inbox:
                        selectFragment = inboxFragment;
                        break;

                    case R.id.cart:
                        selectFragment = cartFragment;
                        break;

                    case R.id.profile:
                        selectFragment = profileFragment;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
                return true;
            }
        });

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();


    }

/* TODO Reimplement recycler for home page fragment

    private void cuisineList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        cuisineList=findViewById(R.id.cuisine);
        cuisineList.setLayoutManager(linearLayoutManager);

        ArrayList<CuisineDomain> cuisine = new ArrayList<>();
        cuisine.add(new CuisineDomain("Italian","cat_1"));
        cuisine.add(new CuisineDomain("Chinese","cat_2"));
        cuisine.add(new CuisineDomain("Greek","cat_3"));
        cuisine.add(new CuisineDomain("Mexican","cat_4"));
        cuisine.add(new CuisineDomain("Arabic","cat_5"));

        adapter=new CuisineAdapter(cuisine);
        cuisineList.setAdapter(adapter);


   }
*/
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogout2:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void initializeUser() {
        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);

                       switch(user.getRole()){
                           case "Admin":
                               user = new Administrator(user);
                                profileFragment = new AdminProfile();
                               break;
                           case "Cook":
                                user = documentSnapshot.toObject(Cook.class);
                                cook = documentSnapshot.toObject(Cook.class); // TODO find more elegant solution to convert this user type to cook so that I can be returned in the getCook method
                                profileFragment = new CookProfile();
                                break;
                           case "Client":
                               user =  documentSnapshot.toObject(Client.class);
                               profileFragment = new ClientProfile();
                               break;

                       }

                    }
                });
    }

    public User getUser(){
        return this.user;
    }

    public Cook getCook(){
        return this.cook;
    }
}