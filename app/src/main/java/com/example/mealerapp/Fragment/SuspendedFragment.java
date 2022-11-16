package com.example.mealerapp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealerapp.Activity.MainActivity;
import com.example.mealerapp.R;


public class SuspendedFragment extends Fragment implements View.OnClickListener {

    Button btnLogout;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_suspended, container, false);
        btnLogout = (Button) view.findViewById(R.id.buttonLogout);

        btnLogout.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_suspended, container, false);
    }

    @Override
    public void onClick(View view) {

        startActivity(new Intent(getActivity(), MainActivity.class));
    }

}