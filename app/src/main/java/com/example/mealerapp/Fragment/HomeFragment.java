package com.example.mealerapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealerapp.Adapter.CuisineAdapter;
import com.example.mealerapp.Domain.CuisineDomain;
import com.example.mealerapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView cuisineList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cuisineList = (RecyclerView) view.findViewById(R.id.cuisine);

        cuisineList();

        return view;
    }

    private void cuisineList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

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
    public void onClick(View view) {

    }
}