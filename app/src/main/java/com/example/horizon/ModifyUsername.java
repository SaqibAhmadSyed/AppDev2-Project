package com.example.horizon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.HashMap;
import java.util.Map;

public class ModifyUsername extends AppCompatActivity {
    TextView username;
    EditText newUsername, confirmUsername;
    ImageButton back;
    Button update;
    DatabaseReference db;
    String fname, lname, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_modify_username);

        newUsername = findViewById(R.id.newUsername);
        confirmUsername = findViewById(R.id.confirmUsername);
        back = findViewById(R.id.back);
        update = findViewById(R.id.updateUsername);
        username = findViewById(R.id.username);
        String str = getIntent().getStringExtra("key");
        username.setText(str);


        db = FirebaseDatabase.getInstance().getReference("users").child(str);

        DatabaseReference firstNameNode = db.child("fName");
        DatabaseReference lastNameNode = db.child("lName");;
        DatabaseReference passwordNode = db.child("password");
        DatabaseReference usernameNode = db.child("username");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyUsername.this, ProfileEdit.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(newUsername.getText().toString())) {
                    Toast.makeText(ModifyUsername.this, "New username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmUsername.getText().toString())) {
                    Toast.makeText(ModifyUsername.this, "Username confirmation cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newUsername.getText().toString().equals(confirmUsername.getText().toString())) {
                    Toast.makeText(ModifyUsername.this, "Username does not match", Toast.LENGTH_SHORT).show();
                    return;
                }


                Map<String, Object> updates = new HashMap<>();
                updates.put(str, confirmUsername.getText().toString());
                updates.put(str+"/password", "1");
                updates.put(str+"/username", confirmUsername.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("").updateChildren(updates, null);
//                db.setValue(confirmUsername.getText().toString());
//                db.child("password").setValue("1");
//                db.setValue(confirmUsername.getText().toString());
//                db.child("username").setValue(confirmUsername.getText().toString());
            }
        });
    }
}