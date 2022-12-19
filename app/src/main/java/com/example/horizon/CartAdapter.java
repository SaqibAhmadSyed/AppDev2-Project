package com.example.horizon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private List<Game> cartlist;

    public CartAdapter(List<Game> cartlist) {
        this.cartlist = cartlist;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new CartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name = cartlist.get(position).getTitle();
        holder.titel.setText(name);
        double p = cartlist.get(position).getPrice();
        String cos = String.valueOf((p));
        holder.cost.setText(cos);
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView cost;
        private TextView titel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



                titel = itemView.findViewById(R.id.titlecart);
                cost = itemView.findViewById(R.id.pricecart);


        }
    }



}
