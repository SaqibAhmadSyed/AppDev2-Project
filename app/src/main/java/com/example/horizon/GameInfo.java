package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GameInfo extends AppCompatActivity {

    TextView title,genre,publisher,date,des, username;
    ImageView thumbnail;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game_info);
        username = findViewById(R.id.username);
        String str = getIntent().getStringExtra("key");
        username.setText(str);

        Bundle extras = getIntent().getExtras();
        String picture = extras.getString("thumbnail");
        String titlegame = extras.getString("title");
        String publishergame = extras.getString("publisher");
        String rdate = extras.getString("releasedate");
        String descript = extras.getString("description");
        String genreee = extras.getString("genre");
        String price  = extras.getString("money");




        title = findViewById(R.id.title);

        publisher = findViewById(R.id.publisher);
        date = findViewById(R.id.releasedate);
        des = findViewById(R.id.description);
        genre = findViewById(R.id.type);

        back = findViewById(R.id.back);
        thumbnail = findViewById(R.id.picture);
        title.setText(titlegame);
        publisher.setText(publishergame);
        date.setText(rdate);
        des.setText(descript);
        genre.setText(genreee);

        Glide.with(this).load(picture).into(thumbnail);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(GameInfo.this, AvailableGames.class);
                next.putExtra("key" ,str);
                startActivity(next);
            }
        });










    }
}