package com.example.mealerapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapp.Activity.landingPage;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class AdminProfile extends Fragment {

    private Button btnLogout;

    private TextView textViewAdminWelcome;

    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);




        btnLogout = (Button) view.findViewById(R.id.btnLogoutAdmin);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landingPage activity = (landingPage) getActivity();

                activity.signOut();
            }
        });





        return view;
    }
}