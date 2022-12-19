package com.example.horizon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private RecyclerViewClickListener listener;
    private List<Game> gameList;
    private Context context;
    static List<Game> cart;
    String username;
    String work;
    double pr;
    DatabaseReference db;


    public GameAdapter(List<Game> gameList, Context context, RecyclerViewClickListener listener, String username) {
        this.gameList = gameList;
        this.context = context;
        this.listener = listener;
        this.username = username;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, price;
        ImageView image;
        Button tocart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.thumbnail);
            tocart = itemView.findViewById(R.id.sendtocart);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        holder.title.setText(gameList.get(position).getTitle());
        holder.price.setText(String.valueOf(gameList.get(position).getPrice()));
        Glide.with(context).load(gameList.get(position).getThumbnail()).into(holder.image);

        holder.tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                work = gameList.get(holder.getAdapterPosition()).getTitle();
                pr = gameList.get(holder.getAdapterPosition()).getPrice();

                db = FirebaseDatabase.getInstance().getReference("cart");

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child(username).child(gameList.get(holder.getAdapterPosition()).getTitle()).setValue(gameList.get(holder.getAdapterPosition()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(view.getContext(), "ADDED TO CART: " + gameList.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
