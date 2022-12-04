package com.example.mealerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.mealerapp.Activity.SearchResult;
import com.example.mealerapp.Adapter.CuisineAdapter;
import com.example.mealerapp.Adapter.MealTypeAdapter;
import com.example.mealerapp.Domain.CuisineDomain;
import com.example.mealerapp.Domain.MealTypeDomain;
import com.example.mealerapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView cuisineList;
    private RecyclerView mealTypeList;


    private SearchView search_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cuisineList = (RecyclerView) view.findViewById(R.id.cuisine);

        cuisineList();

        mealTypeList = (RecyclerView) view.findViewById(R.id.mealType);

        mealTypeList();

        search_home = (SearchView) view.findViewById(R.id.search_home);

        search_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getContext(), SearchResult.class);
                intent.putExtra("query", s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return view;
    }

    private void cuisineList(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        cuisineList.setLayoutManager(linearLayoutManager);

        ArrayList<CuisineDomain> cuisine = new ArrayList<>();
        cuisine.add(new CuisineDomain("Italian","pizza"));
        cuisine.add(new CuisineDomain("Chinese","chinese"));
        cuisine.add(new CuisineDomain("Mexican","mexican"));
        cuisine.add(new CuisineDomain("Japanese","cat_4"));
        cuisine.add(new CuisineDomain("Arabic","cat_5"));

        adapter=new CuisineAdapter(cuisine);

        cuisineList.setAdapter(adapter);


    }

    private void mealTypeList(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        mealTypeList.setLayoutManager(linearLayoutManager);

        ArrayList<MealTypeDomain> mealType = new ArrayList<>();
        mealType.add(new MealTypeDomain("Breakfast","pizza"));
        mealType.add(new MealTypeDomain("Lunch","chinese"));
        mealType.add(new MealTypeDomain("Dinner","mexican"));
        mealType.add(new MealTypeDomain("Dessert","cat_4"));


        adapter=new MealTypeAdapter(mealType);

        mealTypeList.setAdapter(adapter);


    }


    @Override
    public void onClick(View view) {

    }
}