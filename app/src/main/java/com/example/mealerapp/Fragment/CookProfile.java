package com.example.mealerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealerapp.Activity.AccountInfo;
import com.example.mealerapp.Activity.MainActivity;
import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Activity.landingPage;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class CookProfile extends Fragment implements View.OnClickListener {

    private Button btnManageMenu;
    private Button btnLogoutCook;
    private Button btnAccountInfo;
    private View view;
    private FirebaseFirestore db;

    private String UID;

    public CookProfile(String UID){
        this.UID = UID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cook_profile, container, false);

        btnManageMenu = (Button) view.findViewById(R.id.btnManageMenu);

        btnLogoutCook = (Button) view.findViewById(R.id.btnLogoutCook);

        btnManageMenu.setOnClickListener(this);

        btnLogoutCook.setOnClickListener(this);

        btnAccountInfo = (Button) view.findViewById(R.id.btnAccountInfo);

        btnAccountInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.btnManageMenu):
                Intent intent = new Intent(getActivity(), ManageMenuActivity.class);
                intent.putExtra("Cook_UID", UID);
                startActivity(intent);
                break;
            case(R.id.btnAccountInfo):
                intent = new Intent(getActivity(), AccountInfo.class);
                intent.putExtra("UID", UID);
                intent.putExtra("userType", "Cook");
                startActivity(intent);
                break;

            case(R.id.btnLogoutCook):
                landingPage activity = (landingPage) getActivity();
                activity.signOut();
                break;
            }

        }

    }
