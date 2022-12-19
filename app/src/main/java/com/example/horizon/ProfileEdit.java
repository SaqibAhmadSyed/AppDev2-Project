package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEdit extends AppCompatActivity {
    TextView username;
    Button delete, modifyName, modifyPwd;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_edit);

        username = findViewById(R.id.username);
        delete = findViewById(R.id.delete);
        modifyPwd = findViewById(R.id.modifyPwd);
        modifyName = findViewById(R.id.modifyName);
        back = findViewById(R.id.back);
        String str = getIntent().getStringExtra("key");
        username.setText(str);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileEdit.this, Homepage.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        modifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEdit.this, ModifyUsername.class);
                intent.putExtra("key", str);
                startActivity(intent);
            }
        });

        modifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                i.putExtra("key",str);
                startActivity(i);
            }
        });
    }
}