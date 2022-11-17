package com.example.mealerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealerapp.Activity.MainActivity;
import com.example.mealerapp.Activity.ManageMenuActivity;
import com.example.mealerapp.Activity.landingPage;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.R;


public class CookProfile extends Fragment implements View.OnClickListener {

    private Button btnManageMenu;
    private View view;

    private Cook cook;

    public CookProfile(Cook cook){
        this.cook = cook;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cook_profile, container, false);

        btnManageMenu = (Button) view.findViewById(R.id.btnManageMenu);

        btnManageMenu.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.btnManageMenu):
                Intent intent = new Intent(getActivity(), ManageMenuActivity.class);
                startActivity(intent);
                break;
        }
    }
}