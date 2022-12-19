package com.example.horizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModifyPassword extends AppCompatActivity {
    TextView username;
    EditText newPassword, confirmPassword;
    ImageButton back;
    Button update;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_modify_password);

        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        back = findViewById(R.id.back);
        update = findViewById(R.id.updatePassword);
        username = findViewById(R.id.username);
        String str = getIntent().getStringExtra("key");
        username.setText(str);

        db = FirebaseDatabase.getInstance().getReference("users");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyPassword.this, ProfileEdit.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(newPassword.getText().toString())){
                    Toast.makeText(ModifyPassword.this, "New password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword.getText().toString())){
                    Toast.makeText(ModifyPassword.this, "Password confirmation cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(ModifyPassword.this, "password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.child(str).child("password").setValue(confirmPassword.getText().toString());
                Intent i = new Intent(ModifyPassword.this, ProfileEdit.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });
    }
}