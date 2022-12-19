package com.example.horizon;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        setOnClickListener();
        adapter = new GameAdapter(gameList,this, listener);
        gamerecyclerView.setAdapter(adapter);
        getGames();
        back = findViewById(R.id.back);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AvailableGames.this, Homepage.class);
                startActivity(back);
            }
        });




    }
    private void setOnClickListener(){
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
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(AvailableGames.this, "Error", Toast.LENGTH_SHORT);
            }
        });
    }
}