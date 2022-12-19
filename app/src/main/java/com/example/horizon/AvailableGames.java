package com.example.horizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvailableGames extends AppCompatActivity {

    ImageButton back;
    RecyclerView gamerecyclerView;
    LinearLayoutManager layoutManager;
    GameAdapter adapter;
    List<Game> gameList = new ArrayList<>();
    GameAdapter.RecyclerViewClickListener listener;
    DatabaseReference db;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_available_games);

        gamerecyclerView = findViewById(R.id.gamerecycler);
        layoutManager = new LinearLayoutManager(this);
        gamerecyclerView.setLayoutManager(layoutManager);
        username = findViewById(R.id.username);
        String str = getIntent().getStringExtra("key");
        username.setText(str);
        setOnClickListener(str);
        adapter = new GameAdapter(gameList,this, listener, str);
        gamerecyclerView.setAdapter(adapter);
        getGames();
        back = findViewById(R.id.back);

        db = FirebaseDatabase.getInstance().getReference("games");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AvailableGames.this, Homepage.class);
                back.putExtra("key", str);
                startActivity(back);

            }
        });




    }
    private void setOnClickListener(String username){
        listener = new GameAdapter.RecyclerViewClickListener(){
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), GameInfo.class);
                intent.putExtra("title",gameList.get(position).getTitle());
                intent.putExtra("publisher",gameList.get(position).getPublisher());
                intent.putExtra("releasedate",gameList.get(position).getRelease_date());
                intent.putExtra("thumbnail",gameList.get(position).getThumbnail());
                intent.putExtra("description",gameList.get(position).getShort_description());
                intent.putExtra("genre",gameList.get(position).getGenre());
                intent.putExtra("money",gameList.get(position).getPrice());
                intent.putExtra("key", username);
                startActivity(intent);
            }
        };
    }

    private void getGames(){
        RetrofitClient.getRetroFitClient().getGames().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if(response.isSuccessful()&& response.body() != null){
                    gameList.addAll(response.body());
                    adapter.notifyDataSetChanged();

                        /* to add the game in firebase once */
//                    db.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                db.setValue(gameList);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(AvailableGames.this, "Error", Toast.LENGTH_SHORT);
            }
        });
    }
}