package com.example.mealerapp.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealerapp.Domain.CartItemDomain;
import com.example.mealerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private List<CartItemDomain> cartItems;
    private Context mContext;

    private String userID;

    private FirebaseFirestore db;

    public CartItemAdapter(ArrayList<CartItemDomain> cartItems, Context mContext, String userID){
        this.cartItems = cartItems;
        this.mContext = mContext;

        this.userID = userID;

        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemDomain cartItem = cartItems.get(position);

        holder.textviewCartItemName.setText(cartItem.getMealName());

        Toast.makeText(holder.itemView.getContext(), "Subtotal: " + cartItem.getSubtotal(), Toast.LENGTH_SHORT).show();

        holder.textViewCartSubtotal.setText("Subtotal: "+cartItem.getSubtotal());
        holder.editTextCartQty.setText(""+cartItem.getQty());

//        holder.editTextCartQty.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                try{
//                    int qty = Integer.valueOf(holder.editTextCartQty.getText().toString());
//                    cartItem.setQty(qty);
//                    double subtotal = cartItem.getPrice() * qty;
//                    cartItem.setSubtotal(subtotal);
//                    holder.textViewCartSubtotal.setText("Subtotal: " + subtotal);
//                }catch(Exception e){}
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {}
//        });



        holder.btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("users").document(userID).collection("cart").document(cartItem.getID()).delete();


                cartItems.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.btnDecrementQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = cartItem.getQty();

                if(qty == 1){
                    return;
                }

                qty -= 1;
                cartItem.setQty(qty);
                double subtotal = cartItem.getPrice() * qty;
                holder.editTextCartQty.setText(""+qty);
                cartItem.setSubtotal(subtotal);
                holder.textViewCartSubtotal.setText("Subtotal: " + subtotal);

                Map<String, Object> updates = new HashMap<>();

                updates.put("qty", qty);
                updates.put("subtotal", subtotal);

                updateData(updates, cartItem.getID());

            }
        });

        holder.btnIncrementQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = cartItem.getQty();
                qty += 1;
                cartItem.setQty(qty);
                double subtotal = cartItem.getPrice() * qty;
                holder.editTextCartQty.setText(""+qty);
                cartItem.setSubtotal(subtotal);
                holder.textViewCartSubtotal.setText("Subtotal: " + subtotal);

                Map<String, Object> updates = new HashMap<>();

                updates.put("qty", qty);
                updates.put("subtotal", subtotal);

                updateData(updates, cartItem.getID());


            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    private void updateData(Map<String, Object> updates, String documentID){

        db.collection("users").document(userID).collection("cart")
                .document(documentID).update(updates);

    }

    public void clearData() {
        cartItems.clear();
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout cart_itemCard;
        private TextView textviewCartItemName;
        private TextView textViewCartSubtotal;
        private EditText editTextCartQty;
        private Button btnDeleteItem;
        private Button btnDecrementQty;
        private Button btnIncrementQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_itemCard = (ConstraintLayout) itemView.findViewById(R.id.cart_itemCard);
            textviewCartItemName = (TextView) itemView.findViewById(R.id.textViewCartItemName);
            textViewCartSubtotal = (TextView) itemView.findViewById(R.id.textViewCartSubtotal);
            editTextCartQty = (EditText) itemView.findViewById(R.id.editTextCartQty);
            btnDeleteItem = (Button) itemView.findViewById(R.id.btnDeleteItem);
            btnDecrementQty = (Button) itemView.findViewById(R.id.btnDecrementQty);
            btnIncrementQty = (Button) itemView.findViewById(R.id.btnIncrementQty);




        }
    }
}
