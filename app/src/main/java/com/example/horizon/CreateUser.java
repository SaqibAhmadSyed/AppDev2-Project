package com.example.horizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateUser extends AppCompatActivity {

    Button create;
    EditText firstN, lastN, username, pwd, confirmPwd;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_user);

        create = findViewById(R.id.create);
        firstN = findViewById(R.id.firstName);
        lastN = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        confirmPwd = findViewById(R.id.confirmPassword);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference("users");

                String firstName = firstN.getText().toString();
                String lastName = lastN.getText().toString();
                String userN = username.getText().toString();
                String password = pwd.getText().toString();
                String pwdConfirm = confirmPwd.getText().toString();

                if (TextUtils.isEmpty(firstName)){
                    Toast.makeText(CreateUser.this, "First Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lastName)){
                    Toast.makeText(CreateUser.this, "Last Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userN)){
                    Toast.makeText(CreateUser.this, "Username is empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)){
                    Toast.makeText(CreateUser.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pwdConfirm)){
                    Toast.makeText(CreateUser.this, "Confirm password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(pwdConfirm)){
                    Toast.makeText(CreateUser.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                Users users = new Users(firstName, lastName, userN, password);

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild(userN)) {
                            Toast.makeText(CreateUser.this, "This username already exists.", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            db.child(userN).setValue(users);

                            Intent intent = new Intent(CreateUser.this, SignIn.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}