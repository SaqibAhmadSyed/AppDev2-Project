package com.example.horizon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private List<Game> cartlist;
    private Context context;
    private String username;
    public CartAdapter(List<Game> cartlist, Context context, String username) {
        this.cartlist = cartlist;
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);

        return new CartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name = cartlist.get(position).getTitle();
        holder.title.setText(name);
        holder.cost.setText(String.valueOf(cartlist.get(position).getPrice()));

        Glide.with(context).load(cartlist.get(position).getThumbnail()).into(holder.thumbnail);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("WARNING!")
                        .setMessage("Are you sure you want to delete this item?")
                        .setCancelable(false);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    FirebaseDatabase.getInstance().getReference("cart/"+username+"/"+cartlist.get(holder.getAdapterPosition()).getTitle()).removeValue();
                    cartlist.remove(holder.getAdapterPosition());
                    Intent i = new Intent(context, Cart.class);
                    i.putExtra("key", username);
                    context.startActivity(i);
                    Toast.makeText(context, "Item Deleted " + cartlist.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView cost, title;
        private ImageView thumbnail;
        private Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlecart);
            cost = itemView.findViewById(R.id.pricecart);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            delete = itemView.findViewById(R.id.deleteFromCart);
        }
    }
}
