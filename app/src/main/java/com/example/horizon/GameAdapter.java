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

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{
    private RecyclerViewClickListener listener;
    private List<Game> gameList;
    private Context context;
    static List<Game> listcart;


    public GameAdapter(List<Game> gameList, Context context, RecyclerViewClickListener listener) {
        this.gameList = gameList;
        this.context = context;
        this.listener = listener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,des, publ, genre,date;
        ImageView image;
        Button tocart;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

            image = itemView.findViewById(R.id.image);
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


      Glide.with(context).load(gameList.get(position).getThumbnail()).into(holder.image);

      //listcart.add(new Game(gameList.get(position).getTitle(),gameList.get(position).getShort_description(),gameList.get(position).getGenre(),gameList.get(position).getPublisher(),gameList.get(position).getRelease_date(),gameList.get(position).getThumbnail(),gameList.get(position).getPrice()));
        holder.tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "ADDED TO CART: ", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
