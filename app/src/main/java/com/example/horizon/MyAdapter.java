package com.example.horizon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Users> userlist;


    public MyAdapter(ArrayList<Users> userList) {
        this.userlist = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nametxt;
        private TextView usernametxt;
        private TextView passwordtxt;

        public MyViewHolder(final View view) {
            super(view);
            nametxt = view.findViewById(R.id.firstName);
            usernametxt = view.findViewById(R.id.username);
            passwordtxt = view.findViewById(R.id.password);


        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        String name = userlist.get(position).getfName();
        holder.nametxt.setText(name);
        String username = userlist.get(position).getUsername();
        holder.usernametxt.setText(username);
        String pass = userlist.get(position).getPassword();
        holder.passwordtxt.setText(pass);

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
