package com.example.mealerapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealerapp.Activity.landingPage;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class ClientProfile extends Fragment implements View.OnClickListener {

    private Button btnLogoutClient;

    private View view;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_client_profile, container, false);
        btnLogoutClient = (Button) view.findViewById(R.id.btnLogoutClient);
        btnLogoutClient.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {

        landingPage activity = (landingPage) getActivity();

        activity.signOut();

    }
}