package com.example.mealerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapp.Fragment.AdminProfile;
import com.example.mealerapp.Fragment.CartFragment;
import com.example.mealerapp.Fragment.ClientProfile;
import com.example.mealerapp.Fragment.CookProfile;
import com.example.mealerapp.Objects.Administrator;
import com.example.mealerapp.Objects.Client;
import com.example.mealerapp.Fragment.HomeFragment;
import com.example.mealerapp.Fragment.InboxFragment;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.R;
import com.example.mealerapp.Objects.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class landingPage extends AppCompatActivity {

    private TextView textViewWelcome;
    private RecyclerView.Adapter adapter;
    private RecyclerView cuisineList;

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private CartFragment cartFragment;
    private Fragment profileFragment;


    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String userID;

    private User user;

    private Cook cook;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);


        bottomNavigationView = findViewById(R.id.bottom_nav);

        homeFragment = new HomeFragment();
//        inboxFragment = new InboxFragment();
//        cartFragment = new CartFragment();



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        initializeUser();


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



    }

    public void signOut(){
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void initializeUser() {
        userID = mAuth.getCurrentUser().getUid();
        db.collection("users")
                .document(userID)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        Bundle userTypes = new Bundle();
                        Bundle userIDs = new Bundle();
                        String userType = "";

                       switch(user.getRole()){
                           case "Admin":
                               user = new Administrator(user);
                                userType = "Admin";
                               profileFragment = new AdminProfile();
                               break;
                           case "Cook":
                                cook = documentSnapshot.toObject(Cook.class);
                                userType = "Cook";
                                profileFragment = new CookProfile(cook.getuid());
                                break;
                           case "Client":
                               user =  documentSnapshot.toObject(Client.class);
                               profileFragment = new ClientProfile();
                               userType = "Client";
                               break;

                       }

                        userTypes.putString("userType",userType);
                        userTypes.putString("userID", userID);
                        inboxFragment = new InboxFragment();
                        inboxFragment.setArguments(userTypes);

                        userIDs.putString("userID", userID);
                        cartFragment = new CartFragment();
                        cartFragment.setArguments(userIDs);

                        profileFragment.setArguments(userIDs);

                        String userName = user.getFirstName();

                        Bundle userNames = new Bundle();

                        userNames.putString("userName", userName);

                        homeFragment.setArguments(userNames);



                    }
                });
    }

    public User getUser(){
        return this.user;
    }

    public Cook getCook(){
        return this.cook;
    }

    public String getCurrentUser(){


        return new String();
    }

}