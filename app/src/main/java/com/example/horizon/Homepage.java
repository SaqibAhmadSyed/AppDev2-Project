package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    TextView username;
    Button profileEdit , availableGames , cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homepage);

        profileEdit = findViewById(R.id.profileEdit);
        availableGames = findViewById(R.id.availableGamme);
        username = findViewById(R.id.username);
        cart = findViewById(R.id.cart);
        String str = getIntent().getStringExtra("key");
        username.setText("WELCOME " + str);

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, ProfileEdit.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        availableGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, AvailableGames.class);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Cart.class);
                startActivity(intent);
            }
        });
    }
}