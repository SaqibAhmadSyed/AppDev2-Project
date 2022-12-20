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
    Button profileEdit , availableGames , cart, findStore, news, setting, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_homepage);

        findStore = findViewById(R.id.findStore);
        profileEdit = findViewById(R.id.profileEdit);
        availableGames = findViewById(R.id.availableGamme);
        username = findViewById(R.id.username);
        news = findViewById(R.id.news);
        cart = findViewById(R.id.cart);
        setting = findViewById(R.id.setting);
        logout = findViewById(R.id.logout);
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
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Cart.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        findStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, FindStore.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, GameNews.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Setting.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}