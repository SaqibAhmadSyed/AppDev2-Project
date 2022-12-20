package com.example.horizon;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart extends AppCompatActivity {

    RecyclerView rv;
    List<Game> list;
    ImageButton back;
    CartAdapter adapter;
    DatabaseReference db;
    TextView username;
    LinearLayoutManager layoutManager;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);

        checkout = findViewById(R.id.checkout);
        rv = findViewById(R.id.cartrecycler);
        list = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        username = findViewById(R.id.username);
        back = findViewById(R.id.back);
        String str = getIntent().getStringExtra("key");
        username.setText(str);
        checkUser(str);
        adapter = new CartAdapter(list, this, str);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Cart.this, Homepage.class);
                back.putExtra("key", str);
                startActivity(back);
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double total = 0.0 ;

                if(list.isEmpty() == true){

                    Toast.makeText(view.getContext(), "YOUR CART IS EMPTY", Toast.LENGTH_SHORT).show();
                }else{
                    for(Game game : list){
                        total += game.getPrice();
                    }
                    String tt = String.valueOf(total);
                    Toast.makeText(view.getContext(), "YOUR TOTAL IS: " + tt+ " THANK YOU FOR SHOPPING", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

    private void setAdapter(String username){
        CartAdapter adapter = new CartAdapter(list, this, username);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    public void checkUser(String username){

        db = FirebaseDatabase.getInstance().getReference("cart").child(username);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Game games = dataSnapshot.getValue(Game.class);
                    list.add(games);
                }
                adapter.notifyDataSetChanged();
                setAdapter(username);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}