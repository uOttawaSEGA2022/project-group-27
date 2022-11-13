package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class landingPage extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private Button btnLogout;
    private TextView textViewWelcome;
    private RecyclerView.Adapter adapter;
    private RecyclerView cuisineList;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseFirestore db;

    private List<Complaints> complaints;
    private String userID;

    //now we no longer want to use the side navbar so we only want the bottom navbar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

//        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

//        textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);
        cuisineList();




//        drawer = findViewById(R.id.drawer_layout);

//        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);





        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView =(NavigationView) findViewById(R.id.bottom_nav);
        navigationView.setNavigationItemSelectedListener(this);








        mAuth = FirebaseAuth.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        db = FirebaseFirestore.getInstance();

        db.collection("users")
                        .document(mAuth.getCurrentUser().getUid())
                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);

                        if(user.getSuspended() == true){
                            if(user.until == null){
                                Toast.makeText(landingPage.this, "Your account has been permanently suspended", Toast.LENGTH_LONG).show();
                            }else if(Calendar.getInstance().after(user.getUntil())){
                                user.suspended = false;
                                user.until = null;
                            }else{
                            Toast.makeText(landingPage.this, "Your account has been suspended until " + user.until, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            textViewWelcome.setText("Welcome " + user.getFirstName() + "!");
                        }

                        if (user.getRole().equalsIgnoreCase("admin")){
                            navigationView.getMenu().findItem(R.id.nav_cooks).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_complaints).setVisible(true);
                        } else{
                            navigationView.getMenu().findItem(R.id.nav_cooks).setVisible(false);
                            navigationView.getMenu().findItem(R.id.nav_complaints).setVisible(false);

                        }

                        if (user.getRole().equalsIgnoreCase("cook")){
                            navigationView.getMenu().findItem(R.id.nav_menu).setVisible(true);

                        } else{
                            navigationView.getMenu().findItem(R.id.nav_menu).setVisible(false);


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        switch(item.getItemId()){
            case R.id.home:
                setFragment(new HomeFragment());
                break;
            case R.id.cart:
                break;
            case R.id.inbox:
                setFragment(new InboxFragment());
                break;
            case R.id.profile:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }




    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogout:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
        }
    }
}