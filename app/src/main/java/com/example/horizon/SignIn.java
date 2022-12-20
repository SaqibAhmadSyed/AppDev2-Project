package com.example.horizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    Button signIn;
    ImageButton back;
    EditText username, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.signIn);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        back = findViewById(R.id.back);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()){
                } else {
                    checkUser();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(SignIn.this, MainActivity.class);
                startActivity(g);
            }
        });
    }

    public Boolean validateUsername(){
        String val = username.getText().toString();
        if(val.isEmpty()){
            username.setError("Username cannot be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = pwd.getText().toString();
        if(val.isEmpty()){
            pwd.setError("Password cannot be empty");
            return false;
        } else {
            pwd.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String loginUsername = username.getText().toString().trim();
        String loginPassword = pwd.getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDb = ref.orderByChild("username").equalTo(loginUsername);

        checkUserDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    username.setError(null);
                    String pwdFromDb = snapshot.child(loginUsername).child("password").getValue(String.class);

                    if (Objects.equals(pwdFromDb, loginPassword)) {
                        username.setError(null);
                        Intent intent = new Intent(SignIn.this, Homepage.class);
                        intent.putExtra("key", loginUsername);
                        startActivity(intent);
                    } else {
                        pwd.setError("Invalid credentials");
                        pwd.requestFocus();
                    }
                    //Admin Credential
                } else if (username.getText().toString().equals("admin") && pwd.getText().toString().equals("1")){
                    Intent intent = new Intent(SignIn.this, AdminPage.class);
                    intent.putExtra("key", loginUsername);
                    startActivity(intent);
                } else {
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}