package com.example.mealerapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealerapp.Adapter.CartItemAdapter;
import com.example.mealerapp.Adapter.ComplaintAdapter;
import com.example.mealerapp.Domain.CartItemDomain;
import com.example.mealerapp.Domain.ComplaintDomain;
import com.example.mealerapp.Objects.CartItem;
import com.example.mealerapp.Objects.Complaint;
import com.example.mealerapp.Objects.Purchase;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CartFragment extends Fragment {


    private ArrayList<CartItemDomain> cartItemDomains;

    private ArrayList<CartItem> cartItems;

    private CartItemAdapter adapter;

    private RecyclerView recyclerViewCart;

    private Button btnCheckout;

    private FirebaseFirestore db;

    private String userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();

        recyclerViewCart = (RecyclerView) view.findViewById(R.id.recyclerViewCart);

        Bundle bundle = this.getArguments();

        userID = bundle.getString("userID");

        getCart();

        btnCheckout = (Button) view.findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CartItem item: cartItems){
                    Purchase purchase = new Purchase(item.getMealID(), item.getCookID(), userID);
                    db.collection("users").document(item.getClientID())
                            .collection("purchases")
                            .document(purchase.getID()).set(purchase);

                }


                db.collection("users").document(userID)
                        .collection("cart").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                        db.collection("users").document(userID)
                                                .collection("cart")
                                                .document((String)documentSnapshot.get("id")).delete();
                                    }
                                }
                            }
                        });

                cartItemDomains = new ArrayList<>();
                adapter.notifyDataSetChanged();


            }
        });

        return view;


    }

    private void getCart(){
        ArrayList<CartItem> tempitems = new ArrayList<>();
//        Toast.makeText(getActivity(), userID, Toast.LENGTH_SHORT).show();
        db.collection("users").document(userID).collection("cart")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                tempitems.add(documentSnapshot.toObject(CartItem.class));
                            }

                            cartItems = tempitems;


                        }else{
                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                        createCart();
                    }
                });
    }


    private void createCart(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerViewCart.setLayoutManager(linearLayoutManager);

        cartItemDomains = new ArrayList<>();

        for(CartItem item: cartItems){
            cartItemDomains.add(new CartItemDomain(item));
        }


        adapter = new CartItemAdapter(cartItemDomains, getActivity(), userID);

        recyclerViewCart.setAdapter(adapter);

    }
}