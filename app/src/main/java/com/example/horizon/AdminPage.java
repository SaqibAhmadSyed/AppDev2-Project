package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AdminPage extends AppCompatActivity {
    TextView username;
    Button userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_page);

        username = findViewById(R.id.username);
        userList = findViewById(R.id.userList);
        String str = getIntent().getStringExtra("key");
        username.setText("WELCOME " + str);

        userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminpage = new Intent(AdminPage.this, UserList.class);
                startActivity(adminpage);
            }
        });
    }
}